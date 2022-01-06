package com.uirise.webapp;

import java.util.Arrays;

public class SandBox {
    public static int[] massiv = new int[]{1,3,8,10,15,0,0,0,0,0};
    public static void main(String[] arg){
        System.out.println(Arrays.binarySearch(massiv, 0,5, 2));
        System.out.println(Arrays.binarySearch(massiv, 0,5, 9));
        System.out.println(Arrays.binarySearch(massiv, 0,5, 5));
        System.out.println(Arrays.binarySearch(massiv, 0,5, 11));
        System.out.println(Arrays.binarySearch(massiv, 0,5, 16));
        System.out.println(Arrays.binarySearch(massiv, 0,9, 6));
        System.out.println(Arrays.binarySearch(massiv, 0,9, 7));
        System.out.println(Arrays.binarySearch(massiv, 0,9, 8));
        System.out.println(Arrays.binarySearch(massiv, 0,9, 9));
        System.out.println(Arrays.binarySearch(massiv, 0,9, 10));
    }

}
