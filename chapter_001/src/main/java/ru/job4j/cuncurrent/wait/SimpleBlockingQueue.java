package ru.job4j.cuncurrent.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int lenght;

    public SimpleBlockingQueue(int lenght) {
        this.lenght = lenght;
    }

    public synchronized void offer(T value)  {
        try {
            while (queue.size() == lenght) {
                this.wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        queue.offer(value);
        this.notify();
    }

    public synchronized T poll() {
        try {
            while (queue.size() == 0) {
                this.wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
    }
        T result = queue.poll();
        this.notify();
        return result;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
