package com.trioshop.service.user;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordHashing {

    private static final String salt = "978b29efa6c46ac895df0a832a374286b6ce281a";

    public PasswordHashing() {
    }

    public String Hashing(String pw) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((pw + salt).getBytes());
            byte[] pwsalt = md.digest();

            StringBuffer Hashpw = new StringBuffer();
            for (byte b : pwsalt) {
                Hashpw.append(String.format("%02x", b));
            }

            pw = Hashpw.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return pw;
    }
}
