package sbu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.
    public static class divisible3 implements Runnable{
        int n;
        int a;
        ArrayList<Integer> divisible = new ArrayList<Integer>();

        public divisible3(int n , int a , ArrayList<Integer> divisibles){
            this.n = n;
            this.divisible = divisible;
            this.a = a;
        }


        @Override
        public void run() {
            for (int i = 1; i < n + 1; i++) {
                if (i % a == 0) {
                    divisible.add(i);
                }
            }
            System.out.println(divisible);
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public int getSum(int n) throws InterruptedException {
        int sum = 0;

        ArrayList<Integer> divisibles = new ArrayList<>();

        divisible3 dev3 = new divisible3(n, 3, divisibles);
        divisible3 dev5 = new divisible3(n, 5, divisibles);
        divisible3 dev7 = new divisible3(n, 7, divisibles);
        Thread thread3 = new Thread(dev3);
        Thread thread5 = new Thread(dev5);
        Thread thread7 = new Thread(dev7);

        thread3.start();
        thread3.join();

        for (int i = 0; i < 1 ; i++) {
            divisibles.addAll(dev3.divisible);
        }

        thread5.start();
        thread5.join();
        for (int i = 0; i < 1; i++) {
            divisibles.addAll(dev5.divisible);
        }

        thread7.start();
        thread7.join();

        for (int i = 0; i < 1; i++) {
            divisibles.addAll(dev7.divisible);
        }

        Set<Integer> set = new HashSet<Integer>(divisibles);
        divisibles.clear();
        divisibles.addAll(set);

//        System.out.println(divisibles);// for test
        for ( int i : divisibles){
            sum += i;
        }
        // TODO
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
    }

}
