package com.github.asyu.herren.test1;

import com.github.asyu.herren.util.NumberUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Test1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("input array size : ");
        int arraySize = sc.nextInt();

        System.out.print("input numbers separated by ',' : ");
        String data = sc.next();
        String[] stringNumbers = data.split(",");

        if(!validateSize(arraySize, stringNumbers.length) || !isNumbers(stringNumbers)) {
            throw new IllegalArgumentException();
        }

        IntStream intStream = Stream.of(stringNumbers).mapToInt(NumberUtils::toInt);
        String result = getResult(intStream.boxed().sorted(Comparator.reverseOrder()).collect(toList()));
        System.out.println(result);
    }

    private static boolean validateSize(int arraySize, int numbersSize) {
        if(numbersSize != arraySize) {
            System.err.println("invalid input data! check your data");
            System.err.format("array size : %d, numbers size : %d", arraySize, numbersSize);
            return false;
        }
        return true;
    }

    private static boolean isNumbers(String[] stringNumbers) {
        return Stream.of(stringNumbers).allMatch(NumberUtils::isNumber);
    }

    private static String getResult(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0, size = numbers.size(); i < size; i++) {
            if(i + 1 == size) {
                sb.append(numbers.get(i));
            } else {
                sb.append(numbers.get(i) + ",");
            }
        }
        return sb.toString();
    }
}
