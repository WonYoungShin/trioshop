package com.trioshop.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@NoArgsConstructor
public class PasswordHashing {

    private static final String salt = "978b29efa6c46ac895df0a832a374286b6ce281a";

    public String hashing(String pw) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((pw + salt).getBytes());
            byte[] pwSalt = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : pwSalt) {
                sb.append(String.format("%02x", b));
            }

            pw = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return pw;
    }
}
