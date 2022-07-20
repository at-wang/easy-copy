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
        int i3 = random.nextInt(9) + 1;
        date.append("to_date(").append("'20").append(i2).append(i).append("-")
                .append("0").append(i3).append("-").append(i2).append(i3).append("'").append(",'YYYY-MM-dd')");
        return date;
    }

    public static String getStringDate() {
        StringBuilder date = new StringBuilder();
        Random random = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        Random random4 = new Random();
        int i = random.nextInt(10);
        int i2 = random2.nextInt(3);
        int i3 = random3.nextInt(9) + 1;
        int i4 = random4.nextInt(2) + 1;
        if (i4==1){
            date.append(i4).append("9").append(i2).append(i).append("-")
                    .append("0").append(i3).append("-").append(i2).append(i3);
            return date.toString();
        }
        date.append(i4).append("0").append(i2).append(i).append("-")
                .append("0").append(i3).append("-").append(i2).append(i3);
        return date.toString();
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

    public static int getFourNumber() {
        return new Random().nextInt(5000) + new Random().nextInt(5000);
    }

    public static StringBuilder getVarChar() {
        StringBuilder stringBuilder = new StringBuilder();
        UUID uuid = UUID.randomUUID();
        stringBuilder.append(uuid.toString().substring(11, 21));
        return stringBuilder;
    }

    public static String getVarchar(int length) {
        if (length <= 3) {
            Random random = new Random();
            StringBuilder stringBuilder = new StringBuilder();
            String s = "1234567890abcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < 3; i++) {
                stringBuilder.append(s.charAt(random.nextInt(s.length())));
            }
            return stringBuilder.toString();
        }else if ( length < 32){
            Random random = new Random();
            StringBuilder stringBuilder = new StringBuilder();
            String s = "1234567890abcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < length; i++) {
                stringBuilder.append(s.charAt(random.nextInt(s.length())));
            }
            return stringBuilder.toString();
        }else {
            String uuid = UUID.randomUUID().toString();
            String s = uuid.replaceAll("-", "");
            return s;
        }
    }
}
