package org.andmar1x.remote.util.navigation

import android.content.Context
import android.content.Intent
import org.andmar1x.remote.ui.devices.DevicesActivity
import org.andmar1x.remote.ui.home.HomeActivity
import org.andmar1x.remote.ui.splash.SplashActivity

class Navigator {

    fun openSplashScreen(context: Context) {
        val intent = Intent(context, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun openHomeScreen(context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

    fun openDevicesScreen(context: Context) {
        val intent = Intent(context, DevicesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }
}
