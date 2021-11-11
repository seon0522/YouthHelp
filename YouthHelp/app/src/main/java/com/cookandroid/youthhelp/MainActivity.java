package com.cookandroid.youthhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private NetworkThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new NetworkThread();
        thread.start();


//        정상처리 됨.
        Log.e("hello" , String.valueOf(thread));
    }
}