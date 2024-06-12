package com.trioshop.deprecated.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-512 적용  알고리즘
 * 비밀번호 해싱을 위해 작성 되었으나
 * 시큐리티 내 BCrypt 알고리즘 적용으로 인한 비활성화
 */
//@Component
//@NoArgsConstructor
public class PasswordHashing {

    private static final String salt = "978b29efa6c46ac895df0a832a374286b6ce281a";

    public String hashing(String pw) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
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
