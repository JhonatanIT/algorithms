package com.example.algorithms;

import java.util.*;
import java.util.stream.Stream;

public class Test1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Turing");
    }

    public static void main(String[] args) {

        SubClass bbb = new SubClass();
        bbb.get();

        Test1 aa = new SubClass();
        aa.get();

////        Runtime error
//        Set set = new TreeSet();
//        set.add(1);
//        set.add("3");
//        set.add(5);
//        System.out.println(set);

        //Test try catch and finally
        ExceptionTest exceptionTest = new ExceptionTest();
        try {
            System.out.println(exceptionTest.divide(10, 0));
        } catch (Exception ex) {
            System.out.println("division by 0");
        }

        //Thread test
        Thread thread1 = new Thread(new Test1());
        thread1.start();
//        thread1.start();     //Throw IllegalThreadStateException
        System.out.println(thread1.getState());

        //Comparator Test
        List<Integer> listSort = Stream.of(2, 3, 1).sorted(new Sorting()).toList();
        System.out.println(listSort);
//        listSort.forEach(System.out::println);

        //Abstract class Test
        Dog dog = new Dog();
        dog.fetch();
        dog.wagTail();
        dog.move();
        dog.makeNoise();

        Canine canine = new Dog();
//        canine.fetch();            //canine object can't use own Dog's methods
        canine.wagTail();
        canine.move();
        canine.makeNoise();

        Animal animal = new Dog();
//        animal.fetch();           //animal object can't use own Dog's methods
//        animal.wagTail();         //animal object can't use own Dog's methods
        animal.move();
        animal.makeNoise();

        //Test sort Collections
        String[] strings = new String[]{"abD", "aB", "ABC", "cDe", "CDd"};
        Collections.sort(Arrays.asList(strings), String::compareToIgnoreCase);
        Arrays.stream(strings).forEach(System.out::println);

        //ArrayList y LinkedList support nulls?
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add(null);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(null);

        System.out.println(stringArrayList.toString());
        System.out.println(linkedList.toString());

    }

    private void get() {
        System.out.println("GAA");
    }

    static class Sorting implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }
}


class SubClass extends Test1 {

    public void get() {
        System.out.println("SubClass");
    }
}

class ExceptionTest {

    public Integer divide(int a, int b) {
        try {
            return a / b;
        } finally {
            System.out.println("finally");
        }
    }
}

abstract class Animal {

    public abstract void makeNoise();

    public abstract void move();
}

abstract class Canine extends Animal {

    public void wagTail() {
        System.out.println("Wagging");
    }

    @Override
    public void move() {
        System.out.println("Run");
    }
}

class Dog extends Canine {

    public void fetch() {
        System.out.println("Fetch");
    }

    @Override
    public void makeNoise() {
        System.out.println("Bark");
    }
}
