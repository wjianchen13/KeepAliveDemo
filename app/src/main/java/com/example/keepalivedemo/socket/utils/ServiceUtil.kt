package com.example.keepalivedemo.socket.utils

import android.app.ActivityManager
import android.content.Context

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 10
 */
object ServiceUtil {

    /**
     * 判断服务是否处于运行中
     */
    fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.getRunningServices(Int.MAX_VALUE)
        for (service in services) {
            if (service.javaClass.name == serviceClass.javaClass.name) {
                return true
            }
        }
        return false
    }
}