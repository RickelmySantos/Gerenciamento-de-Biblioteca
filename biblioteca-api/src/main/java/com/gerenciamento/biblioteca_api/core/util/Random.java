package com.gerenciamento.biblioteca_api.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Random {
  public static BigDecimal nextBigDecimal(int precision, int scale, boolean onlyPositive) {

    Long maxValue = Long.parseLong("9".repeat(precision));
    Long minValue = onlyPositive ? 0 : maxValue * -1;

    BigInteger value = Random.nextBigInteger(minValue, maxValue + 1L);
    return new BigDecimal(value, scale, new MathContext(precision, RoundingMode.FLOOR));
  }

  public static BigInteger nextBigInteger(Long startInclusive, Long endExclusive) {
    return BigInteger.valueOf(Random.nextLong(startInclusive, endExclusive));
  }

  public static BigDecimal nextBigDecimal(Double startInclusive, Double endExclusive) {
    return BigDecimal.valueOf(Random.nextDouble(startInclusive, endExclusive));
  }

  public static <E> E nextElement(E[] array) {
    return array[Random.nextInt(0, array.length)];
  }

  public static Integer nextInt() {
    return RandomUtils.nextInt();
  }

  public static Integer nextInt(int startInclusive, int endExclusive) {
    return RandomUtils.nextInt(startInclusive, endExclusive);
  }

  public static Long nextLong() {
    return RandomUtils.nextLong();
  }

  public static Long nextLong(long startInclusive, long endExclusive) {
    return RandomUtils.nextLong(startInclusive, endExclusive);
  }

  public static Float nextFloat() {
    return RandomUtils.nextFloat();
  }

  public static Float nextFloat(float startInclusive, float endExclusive) {
    return RandomUtils.nextFloat(startInclusive, endExclusive);
  }

  public static Double nextDouble() {
    return RandomUtils.nextDouble();
  }

  public static Double nextDouble(double startInclusive, double endExclusive) {
    return RandomUtils.nextDouble(startInclusive, endExclusive);
  }

  public static Boolean nextBoolean() {
    return RandomUtils.nextBoolean();
  }

  public static byte[] nextBoolean(int length) {
    return RandomUtils.nextBytes(length);
  }

  public static String nextString(int length) {
    return RandomStringUtils.random(length);
  }

  public static String nextString(int length, char... chars) {
    return RandomStringUtils.random(length, chars);
  }

  public static String nextAlphanumericString(int length) {
    return RandomStringUtils.randomAlphanumeric(length);
  }

  public static String nextAlphabeticString(int length) {
    return RandomStringUtils.randomAlphabetic(length);
  }

  public static String nextNumericString(int length) {
    return RandomStringUtils.randomNumeric(length);
  }

  public static <E extends Enum<E>> E nextEnum(Class<E> enumClass) {
    return Random.nextElement(enumClass.getEnumConstants());
  }

  public static LocalDate nextLocalDate() {

    long minEpochDay = LocalDate.of(1900, 1, 1).toEpochDay();
    long maxEpochDay = LocalDate.of(2100, 12, 31).toEpochDay();


    long randomEpochDay = ThreadLocalRandom.current().nextLong(minEpochDay, maxEpochDay);

    return LocalDate.ofEpochDay(randomEpochDay);
  }


}
