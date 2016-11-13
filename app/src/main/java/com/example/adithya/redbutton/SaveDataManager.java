package com.example.adithya.redbutton;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by Adithya on 11/13/2016.
 */
public class SaveDataManager {
    private static final String SAVED_DATA = "saved_data";
    private static final String DEFAULT_MESSAGE = "I'm stressed.";
    private static final String MESSAGE_IDENTIFIER = "message";
    private static final String NUMBER_IDENTIFIER = "number";

    private static Strategy strat = Strategy.INCOMING_SMS;

    private static final String[] messages = {"Hey, I'm feeling stressed. Can we talk?", "I don't feel so great right now...", "Way too much on my mind atm", "Got R E K T with the caching project. Hoping for partial credit...screw this class."};
    private static int message_increment = 0;

    private enum Strategy {
        INCOMING_SMS
    }

    public static boolean checkingIncomingSMS() {
        return strat == Strategy.INCOMING_SMS;
    }

    public static void checkPermissions(Activity thisActivity) {
        ArrayList<String> permissions = new ArrayList<String>();
        String[] permissions_to_check = {Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS};
        for (int i = 0; i < permissions_to_check.length; i++) {
            if (ContextCompat.checkSelfPermission(thisActivity, permissions_to_check[i]) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(permissions_to_check[i]);
            }
        }

        if (permissions.size() > 0) {
            ActivityCompat.requestPermissions(thisActivity, permissions.toArray(new String[1]), thisActivity.hashCode() & 0xFFFF);
        }

    }

    public static String getSavedData() {
        return SAVED_DATA;
    }

    public static String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

    public static String getRandomMessage() {
        String s = messages[message_increment];
        if (++message_increment >= messages.length) {
            message_increment = 0;
        }
        return s;
    }

    public static String getMessageIdentifier() {
        return MESSAGE_IDENTIFIER;
    }

    public static String getNumberIdentifier() {
        return NUMBER_IDENTIFIER;
    }


}
