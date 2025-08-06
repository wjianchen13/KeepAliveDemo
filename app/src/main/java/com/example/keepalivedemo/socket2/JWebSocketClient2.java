package com.example.keepalivedemo.socket2;

import android.content.Context;

import com.example.keepalivedemo.Utils;
import com.example.keepalivedemo.socket.utils.Logs;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * WebSocket
 */
public class JWebSocketClient2 extends WebSocketClient {

    private static final String TAG = JWebSocketClient2.class.getSimpleName();

    public JWebSocketClient2(URI serverUri) {
        super(serverUri, new Draft_6455(), null, 15000);
        Logs.INSTANCE.i(TAG, "JWebSocketClient -- init");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Utils.log("onOpen");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Utils.log("onOpen code: " + code + "  reason: " + reason + "  remote: " + remote);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        super.onMessage(bytes);
    }

    @Override
    public void onMessage(String message) {
        Utils.log("onMessage: " + message);
    }

    @Override
    public void onError(Exception ex) {
        Utils.log("onError: " + ex.getMessage());
    }
}