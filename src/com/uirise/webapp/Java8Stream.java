package com.uirise.webapp;

import java.util.*;
import java.util.stream.IntStream;

public class Java8Stream {
    public static void main(String[] args){
//реализовать метод через стрим int minValue(int[] values).
//Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
// Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
        int[] arrayInput = new int[] { 1, 8, 8, 6 };

        List<Integer> integers = new ArrayList<>();
        for (int i: arrayInput) {
            integers.add(i);
        }


        System.out.println(minValue(arrayInput));

        System.out.println(oddOrEven(integers));
    }

    public static int minValue(int[] values) {
        IntStream coll = Arrays.stream(values).distinct().sorted();
        Iterator<Integer> iterator = coll.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result = result*10 + iterator.next();
        }
        return result;
    }
//реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные, если четная - удалить все четные.
// Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
    public static List<Integer> oddOrEven(List<Integer> integers){
        Optional<Integer> sum = integers.stream()
                .reduce(Integer::sum);
        if (sum.get() % 2 == 0) {
            integers.removeIf(n -> (n % 2 == 0));
        } else {
            integers.removeIf(n -> (n % 2 != 0));
        }
        return integers;
    }

}
