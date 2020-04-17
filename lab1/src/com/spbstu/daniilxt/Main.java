package com.spbstu.daniilxt;

import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Main {
  public static void main(String[] args) {
    System.out.println("test \n");
    System.out.println(010==10);
/*
    System.out.println("Please, input range:");

    Scanner in = new Scanner(System.in);
    if (in.hasNextInt()) {
      int input = in.nextInt();
      int input2 = in.nextInt();

      System.out.println("Prime numbers:");
      int count = 0;
      for (int i = input; i <= input2; i++) {

        if (isPrime(i)) {
          System.out.print(i);
          System.out.print(" ");
          count++;
        }
      }
      System.out.println("\nCount of numbers:");
      System.out.println(count);
    } else System.out.println("Error");


    System.out.println("Hello");*/
    //long a = 2315786342149L;
   // long a = 892347384743343L;
    long a = 99999999999999L;
    System.out.println("Position is:" + rec(a));
  }

  public static byte rec(long a) {
    // Без строк и массивов придумал только так...
    long max = Long.MIN_VALUE;
    byte i = 0;
    byte res = 0;
    long b;
    if (a > -10 && a < 10) {
      return 1;
    }

    long c = a;
    while (c > 0) {
      b = c % 10;
      c = c / 10;
      if (b >= max) {
        max = b;
        res = i;
      }
      i++;
    }
    if (i < 4) { // Если число меньше четрыехзначного
      i += 1;
    }
    return (byte) Math.abs(i - res);
  }


  public static boolean isPrime(int num) {
    if (num < 2) return false;
    if (num == 2) return true;
    if (num % 2 == 0) return false;

    for (int i = 3; i <= sqrt(num); i += 2) {
      if (num % i == 0) return false;
    }
    return true;
  }
}
/*
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.Math.sqrt;


public class Main
{
  public static void main (String[] args)
  {

    Scanner scanner = new Scanner(System.in);

    try
    {
      int num1 = scanner.nextInt();
      int num2 = scanner.nextInt();

      if (num1 > num2){
        System.out.println("Invalid interval");
        return;
      }

      if (num2 < 2){                                  //Любое число меньше двух не простое
        System.out.println("Count: 0");
        return;
      }

      int count = 0;

      //
      if (num1 <= 2)
      {
        num1 = 3;
        count++;
        System.out.print("2 ");                      //Служит для исключения четных чисел из заданного промежутка
      }

      if (!Prime(num1))
      {
        num1++;
      }
      //

      for (int i = num1; i <= num2; i += 2){
        if (Prime(i))
        {
          count++;
          System.out.print(i);
          System.out.print(" ");
        }
      }

      System.out.print("\nCount of numbers: ");
      System.out.println(count);
    }
    catch (NoSuchElementException e1)
    {
      System.out.println("Error input");
    }

  }

  public static boolean Prime(int num)
  {
    for (int i = 3; i <= sqrt(num); i += 2)
    {
      if (num % i == 0){
        return false;
      }
    }

    return true;
  }
}
*/
