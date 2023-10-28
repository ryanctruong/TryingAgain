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

        Intent act_intent = getIntent();
        if (act_intent != null) {
            String message = act_intent.getStringExtra("sms");
            if (message != null) {
                handleReceivedSMS(message);
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            String[] permission = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permission, 101);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("sms");
        if (message != null) {
            handleReceivedSMS(message);
        }
    }

    private void handleReceivedSMS(String message) {
        if (isValidSmsFormat(message)) {
            Toast.makeText(this, "Valid SMS: " + message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Invalid SMS format: " + message, Toast.LENGTH_SHORT).show();
        }

        ticker_VM.addTickers(message);
    }

    private boolean isValidSmsFormat(String sms) {
        if(sms.length() > 4) {
            return false;
        }
        char[] chars = sms.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
