package ru.job4j.cuncurrent;

public class TreadState {
    public Thread getThread() {
        return new Thread(
                () -> { });
    }

    public static void main(String[] args) {
        TreadState treadState = new TreadState();
        Thread first = treadState.getThread();
        Thread second = treadState.getThread();
        System.out.println(first.getName() + " " + first.getState() + "   "
                + second.getName() + " " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " " + first.getState() + "   "
                    + second.getName() + " " + second.getState());
        }
        System.out.println(first.getState() + "  " + second.getState());
        System.out.println("First and Second threads is completed!");

    }
}
