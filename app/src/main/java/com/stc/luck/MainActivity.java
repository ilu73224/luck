package com.stc.luck;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity {
    private Process p=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        try {
            p = Runtime.getRuntime().exec("su shell input keyevent 26");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("QQ", "dd");
        finish();//
    }
}
