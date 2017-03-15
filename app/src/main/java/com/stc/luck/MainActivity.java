package com.stc.luck;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends Activity {
    private Process p=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            p = Runtime.getRuntime().exec("su shell input keyevent 26");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }
}
