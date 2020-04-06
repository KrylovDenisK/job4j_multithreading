package ru.job4j.cuncurrent;

public class CuncurrentOutput {
    public Thread getThread() {
        return new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
    }
    public static void main(String[] args) {
        CuncurrentOutput cuncurrentOutput = new CuncurrentOutput();
        Thread another = cuncurrentOutput.getThread();
        Thread second = cuncurrentOutput.getThread();
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}