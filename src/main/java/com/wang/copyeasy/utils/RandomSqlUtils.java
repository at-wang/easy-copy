package com.wang.copyeasy.utils;

import java.util.Random;
import java.util.UUID;

public class RandomSqlUtils {
    public static StringBuilder getDate() {
        StringBuilder date = new StringBuilder();
        Random random = new Random();
        Random random2 = new Random();
        int i = random.nextInt(10);
        int i2 = random2.nextInt(3);
        int i3 = random.nextInt(9)+1;
        date.append("to_date(").append("'20").append(i2).append(i).append("-")
                .append("0").append(i3).append("-").append(i2).append(i3).append("'").append(",'YYYY-MM-dd')");
        return date;
    }

    public static int getNumber() {
        Random random = new Random();
        int i = random.nextInt(10);
        Random random2 = new Random();
        int i2 = random.nextInt(10);
        Random random3 = new Random();
        int i3 = random.nextInt(10);
        Random random4 = new Random();
        int i4 = random.nextInt(10);
        Random random5 = new Random();
        int i5 = random.nextInt(10);
        Random random6 = new Random();
        int i6 = random.nextInt(10);
        Random random7 = new Random();
        int i7 = random.nextInt(10);
        return i + i2 + i3 + i4 + i5 + i6 + i7;
    }

    public static StringBuilder getVarChar() {
        StringBuilder stringBuilder = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        stringBuilder.append("'").append(uuid.toString().substring(11, 12)).append("'");
        return stringBuilder;
    }
}
