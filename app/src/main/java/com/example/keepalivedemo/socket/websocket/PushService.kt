package com.example.keepalivedemo.socket.websocket

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
import android.text.TextUtils
import com.example.keepalivedemo.socket.WebSocketPush
import com.example.keepalivedemo.socket.common.ConstConfig
import com.example.keepalivedemo.socket.utils.Logs
import com.example.keepalivedemo.socket.utils.RxUtil
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.URI
import java.util.*


/**
 * Just : 长连接服务
 * @author by Zian
 * @date on 2019/08/19 10
 */
class PushService : Service() {

    private val TAG = "PushService"
    private val TAG_WEBSOCKET = "WebSocket"
    private var client: JWebSocketClient?= null

    val compositeDisposable = CompositeDisposable()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Logs.w(TAG, "PushService --> onCreate")
        super.onCreate()
        startForegroundService()

        initWebSocket()
        heartbeat()
        //logService()
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
        compositeDisposable.clear()
        Logs.w(TAG, "PushService --> onDestroy")
    }

    //打印服務運行中
    fun logService() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Logs.d(TAG, "running...")
            }
        }, 0, 10000)
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

    /**
     * webSocket
     */
    fun initWebSocket() {
        if (TextUtils.isEmpty(WebSocketPush.getWebSocketUri())) {
            Logs.e(TAG_WEBSOCKET, "web socket uri is empty")
            return
        }
        val disposable = Observable.just("")
            .observeOn(Schedulers.io())
            .subscribe {
                val uri = URI.create(WebSocketPush.getWebSocketUri())
                client = object : JWebSocketClient(uri, this) {}
                try {
                    client?.connectBlocking()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        compositeDisposable.add(disposable)
    }

    /**
     * 心跳检测
     */
    fun heartbeat() {
        var sendTime = 0L
        val disposable = RxUtil.polling(WebSocketPush.getBeatMilliseconds())
            .subscribe {
                Logs.i(TAG_WEBSOCKET, "heart beating....")
                if (System.currentTimeMillis() - sendTime >= WebSocketPush.getBeatMilliseconds()) {
                    client?.let {
                        if (!it.isOpen) {
                            it.close()
                            Logs.e(TAG, "web socket try to reconnect.... ${WebSocketPush.getWebSocketUri()}")
                            initWebSocket()
                        } else {
                            it.send("beating " + System.currentTimeMillis())
                        }
                    }
                }
                sendTime = System.currentTimeMillis()
            }
        compositeDisposable.add(disposable)
    }
}

