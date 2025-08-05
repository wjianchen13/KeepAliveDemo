package com.example.keepalivedemo.socket.websocket

import android.content.Context
import android.content.Intent

/**
 * Just : 服务管理
 * @author by Zian
 * @date on 2019/08/19 10
 */
class ServiceManager {
    companion object {
        val instance: ServiceManager by lazy { ServiceManager() }
    }

    fun startService(context: Context) {
        val intent = Intent(context, PushService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }
}