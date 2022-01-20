package com.example.RestApi.P1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveStringAndSort {
    public static void main(String[] args) {
        String[] str = new String[100];
        String removeString = "test";
        removeTestStringAndSort(str, removeString);
    }

    private static void removeTestStringAndSort(String[] str, String removeString) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The Size Of Array Is 100.");
        System.out.print("HowMany String do you want to add in array? = ");
        int number = sc.hasNextInt() ? sc.nextInt() : 0;
        if (number > 0 && number <= 100) {
            for (int i = 0; i < number; i++) {
                System.out.print("Enter String " + (i + 1) + " Time = ");
                str[i] = sc.next();
            }
            List<String> stringList = Arrays.asList(str);
            System.out.println("Before Sort = " + stringList.stream().filter(Objects::nonNull).collect(Collectors.joining(" ")));
            stringList = stringList.stream().filter(s -> null != s && !s.equals(removeString)).sorted().collect(Collectors.toList());
            System.out.println("After Removing All test strings and sorted in alphabetically list = " + stringList.stream().sorted().collect(Collectors.joining(" ")));
        } else {
            System.out.println("Please input data in range of 100.");
        }
    }
}
