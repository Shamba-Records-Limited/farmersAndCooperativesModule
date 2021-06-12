package com.shabarecords.farmersmodule.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;


public class UtilGenerator {

    public static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,16}$";
    public static String senderCodeRegex = "[0-9]{3,}";
    private static Logger LOGGER = LoggerFactory.getLogger(UtilGenerator.class);
//

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static ZonedDateTime dateFromString(String date) {

        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            LOGGER.error("Error", e);
        }

        return ZonedDateTime.ofInstant(date1.toInstant(),
                ZoneId.of("Africa/Nairobi"));
    }


    public static String getCode(String type, String inputString) {

        int n = 5;
        // chose a Character random from this String
        String inputStringUcase = inputString.trim().toUpperCase().replaceAll(" ", "").concat("123456789");

        // create StringBuffer size of inputString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to inputString variable length
            int index
                    = (int) (inputStringUcase.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(inputStringUcase
                    .charAt(index));
        }

        return String.format("%s%s", type, sb.toString());
    }


    public static String generatePackageId(String clientEmail) {
        // chose a Character random from this String
        String inputStringUcase = clientEmail.trim().toUpperCase().replace("@", "").replaceAll(".", "").concat("123456789");

        // create StringBuffer size of inputString
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between
            // 0 to inputString variable length
            int index
                    = (int) (inputStringUcase.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(inputStringUcase
                    .charAt(index));
        }

        return sb.toString().trim();
    }


    // KEY
    public static String generateKey() {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int len = 64;
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(str.charAt(rnd.nextInt(str.length())));
        }
        return sb.toString();
    }


    public static boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException exception) {
            return false;
        } catch (MalformedURLException exception) {
            return false;
        }

    }


    public static String hashBySha256(final String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}