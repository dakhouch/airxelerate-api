package com.example.airxelerateapi.util;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtil {
    static final String CHARACTERS = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final Random RANDOM = new Random();

    private TestUtil() {
        throw new IllegalStateException("TestUtil private constructor");
    }

    public static String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    public static int getRandomInteger(int min, int max) {
        return min + (int) (RANDOM.nextDouble() * (max - min));
    }
    public static long getRandomLong(long min, long max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        return ThreadLocalRandom.current().nextLong(min, max);
    }
    public static Instant getRandomInstant() {
        SecureRandom random = new SecureRandom();
        long randomOffset = random.nextInt(20_000_000) - 10_000_000;
        return Instant.now().plus(randomOffset, ChronoUnit.SECONDS);
    }

    public static boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }

    public static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        int randomIndex = RANDOM.nextInt(enumConstants.length);
        return enumConstants[randomIndex];
    }


    public static String getRandomFirstname() {
        return getRandomString(15);
    }

    public static String getRandomLastname() {
        return getRandomString(15);
    }

    public static String getRandomEmail() {
        return getRandomString(15) + "@test.ma";
    }

    public static String generateRandomFlightNumber() {
        return getRandomString(4).toUpperCase();
    }
    public static Instant generateRandomFlightDate() {
        return getRandomInstant();
    }
    public static String generateRandomAirlineCode() {
        return getRandomString(2).toUpperCase();
    }
    public static String generateRandomAirportCode() {
        return getRandomString(2).toUpperCase();
    }
    public static Long generateRandomId() {
        return getRandomLong(0,Integer.MAX_VALUE);
    }


}
