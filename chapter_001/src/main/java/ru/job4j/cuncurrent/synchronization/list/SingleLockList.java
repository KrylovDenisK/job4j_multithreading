package ru.job4j.cuncurrent.synchronization.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private SimpleList<T> simpleList = new SimpleList<>(16);
    private T[] array;
    public synchronized void add(T value) {
        simpleList.add(value);
    }

    public synchronized T get(int index) {
        return simpleList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return List.of(simpleList.toArray()).iterator();
    }
}
