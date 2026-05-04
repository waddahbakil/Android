package com.waddah.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    // قائمة الـ 15 رقماً (قم بتعديلها هنا)
    String[] phoneNumbers = {"00967xxxxxxx1", "00967xxxxxxx2", "00967xxxxxxx15"};
    String message = "رسالة تجريبية من تطبيق الدكتور وضاح";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);

        // طلب إذن الإرسال إذا لم يكن موجوداً
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
                // انتظار بسيط لتجنب ضغط الشبكة
                Thread.sleep(2000); 
            }
            Toast.makeText(this, "تم إرسال الرسائل للجميع", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "خطأ في الإرسال: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
