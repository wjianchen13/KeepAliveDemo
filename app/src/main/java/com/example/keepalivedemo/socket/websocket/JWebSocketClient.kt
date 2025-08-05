package com.example.keepalivedemo.socket.websocket

import android.content.Context
import android.content.Intent
import com.example.keepalivedemo.socket.common.ConstConfig
import com.example.keepalivedemo.socket.utils.Logs
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft_6455
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

/**
 * Just : webSocket
 * @author by Zian
 * @date on 2019/08/19 17
 */
open class JWebSocketClient(serverUri: URI?, val context: Context) : WebSocketClient(serverUri, Draft_6455()) {

    private val TAG = "WebSocketPush"

    init {
        Logs.i(TAG, "JWebSocketClient -- init")
    }

    override fun onOpen(handshakedata: ServerHandshake?) {
        Logs.i(TAG, "onOpen")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Logs.i(TAG, "onClose --> code: $code" + "reason: $reason")
    }

    override fun onMessage(message: String?) {
        Logs.i(TAG, "onMessage: \n$message")
        //发送显式广播
        val intent = Intent()
        intent.setClassName(context, ConstConfig.BROADCAST_RECEIVER_PATH)
        intent.putExtra(ConstConfig.PUSH_MESSAGE_KEY, message)
        context.sendBroadcast(intent)

    }

    override fun onError(ex: Exception?) {
        Logs.e(TAG, "onError: " + "\n" + ex?.printStackTrace())
    }
}