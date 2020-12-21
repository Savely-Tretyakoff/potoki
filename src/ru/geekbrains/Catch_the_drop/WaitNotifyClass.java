package ru.geekbrains.Catch_the_drop;

public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyClass wnc = new WaitNotifyClass();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                wnc.printA();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                wnc.printB();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                wnc.printC();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }


    public void printA() {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'A') {
                            monitor.wait();
                        }
                        System.out.print("A");
                        currentLetter = 'B';
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printB () {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'B') {

                            monitor.wait();
                        }
                        System.out.print("B");
                        currentLetter = 'C';
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printC () {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'C') {
                            monitor.wait();
                        }
                        System.out.print("C");
                        currentLetter = 'A';
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


