package com.example.sonartest.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Vulnerability: using weak hash algorithm (MD5)
    public static void generateWeakHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update("password".getBytes());
            byte[] digest = md.digest();
            System.out.println("Generated weak hash: " + new String(digest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Unused method + inefficiency (code smell)
    public static String reverseString(String input) {
        String reversed = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed += input.charAt(i);
        }
        return reversed;
    }
}
