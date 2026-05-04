package com.waddah.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    // ضع أرقامك الـ 15 هنا
    String[] phoneNumbers = {"+967770000001", "+967770000002"}; 
    String message = "تذكير: رسالة آلية يومية من تطبيق د. وضاح";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            sendBulkSMS();
        }
    }

    private void sendBulkSMS() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            for (String number : phoneNumbers) {
                smsManager.sendTextMessage(number, null, message, null, null);
                Thread.sleep(1500); // انتظار ثانية ونصف بين الرسائل
            }
            Toast.makeText(this, "تم إرسال الرسائل لجميع الأشخاص", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "حدث خطأ: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
