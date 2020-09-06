package com.github.asyu.herren.test4;

import com.github.asyu.herren.util.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Test4 {

    private static final int TARGET_NUMBER = 10;

    private static List<List<Integer>> results = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("input : ");
        String input = sc.next();
        List<Integer> numbers = getNumbers(input);
        List<Integer> sumNumbers = new ArrayList<>();
        solution(0, sumNumbers, 0, numbers);

        results.stream().forEach(result -> System.out.println(Arrays.toString(result.toArray())));
        System.out.println(results.size());
    }

    private static void solution(int sum, List<Integer> sumNumbers, int startIndex, List<Integer> numbers) {
        if (sum == TARGET_NUMBER) {
            results.add(new ArrayList<>(sumNumbers));
            return;
        }

        if (sum > TARGET_NUMBER) {
            return;
        }

        for (int i = startIndex; i < numbers.size(); i++) {
            sumNumbers.add(numbers.get(i));
            solution(sum + numbers.get(i), sumNumbers, i + 1, numbers);
            sumNumbers.remove(numbers.get(i));
       }
    }

    private static List<Integer> getNumbers(String input) {
        return Arrays.stream(input.split(""))
                     .filter(NumberUtils::isNumber)
                     .map(NumberUtils::toInt)
                     .filter(number -> (number > 0 && number < 10))
                     .distinct()
                     .sorted()
                     .collect(toList());
    }
}
