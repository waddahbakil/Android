package com.waddah.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    // ضع أرقام الـ 15 شخص هنا بدلاً من الأرقام الوهمية
    String[] phoneNumbers = {
        "+967770000001", "+967770000002", "+967770000003", 
        "+967770000004", "+967770000005", "+967770000006",
        "+967770000007", "+967770000008", "+967770000009",
        "+967770000010", "+967770000011", "+967770000012",
        "+967770000013", "+967770000014", "+967770000015"
    };
    
    String message = "السلام عليكم، هذه رسالة يومية مجدولة.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // زر الإرسال سيعمل فور فتح التطبيق
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
                Thread.sleep(2000); // تأخير ثانيتين بين كل رسالة
            }
            Toast.makeText(this, "تم الإرسال للجميع بنجاح", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "فشل الإرسال: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
