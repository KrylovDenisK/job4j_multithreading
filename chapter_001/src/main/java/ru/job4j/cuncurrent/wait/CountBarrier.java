package ru.job4j.cuncurrent.wait;

public class CountBarrier {
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        this.notifyAll();
    }

    public synchronized void await() {
        while (count != total) {
            System.out.println("Wait");
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        int total = 50;
        CountBarrier countBarrier = new CountBarrier(total);
        Thread await = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " complete");
                }
        );
        await.start();
        for (int i = 0; i < total; i++) {
            Thread thread = new Thread(countBarrier::count);
            thread.start();
        }
    }
}
