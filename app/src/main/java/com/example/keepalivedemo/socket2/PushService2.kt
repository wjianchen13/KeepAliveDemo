package com.example.keepalivedemo.socket2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import com.example.keepalivedemo.socket.common.ConstConfig
import com.example.keepalivedemo.socket.utils.Logs


/**
 * Just : 长连接服务
 * @author by Zian
 * @date on 2019/08/19 10
 */
class PushService2 : Service() {

    private val TAG = "PushService"

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Logs.w(TAG, "PushService --> onCreate")
        super.onCreate()
        startForegroundService()
    }

    //service 只有一个实例 多次调用会调用onStartCommand
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logs.w(TAG, "PushService --> onStartCommand")
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Logs.w(TAG, "PushService --> onTaskRemoved")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logs.w(TAG, "PushService --> onDestroy")
    }

    /**
     * 8.0开前台服务
     */
    fun startForegroundService() {
        val builder = Notification.Builder(applicationContext)
            .setContentTitle("PushService")
            .setContentText("PushService is running...")
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(ConstConfig.PUSH_CHANNEL_ID, ConstConfig.PUSH_CHANNEL_NAME, importance)
            //配置通知渠道的属性
            mChannel.description = ConstConfig.PUSH_CHANNEL_DESCRITION
            //设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true)
            mChannel.lightColor = Color.GREEN
            //设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true)
            //mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            //最后在notificationmanager中创建该通知渠道 //
            mNotificationManager.createNotificationChannel(mChannel)

            //为通知设置去渠道id
            builder.setChannelId(ConstConfig.PUSH_CHANNEL_ID)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 对于 Android 11 及以上版本，启动前台服务并指定多种服务类型
            val serviceTypes = ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE or
                    //ServiceInfo.FOREGROUND_SERVICE_TYPE_CAMERA or
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            this.startForeground(
                1,
                builder.build(),
                serviceTypes
            )
        } else {
            this.startForeground(1, builder.build())
        }
//        startForeground(1, builder.build())
    }

}

