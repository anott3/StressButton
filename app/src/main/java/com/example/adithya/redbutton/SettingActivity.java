package com.example.adithya.redbutton;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    private static String saveDataString = SaveDataManager.getSavedData();
    private String defaultMessageString = SaveDataManager.getDefaultMessage();
    String kevinsPhoneNumber = "4044216036";

    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        textView1 = (TextView) findViewById(R.id.number_textView);
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);

 textView1.setText(sp.getString(SaveDataManager.getNumberIdentifier(), kevinsPhoneNumber));

        textView2 = (TextView) findViewById(R.id.message_textView);
        textView2.setText(sp.getString(SaveDataManager.getMessageIdentifier(), defaultMessageString));

    }

    public static SharedPreferences getSharedPreferences (Context ctxt) {

        return ctxt.getSharedPreferences(saveDataString, 0);
    }

    private static String getRandomPhoneNumber() {
        ContentResolver cr = StressButton.getContext().getContentResolver(); //Activity/Application android.content.Context
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        ArrayList<String> allContacts = new ArrayList<String>();
        if(cursor.moveToFirst())
        {
            do
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                    while (pCur.moveToNext())
                    {
                        String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        allContacts.add(contactNumber);
                        break;
                    }
                    pCur.close();
                }

            } while (cursor.moveToNext()) ;
        }
        return allContacts.get((int)(Math.random()*allContacts.size()));
    }
    private void setSettingHelper(String settingIdentifier, String setting) {
        SharedPreferences sharedPreferences = getSharedPreferences(SettingActivity.this.getApplicationContext());
        SharedPreferences.Editor spe = sharedPreferences.edit();
        spe.putString(settingIdentifier, setting).commit();
    }

    public void setMessage(String message) {
        if (message != null && !message.equals("")) {
            setSettingHelper(SaveDataManager.getMessageIdentifier(), message);
        }
    }

    public void setNumber(String number, boolean randomNumber) {
        if (randomNumber) {
            setSettingHelper(SaveDataManager.getNumberIdentifier(), number);
        } else {
            boolean validIncomingSMS = SaveDataManager.checkingIncomingSMS();
            if (validIncomingSMS) {
                setSettingHelper(SaveDataManager.getNumberIdentifier(), number);
            }
        }
    }

    public void pickRandomMessage(View view) {
        String s = SaveDataManager.getRandomMessage();
        TextView textView = (TextView) findViewById(R.id.message_textView);
        textView.setText(s);
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(SaveDataManager.getMessageIdentifier(), s).commit();
    }

    public void pickRandomNumber(View view) {
        String s = getRandomPhoneNumber();
        TextView textView = (TextView) findViewById(R.id.number_textView);
        textView.setText(s);
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(SaveDataManager.getNumberIdentifier(), s).commit();
    }
}
