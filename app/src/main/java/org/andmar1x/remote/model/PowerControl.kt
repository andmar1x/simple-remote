package org.andmar1x.remote.model

import android.support.annotation.DrawableRes

data class PowerControl(var type: Type, @DrawableRes var icon: Int, var title: String, var description: String) {

    enum class Type {
        POWER_OFF, REBOOT, LOCK
    }
}
