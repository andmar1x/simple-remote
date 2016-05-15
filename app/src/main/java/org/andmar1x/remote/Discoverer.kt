package org.andmar1x.remote

import android.content.SharedPreferences
import android.net.wifi.WifiManager
import android.support.annotation.IntRange
import android.text.TextUtils
import org.andmar1x.remote.model.Device
import timber.log.Timber
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.*
import javax.inject.Inject

class Discoverer
@Inject
constructor(wifiManager: WifiManager, sharedPreferences: SharedPreferences) {

    private val wifiManagerRef: WeakReference<WifiManager>

    private val groupHost: String
    private val port: Int

    private var multicastLock: WifiManager.MulticastLock? = null
    private var discoveryThread: Thread? = null

    init {
        this.wifiManagerRef = WeakReference(wifiManager)
        this.port = sharedPreferences.getInt("KEY_PORT", DEFAULT_PORT)
        this.groupHost = sharedPreferences.getString("KEY_HOST", DEFAULT_GROUP_HOST)
    }

    fun start(listener: Listener) {
        stop()

        val wifiManager = wifiManagerRef.get() ?: return

        multicastLock = wifiManager.createMulticastLock("lock")
        multicastLock?.acquire()

        try {
            val wifiInfo = wifiManager.connectionInfo
            val dhcpInfo = wifiManager.dhcpInfo
            if (wifiInfo == null || dhcpInfo == null) {
                stop()
            }

            val hostAddress = wifiInfo.ipAddress
            var broadcastIpAddress = hostAddress and dhcpInfo.netmask or dhcpInfo.netmask.inv()
            val broadcastAddressBytes = byteArrayOf(
                    (broadcastIpAddress and 0xFF).toByte(),
                    (broadcastIpAddress shr 8 and 0xFF).toByte(),
                    (broadcastIpAddress shr 16 and 0xFF).toByte(),
                    (broadcastIpAddress shr 24 and 0xFF).toByte())
            val broadcastAddress = InetAddress.getByAddress(broadcastAddressBytes)
            discoveryThread = Thread(DiscoveryRunnable(groupHost, port, broadcastAddress, listener))
            discoveryThread?.start()
        } catch (e: Exception) {
            Timber.e(e, "Failed to start discoverer")
            stop()
        }
    }

    fun stop() {
        discoveryThread?.interrupt()
        discoveryThread = null
        multicastLock?.release()
    }

    interface Listener {

        fun onDiscoverStarted()

        fun onDeviceFound(device: Device)

        fun onDiscoverFinished()
    }

    private class DiscoveryRunnable(private val groupHost: String,
                                    @IntRange(from = 0, to = 65535) private val port: Int,
                                    private val broadcastAddress: InetAddress,
                                    listener: Listener) : Runnable {

        private val listenerRef: WeakReference<Listener>

        private var groupAddress: InetAddress? = null
        private var networkInterface: NetworkInterface? = null

        private var socket: MulticastSocket? = null

        init {
            this.listenerRef = WeakReference(listener)
            try {
                this.groupAddress = InetAddress.getByName(groupHost)
                this.networkInterface = NetworkInterface.getByInetAddress(broadcastAddress)
            } catch (e: Exception) {
                Timber.w(e, "Failed to get group address")
            }
        }

        override fun run() {
            openSocket()
            joinGroup()
            onDiscoverStarted()

            try {
                send()
            } catch (e: IOException) {
                Timber.e(e, "Failed to send broadcast")
                abort()
                return
            }

            try {
                receive()
            } catch (e: SocketTimeoutException) {
                Timber.w(e, "Socket timed out")
            } catch (e: IOException) {
                Timber.w(e, "Failed to receive answer")
            }

            abort()
        }

        private fun openSocket() {
            try {
                socket = MulticastSocket(port)
                socket?.timeToLive = DEFAULT_TIME_TO_LIVE
                socket?.soTimeout = DEFAULT_SO_TIMEOUT
                //                socket?.networkInterface = networkInterface
            } catch (e: Exception) {
                Timber.e(e, "Failed to create socket")
                abort()
            }
        }

        private fun joinGroup() {
            if (groupAddress != null) {
                try {
                    socket?.joinGroup(groupAddress)
                } catch (e: IOException) {
                    Timber.w(e, "Failed to join group: %s", groupAddress.toString())
                }
            }
        }

        private fun leaveGroup() {
            if (groupAddress != null) {
                try {
                    socket?.leaveGroup(groupAddress)
                } catch (e: IOException) {
                    Timber.w(e, "Failed to leave group: %s", groupAddress.toString())
                }
            }
        }

        @Throws(IOException::class)
        private fun send() {
            val sendBuffer = DATAGRAM_MESSAGE_BROADCAST.toByteArray()
            val interfaces = NetworkInterface.getNetworkInterfaces()
            while (interfaces.hasMoreElements()) {
                val nextElement = interfaces.nextElement()
                if (nextElement.isLoopback || !nextElement.isUp) {
                    continue
                }

                for (interfaceAddress in nextElement.interfaceAddresses) {
                    val broadcast = interfaceAddress.broadcast ?: continue
                    val packet = DatagramPacket(sendBuffer, sendBuffer.size, broadcast, port)
                    socket?.send(packet)
                }
            }
        }

        @Throws(IOException::class)
        private fun receive() {
            while (true) {
                val receiveBuffer = ByteArray(32)
                val receivePacket = DatagramPacket(receiveBuffer, receiveBuffer.size)

                socket?.receive(receivePacket)

                val message = String(receivePacket.data).trim { it <= ' ' }
                if (!TextUtils.equals(message, DATAGRAM_MESSAGE_NOT_CONNECTED)) {
                    continue
                }

                onDeviceFound(receivePacket.address)
            }
        }

        private fun abort() {
            leaveGroup()
            socket?.close()
            onDiscoverFinished()
        }

        private fun onDiscoverStarted() {
            val listener = listenerRef.get()
            if (listener == null) {
                Timber.w("Listener is null")
                return
            }
            listener.onDiscoverStarted()
        }

        private fun onDeviceFound(inetAddress: InetAddress) {
            val listener = listenerRef.get()
            if (listener == null) {
                Timber.w("Listener is null")
                return
            }
            listener.onDeviceFound(Device(inetAddress.hostName, inetAddress.hostAddress))
        }

        private fun onDiscoverFinished() {
            val listener = listenerRef.get()
            if (listener == null) {
                Timber.w("Listener is null")
                return
            }
            listener.onDiscoverFinished()
        }
    }

    companion object {

        private val DEFAULT_GROUP_HOST = "224.0.1.0"

        private val DEFAULT_SO_TIMEOUT = 5000
        private val DEFAULT_TIME_TO_LIVE = 64
        private val DEFAULT_PORT = 5487

        private val DATAGRAM_MESSAGE_BROADCAST = "QRC:Broadcast"
        private val DATAGRAM_MESSAGE_NOT_CONNECTED = "QRC:NotConnected"
    }
}