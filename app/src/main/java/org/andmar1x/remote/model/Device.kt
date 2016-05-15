package org.andmar1x.remote.model

data class Device(var hostName: String, var hostAddress: String) {

    var name: String? = null
    var connected: Boolean = false
}
