package ru.mipt.restaurant.server.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(rawPassword.toString().getBytes());
            StringBuilder hexData = new StringBuilder();
            byte[] data = sha512.digest();
            for (byte aData : data) hexData.append(Integer.toString((aData & 0xff) + 0x100, 16).substring(1));

            return hexData.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error has occurred while encrypting password", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
