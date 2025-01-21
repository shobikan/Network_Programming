package com.np.Multitreading;

public  class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread is running");
        for (int i = 0; i < 100; i++) {
            System.out.println("Number : " + i);
        }
    }
}