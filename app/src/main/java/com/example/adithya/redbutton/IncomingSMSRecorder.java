package com.example.adithya.redbutton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import android.util.Log;

/**
 * Created by Adithya on 11/13/2016.
 */
public class IncomingSMSRecorder extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();
    private String saveDataString = SaveDataManager.getSavedData();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        String phoneNumber = null;
        try {
            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj.length > 0) {
                    phoneNumber = SmsMessage.createFromPdu((byte[]) pdusObj[0]).getDisplayOriginatingAddress();
                }
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }
}
