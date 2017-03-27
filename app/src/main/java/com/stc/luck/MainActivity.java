package com.stc.luck;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    public static final String TAG = "luck";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new mainTask().execute();
        finish();
    }
    private class mainTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Process pSendPowerKeyEvent = Runtime.getRuntime().exec("su");
                DataOutputStream opt = new DataOutputStream(pSendPowerKeyEvent.getOutputStream());
                opt.writeBytes("input keyevent 26\n");
                opt.writeBytes("exit\n");
                opt.flush();
                pSendPowerKeyEvent.waitFor();
                if(0 == pSendPowerKeyEvent.exitValue()) {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(pSendPowerKeyEvent.getInputStream()));
                    StringBuilder log = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        log.append(String.format("%s\n", line));
                    }
                    if(0 < log.length()) Log.d(TAG, "run success result = \n " + log.toString());
                } else {
                    Log.d(TAG, "run failed exit value =  " + pSendPowerKeyEvent.exitValue());
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(pSendPowerKeyEvent.getErrorStream()));
                    StringBuilder log = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        log.append(String.format("%s\n", line));
                    }
                    if(0 < log.length()) Log.d(TAG, "Error = \n " + log.toString());
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
