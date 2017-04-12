package ru.mipt.restaurant.server.utils;

import org.junit.Assert;
import org.junit.Test;
import ru.mipt.restaurant.server.security.CustomPasswordEncoder;

public class CustomPasswordEncoderTest {

    private final CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();

    @Test
    public void testEncrypt() throws Exception {
        Assert.assertEquals(128, passwordEncoder.encode("password").length());
    }

    @Test
    public void testEquals() throws Exception {
        String originalPassword = "password";
        String encryptedPassword = new CustomPasswordEncoder().encode(originalPassword);

        Assert.assertTrue(passwordEncoder.matches(originalPassword, encryptedPassword));
    }
}