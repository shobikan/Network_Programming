package com.np.Multitreading;


public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable is running");
        for (int i = 0; i < 100; i++) {
            System.out.println("Number : " + i);
        }
    }
}