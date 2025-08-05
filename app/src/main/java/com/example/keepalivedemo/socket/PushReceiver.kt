package com.example.keepalivedemo.socket

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.keepalivedemo.socket.common.ConstConfig

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 10
 */
class PushReceiver : BroadcastReceiver() {


    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.w("WebSocketPush", "onReceive")
        p1?.let {
            val message = it.getStringExtra(ConstConfig.PUSH_MESSAGE_KEY)
            Log.w("WebSocketPush -->", message!!)
        }
    }
}