package com.example.keepalivedemo.socket.utils

import android.util.Log
import com.example.keepalivedemo.socket.WebSocketPush

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 17
 */
object Logs {

    private val TAG = "TAG"

    fun i(tag: String, any: Any?) {
        if (WebSocketPush.getIsDebug())
            Log.i(tag, any?.toString() ?: "null")
    }

    fun d(tag: String, any: Any?) {
        if (WebSocketPush.getIsDebug())
            Log.d(tag, any?.toString() ?: "null")
    }

    fun w(tag: String, any: Any?) {
        if (WebSocketPush.getIsDebug())
            Log.w(tag, any?.toString() ?: "null")
    }

    fun e(tag: String, any: Any?) {
        if (WebSocketPush.getIsDebug())
            Log.e(tag, any?.toString() ?: "null")
    }

    fun i(any: Any?) {
        i(TAG, any)
    }

    fun d(any: Any?) {
        d(TAG, any)
    }

    fun w(any: Any?) {
        w(TAG, any)
    }

    fun e(any: Any?) {
        e(TAG, any)
    }
}