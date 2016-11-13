package com.example.adithya.redbutton;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String saveDataString = SaveDataManager.getSavedData();
    private String defaultMessageString = SaveDataManager.getDefaultMessage();
    private final SmsManager smsManager = SmsManager.getDefault();
    String kevinsPhoneNumber = "4044216036";
//      String kevinsPhoneNumber = "7328613900"; //actually Wen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveDataManager.checkPermissions(MainActivity.this);
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(SaveDataManager.getMessageIdentifier(), SaveDataManager.getDefaultMessage()).commit();
        spe.putString(SaveDataManager.getNumberIdentifier(), kevinsPhoneNumber).commit();
    }

    public void onClickRekt(View v) {
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("message", "Adithya is rekt.").commit();
        System.out.println("onClickRekt being called.");
    }

    public void onClickUnrekt(View v) {
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        String toastString = sp.getString("message", defaultMessageString);
        Toast testToast = Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG);
        testToast.show();
    }

    public void sendRekt(View v) {
        /* Ask user for permission to send SMS */
        /* TODO: check if user actually grant the permission */
        String jaysPhoneNumber = "5713153823";
        String wensPhoneNumber = "7328613900";
        SharedPreferences sp = getSharedPreferences(saveDataString, 0);
        String number = sp.getString(SaveDataManager.getNumberIdentifier(), kevinsPhoneNumber);
        String text = sp.getString(SaveDataManager.getMessageIdentifier(), defaultMessageString);
        smsManager.sendTextMessage(number, null, text, null, null);
    }

    public void openSettings(View v) {
        System.out.println("onClickRekt being called.");

        startActivity(new Intent(MainActivity.this, SettingActivity.class));
    }

}
