package com.example.keepalivedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keepalivedemo.socket.SocketActivity;
import com.example.keepalivedemo.socket2.SocketActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTest1(View v) {
        startActivity(new Intent(this, SocketActivity.class));
    }

    public void onTest2(View v) {
        startActivity(new Intent(this, SocketActivity2.class));
    }

}