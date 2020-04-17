package com.spbstu.daniilxt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.spbstu.daniilxt.Main.isPrime;
//import static com.spbstu.daniilxt.Main.Prime;

class MainTest {

  @Test
  void testPrime() {
    assertTrue(isPrime(11));
    assertTrue(isPrime(83));
    assertFalse(isPrime(8113));

    int count = 0;
    for (int i = 0; i <= 10000000; i++) {
      if (isPrime(i)) {
        count++;
      }
    }
    System.out.println(count);
  }
}
/*
class MainTest {

  @Test
  void testPrime() {
    assertTrue(Prime(11));
    assertTrue(Prime(83));
    assertFalse(Prime(8113));

    int count = 0;
    for (int i = 0; i <= 10000000; i++) {
      if (Prime(i)) {
        count++;
      }
    }
    System.out.println(count);
  }
}
*/
