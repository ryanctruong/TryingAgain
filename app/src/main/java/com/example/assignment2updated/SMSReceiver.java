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
        // Check if the broadcast action is an SMS received event
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            // Retrieve the SMS message
            final Bundle extras = intent.getExtras();
            if (extras != null) {
                final Object[] pdus = (Object[]) extras.get("pdus");
                String format = extras.getString("format");

                if (pdus != null) {
                    // Loop through the received SMS messages
                    for (Object pdu : pdus) {
                        SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu, format);

                        // Sender and message
                        String sender = sms.getDisplayOriginatingAddress();
                        String message = sms.getMessageBody();

                        // SMS details
                        String displayMessage = "Sender: " + sender + "\nMessage: " + message;
                        Log.i("SMS", displayMessage);

                        // Toast Notification
                        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();

                        // Intent to launch MainActivity
                        Intent mainActivityIntent = new Intent(context, MainActivity.class);
                        mainActivityIntent.putExtra("sms", message);

                        // Setting Flags
                        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Run applicaton
                        context.startActivity(mainActivityIntent);
                    }
                }
            }
        }
    }
}
