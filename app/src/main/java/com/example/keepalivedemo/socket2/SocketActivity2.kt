package com.example.keepalivedemo.socket2
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.keepalivedemo.R
import com.example.keepalivedemo.socket.websocket.PushService

/**
 * 不在Service开启网络连接，测试开启前台服务是否可以保持长连接
 */
class SocketActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket2)
    }

    fun onTest1(v: View) {
        SocketManager.getInstance().connect()
    }

    fun onTest2(v: View) {
        startService(this)
    }

    fun startService(context: Context) {
        val intent = Intent(context, PushService2::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    fun onTest3(v: View) {
        SocketManager.getInstance().close()
    }

    fun onTest4(v: View) {
        val intent = Intent(
            this,
            PushService2::class.java
        )
        stopService(intent)
    }
}
