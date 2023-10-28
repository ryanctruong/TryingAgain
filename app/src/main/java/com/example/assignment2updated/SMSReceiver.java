package com.example.assignment2updated;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle extras = intent.getExtras();

        if (intent.getAction() != null && intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            if (extras != null) {
                final Object[] pdus = (Object[]) extras.get("pdus");
                String format = extras.getString("format");

                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu, format);

                        String sender = sms.getDisplayOriginatingAddress();
                        String message = sms.getMessageBody();

                        String displayMessage = "Sender: " + sender + "\nMessage: " + message;
                        Log.i("SMS", displayMessage);
                        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();

                        Intent mainActivityIntent = new Intent(context, MainActivity.class);
                        mainActivityIntent.putExtra("sms", message);
                        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(mainActivityIntent);
                    }
                }
            }
        }
    }
}
