package com.example.adithya.redbutton;

import android.Manifest;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRekt(View v) {
        SharedPreferences sp = getSharedPreferences("rekt", 0);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString("slogan", "Adithya is rekt.").commit();
        System.out.println("onClickRekt being called.");
    }

    public void onClickUnrekt(View v) {
        SharedPreferences sp = getSharedPreferences("rekt", 0);
        String toastString = sp.getString("slogan", "Nothing named slogan was stored");
        Toast testToast = Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG);
        testToast.show();
    }

    public void sendRekt(View v) {
        /* Ask user for permission to send SMS */
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        /* TODO: check if user actually grant the permission */
        SmsManager smsManager = SmsManager.getDefault();
        String jaysPhoneNumber = "5713153823";
        String wensPhoneNumber = "7328613900";
        String text = "Let us unrekt Adithya\n";
        smsManager.sendTextMessage(jaysPhoneNumber, null, text, null, null);
    }


}
