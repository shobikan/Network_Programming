package com.np.Multitreading;

public class Thread_Demo {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread(); // First thread
        MyThread thread2 = new MyThread(); // Second thread

//        thread1.start(); // Start first thread
//        thread2.start(); // Start second thread

        Thread thread3 = new Thread(new MyRunnable());
        Thread thread4 = new Thread(new MyRunnable());

//        thread3.start();
//        thread4.start();

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Shobi");
            }
        });
        Thread thread6 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Kan");
            }
        });

        thread5.start();
        thread6.start();
    }
}