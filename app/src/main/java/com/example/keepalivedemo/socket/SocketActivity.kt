package com.example.keepalivedemo.socket
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.keepalivedemo.R

/**
 * Android 使用Java-WebSocket实现简易推送流程
 * https://blog.csdn.net/qq_30837235/article/details/99714121
 * 测试流程
 * 1.下载WebSocketMan v1.0.7
 * 2.服务器地址改为 ws://192.168.1.32:8443/ 点击开始监听
 * 地址可以查看网络设置，IPv4地址,右键网络->打开"网络和Internet设置"->左侧切换到状态->点击右侧的属性查看IPv4地址
 * 3.打开客户端
 *
 */
class SocketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)
        startService()
    }

    fun startService() {
        WebSocketPush.newBuilder(this)
            .setWebSocketUri("ws://192.168.1.32:8443/")//web socket地址
            .setBeatingTime(15000)//发送心跳包间隔
            .setBroadcastReceiverPath(PushReceiver::class.java.name)//广播路径 用于发送显式广播
            .setIsDebug(true)//是否打印日志
            .startService()//开启服务
    }
}
