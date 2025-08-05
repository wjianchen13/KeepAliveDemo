package com.example.keepalivedemo.socket

import android.content.Context
import com.example.keepalivedemo.socket.common.ConstConfig
import com.example.keepalivedemo.socket.websocket.ServiceManager

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 16
 */
class WebSocketPush {

    companion object {

        private lateinit var mWebSocketPush: WebSocketPush
        private lateinit var mContext: Context

        /**
         * websocket地址
         */
        private var WEB_SOCKET_URI = "ws://192.168.1.32:8443/"

        fun getWebSocketUri(): String {
            return WEB_SOCKET_URI
        }

        /**
         * 心跳检测间隔时间
         */
        private var BEAT_MILLISECONDS = 30L

        fun getBeatMilliseconds(): Long {
            return BEAT_MILLISECONDS
        }

        /**
         * 是否打印日志
         */
        private var IS_DEBUG = false

        fun getIsDebug(): Boolean {
            return IS_DEBUG
        }

        fun newBuilder(context: Context): WebSocketPush {
            mWebSocketPush = WebSocketPush()
            mContext = context
            return mWebSocketPush
        }
    }

    /**
     * 设置webSocket链接
     */
    fun setWebSocketUri(uri: String): WebSocketPush {
        WEB_SOCKET_URI = uri
        return this
    }

    /**
     * web socket 发送心跳包间隔
     */
    fun setBeatingTime(milliseconds: Long): WebSocketPush {
        BEAT_MILLISECONDS = milliseconds
        return this
    }

    /**
     * 是否处于debug模式
     */
    fun setIsDebug(isDebug: Boolean): WebSocketPush {
        IS_DEBUG = isDebug
        return this
    }

    /**
     * 显示广播路径
     */
    fun setBroadcastReceiverPath(path: String): WebSocketPush {
        ConstConfig.BROADCAST_RECEIVER_PATH = path
        return this
    }

    /**
     * 启动服务
     */
    fun startService() {
        ServiceManager.instance.startService(mContext)
    }


}