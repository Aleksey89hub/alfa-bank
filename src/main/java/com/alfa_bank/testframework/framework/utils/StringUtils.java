package com.alfa_bank.testframework.framework.utils;

import io.qameta.allure.Step;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static final String COMMA_AND_SPACE_DELIMITER = ", ";
    public static final String UNDERSCORE = "_";
    public static final String EMPTY = "";
    public static final String FORMAT_AMOUNT_PATTERN = "%.2f";
    public static final String SEMICOLON = ";";

    private static final Random RANDOM = new Random();

    public static String generateRandomString(int length, String characters) {
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = generateRandomCharacter(characters, RANDOM);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    private static char generateRandomCharacter(String regex, Random random) {
        while (true) {
            char randomChar = (char) (random.nextInt(128));
            Matcher matcher = Pattern.compile(regex).matcher(String.valueOf(randomChar));
            if (matcher.matches()) {
                return randomChar;
            }
        }
    }

    @Step
    public static String randomizeCapitalization(String input) {
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (RANDOM.nextBoolean()) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
        }

        return new String(charArray);
    }
}