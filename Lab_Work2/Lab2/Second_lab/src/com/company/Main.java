package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Enter your space: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] mass = new int[n];
        for (int i = 0; i < mass.length; i++)
        {
            mass[i] = input.nextInt();
        }

        FindMinimum(mass);
        FindMaximum(mass);
        FindAverage(mass);
        FindMedian(mass);
        FindGeometricAverage(mass);

        System.out.println("Minimum is:" + FindMinimum(mass));
        System.out.println("Maximum is:" + FindMaximum(mass));
        System.out.println("Average is:" + FindAverage(mass));
        System.out.println("Median is:" + FindMedian(mass));
        System.out.println("GeoAverage is:" + FindGeometricAverage(mass));
    }

    private static int FindGeometricAverage(int[] mass) {
        double var = 1.0;
        for (int mas : mass)
        {
            if (mas == 0)
            {
                throw new RuntimeException("Massive elements can not be null!");
            }
            else
            {
                var *= mas;
            }
        }
        return (int) Math.pow(var, 1.0/mass.length);
    }

    private static int FindMedian(int[] mass) {
        Arrays.sort(mass);
        for (int mas : mass)
        {
            if (mas == 0)
            {
                throw new RuntimeException("Massive elements can not be null!");
            }
        }
        return mass[mass.length / 2];
    }

    private static int FindAverage(int[] mass) {
        int sum = 0;
        for (int mas : mass) {
            if (mas == 0)
            {
                throw new RuntimeException("Massive elements can not be null!");
            }
            else
            {
                sum += mas;
            }
        }
        return sum / mass.length;
    }

    private static int FindMaximum(int[] mass) {
        int max = 0;
        for (int mas : mass) {
            if (mas == 0)
            {
                throw new RuntimeException("Massive elements can not be null!");
            }
            else
            {
                max = Math.max(mas, mass[0]);
            }
        }
        return max;
    }

    private static int FindMinimum(int[] mass) {
        int min = 0;
        for (int mas : mass) {
            if (mas == 0)
            {
                throw new RuntimeException("Massive elements can not be null!");
            }
            else
            {
                min = Math.min(mas, mass[0]);
            }
        }
        return min;
    }
}
