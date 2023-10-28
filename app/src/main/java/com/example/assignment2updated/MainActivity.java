package com.example.assignment2updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TickerViewModel ticker_VM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticker_VM = new ViewModelProvider(this).get(TickerViewModel.class);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            String[] permission = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permission, 101);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        if (message == null || message.isEmpty() || message.length() > 4 || !checkValidTicker(message)) {
                Toast.makeText(getApplicationContext(), "Invalid Ticker", Toast.LENGTH_LONG).show();
        } else {
            ticker_VM.addTicker(message.toUpperCase());
        }
    }


    private boolean checkValidTicker(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
