package com.marvel.marvelapi.utils;

import java.security.MessageDigest;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Signs {

    private String privateKey = "";

    private String publicKey = "";

    public Signs() {
        this.publicKey = ConstantsUtils.PUBLIC_KEY;
        this.privateKey = ConstantsUtils.PRIVATE_KEY;
    }

    public Map<String, String> generateHash() {
        String hash = "";
        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();

        String body = timestamp + privateKey + publicKey;

        try {
            // Instance MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // String to byte array
            byte[] mdBytes = md.digest(body.getBytes());
            // hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte b : mdBytes) {
                sb.append(String.format("%02x", b));
            }
            hash = sb.toString();
        } catch (Exception e) {
            System.out.println("ERROR: sing error, " + e);
        }
        Map<String, String> signs = new HashMap<>();
        signs.put("hash", hash);
        signs.put("timestamp", String.valueOf(timestamp));
        return signs;
    }

}
