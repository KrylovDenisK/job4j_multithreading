package ru.job4j.cuncurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        try {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(500);
                String progress = count % 2 == 0 ? " - \\ | / -" : " - / | \\ -";
                System.out.print("\r load: " + progress);
                count++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String...args) throws InterruptedException {
        Thread thread = new Thread(new ConsoleProgress());
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
    }
}
