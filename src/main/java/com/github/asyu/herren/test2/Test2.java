package com.github.asyu.herren.test2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {

    private static final Pattern KOREAN_PATTERN = Pattern.compile("^[가-힣]*$");

    private static final char START_KOREAN_UNICODE = 0xAC00;

    final static char[] KOREAN_INITIALS = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ',
                                           'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    final static char[] KOREAN_MIDDLES = {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ' , 'ㅖ', 'ㅗ', 'ㅘ',
                                          'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ' ,'ㅡ', 'ㅢ', 'ㅣ'};

    final static char[] KOREAN_FINALS = {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ',
                                         'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ',
                                         'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("input : ");
        String input = sc.next();

        if(isKorean(input) == false) {
            throw new IllegalArgumentException("input string must be korean.");
        }

        System.out.println(getInitials(input));
        //System.out.println(getMiddels(input));
        //System.out.println(getFinals(input));
    }

    private static String getInitials(String  input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int codePoint = input.codePointAt(i);
            int initialsIndex = (codePoint - START_KOREAN_UNICODE) / (KOREAN_MIDDLES.length * KOREAN_FINALS.length);
            result.append(KOREAN_INITIALS[initialsIndex]);
        }
        return result.toString();
    }

    private static String getMiddels(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int codePoint = input.codePointAt(i);
            int middlesIndex = ((codePoint - START_KOREAN_UNICODE) % (KOREAN_MIDDLES.length * KOREAN_FINALS.length)) / KOREAN_FINALS.length;
            result.append(KOREAN_MIDDLES[middlesIndex]);
        }
        return result.toString();
    }

    private static String getFinals(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int codePoint = input.codePointAt(i);
            int finalIndex = (codePoint - START_KOREAN_UNICODE) % KOREAN_FINALS.length;
            result.append(KOREAN_FINALS[finalIndex]);
        }
        return result.toString();
    }


    private static boolean isKorean(String input) {
        char[] chars = input.toCharArray();
        for (char c: chars) {
            int range = c - START_KOREAN_UNICODE;
            if(range >= 0 && range <= 11184) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isKoreanUseRegex(String input) {
        Matcher matcher = KOREAN_PATTERN.matcher(input);
        if(matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
