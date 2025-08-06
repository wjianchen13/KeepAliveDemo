package com.example.keepalivedemo.socket2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.keepalivedemo.Utils;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SocketManager {

    private static final String url = "ws://192.168.1.32:8443/";

    private static SocketManager INSTANCE = null;

    private Handler mHandler = null;

    private JWebSocketClient2 mClient = null;

    private SocketManager() {
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                heartbeat();
                startHeartBeat(15000);
            }
        };
    }

    public static SocketManager getInstance() {
        if (INSTANCE == null) {
            synchronized(SocketManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SocketManager();
                }
            }
        }
        return INSTANCE;
    }

    public void connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mClient = new JWebSocketClient2(new URI(url)){
                        @Override
                        public void onOpen(ServerHandshake handshakedata) {
                            super.onOpen(handshakedata);
                            startHeartBeat(15000);
                        }
                    };
                    mClient.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void close() {
        if(mClient != null) {
            mClient.close();
            mClient = null;
        }
    }

    public void send(String msg) {
        if(mClient != null && mClient.isOpen()) {
            Utils.log("send msg: " + msg);
            mClient.send(msg);
        }
    }

    private void heartbeat() {
        send("beating " + Utils.getCurrentData());
    }

    private void startHeartBeat(int delay) {
        if(mHandler != null) {
            mHandler.sendEmptyMessageDelayed(1, 15000);
        }
    }

}
