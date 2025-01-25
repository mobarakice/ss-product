package com.sweetsavvy.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.stream.Collectors;

public class XorEncryption {
    public static String xorString(String input, int key) {
        return input.chars()
                .mapToObj(c -> (char) (c ^ key))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static long getXorKey(long random) {
        return random % 255;
    }

    record TnToken(long expires, long random, String sign) {
    }

    public static void main(String[] args) throws JsonProcessingException {
        var packageName = "com.foodibd.android";
        var instant = Instant.now();
        long random = instant.getEpochSecond();
        System.out.println("random: " + random);
        var expires = instant.plus(30, ChronoUnit.MINUTES).getEpochSecond();
        System.out.println("expires: " + expires);
        var key = getXorKey(random);
        System.out.println("xor_key: " + key);


        // Encrypt the string
        var xorText = xorString(packageName, (int) key);
        System.out.println("xor_text: " + xorText);
        var encrypted = Base64.getEncoder()
                .encodeToString(xorText.getBytes());

        System.out.println("Encrypted: " + encrypted);

        var decode = new String(Base64.getDecoder().decode(encrypted));
        System.out.println("decoded: " + decode);
        var ori = xorString(decode, (int) key);
        System.out.println("orinal: " + ori);

        var token = Base64.getEncoder().encodeToString(Base64.getEncoder().encode(new ObjectMapper()
                .writeValueAsBytes(new TnToken(expires, random, encrypted))));
        System.out.println("token: " + token);
    }
}
