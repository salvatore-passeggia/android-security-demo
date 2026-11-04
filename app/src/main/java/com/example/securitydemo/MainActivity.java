package com.example.securitydemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private Button checkBtn;
    private static final String TAG = "SecurityDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        checkBtn = findViewById(R.id.checkBtn);

        checkBtn.setOnClickListener(v -> {
            try {
                statusText.setText("Generating secure key...");
                SecretKey key = SecurityUtils.generateKey();
                String encrypted = SecurityUtils.encryptData("Sensitive Banking Data", key);
                statusText.setText("✅ Encryption Successful!\nData secured with AES-GCM.");
                Log.d(TAG, "Encryption test passed.");
            } catch (Exception e) {
                statusText.setText("❌ Error: " + e.getMessage());
                Log.e(TAG, "Encryption failed", e);
            }
        });
    }
}
