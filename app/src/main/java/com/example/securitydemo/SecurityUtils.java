package com.example.securitydemo;

import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class SecurityUtils {

    private static final String AES_MODE = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 128;

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    public static String encryptData(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_MODE);
        byte[] iv = new byte[IV_SIZE / 8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        
        GCMParameterSpec gcmSpec = new GCMParameterSpec(IV_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);
        
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }
}
