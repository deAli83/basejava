package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 2, 3, 3, 5, 5, 8, 9};

        System.out.println(minValue(numbers));
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
        System.out.println(oddOrEven(IntStream.of(numbers).boxed().collect(Collectors.toList()), sum));
    }

    public static int minValue(int[] numbers) {
        return Arrays.stream(numbers).distinct().sorted().reduce((x, y) -> x * 10 + y).getAsInt();
    }

    public static List<Integer> oddOrEven(List<Integer> numbers, int sum) {
        return numbers.stream().filter(x -> (x % 2 != sum % 2)).collect(Collectors.toList());
    }
}