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

    private TickerViewModel tickerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TickerViewModel
        tickerViewModel = new ViewModelProvider(this).get(TickerViewModel.class);

        // Request SMS receive permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permissions, 101);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Extract the SMS message from the intent
        String message = intent.getStringExtra("sms");

        // Check if the SMS is valid
        if (message == null || message.isEmpty() || message.length() > 4 || !isValidTicker(message)) {
            Toast.makeText(getApplicationContext(), "Invalid Ticker", Toast.LENGTH_LONG).show();
        } else {
            // if SMS is validate, change to upperCase bc websites only looks for UpperCase
            tickerViewModel.addTicker(message.toUpperCase());
        }
    }

    // Check if there are digitss
    private boolean isValidTicker(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
