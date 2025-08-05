package com.example.keepalivedemo.socket.common

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 17
 */
object ConstConfig {

//    /**
//     * websocket地址
//     */
//    var WEB_SOCKET_URI = ""
//
//    /**
//     * 心跳检测间隔时间
//     */
//    var BEAT_MILLISECONDS = 30L
//
//    /**
//     * 是否打印日志
//     */
//    var IS_DEBUG = false

    /**
     * 发送显示广播的指定路径
     */
    var BROADCAST_RECEIVER_PATH = ""


    /**
     * Notification渠道id
     */
    const val PUSH_CHANNEL_ID = "push_channel_id"

    /**
     * Notification 用户可以看得到的渠道名
     */
    const val PUSH_CHANNEL_NAME = "WEBSOCKET_PUSH"

    /**
     * Notification 用户可以看得到的渠道描述
     */
    const val PUSH_CHANNEL_DESCRITION = "推送服务"

    /**
     * 消息转发的 key
     */
    const val PUSH_MESSAGE_KEY = "push_service_key"


}