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
        final Bundle bundle = intent.getExtras();

        if (intent.getAction() != null && intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format");

                if (pdusObj != null) {
                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);

                        String sender = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getMessageBody();

                        String printMessage = "Sender: " + sender + "\nMessage: " + message;
                        Log.i("SMS", printMessage);
                        Toast.makeText(context, printMessage, Toast.LENGTH_LONG).show();

                        Intent actIntent = new Intent(context, MainActivity.class);
                        actIntent.putExtra("sms", message);
                        actIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(actIntent);
                    }
                }
            }
        }
    }
}