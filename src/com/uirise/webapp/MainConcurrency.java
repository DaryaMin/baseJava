package com.uirise.webapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(mainConcurrency.counter);

        //ДЗ по deadlock
        Map<String, String> bill1 = new HashMap<>();
        bill1.put("id", "uuid1");
        bill1.put("count", "12234");
        Map<String, String> bill2 = new HashMap<>();
        bill2.put("id", "uuid2");
        bill2.put("count", "122345");

        send(bill1, bill2);
        send(bill2, bill1);


    }

    private static void send(Map<String, String> billFrom, Map<String, String> billTo) {
        new Thread(() -> {
            synchronized (billFrom) {
                System.out.println("Перевод с " + billFrom.get("id"));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (billTo) {
                    System.out.println("Перевод на " + billTo.get("id"));
                }
            }
        }).start();
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }
}