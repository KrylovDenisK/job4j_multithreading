package ru.job4j.cuncurrent.synchronization.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class SimpleList<T> implements Iterable<T> {

    private T[] conteiner;
    private int size;
    private int index = 0;
    private int modCount = 0;

    public SimpleList(int size) {
        this.size = size;
        conteiner = (T[]) new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int indexItr = 0;

            @Override
            public boolean hasNext() {
                return indexItr < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return conteiner[indexItr++];
            }
        };
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        conteiner[index++] = value;
        if (index == size) {
            expansionContainer();
            size = conteiner.length;
            modCount++;
        }
    }

    public T get(int index) {
        return conteiner[index];
    }

    private void expansionContainer() {
        T[] newConteiner = (T[]) new Object[(size * 3) / 2 + 1];
        System.arraycopy(conteiner, 0, newConteiner, 0, size);
        conteiner = newConteiner;
    }

    public boolean hasValue(T value) {
        boolean result = false;
        if (index != 0) {
            result = IntStream.range(0, index)
                    .anyMatch(x -> Objects.nonNull(conteiner[x]) ? conteiner[x].equals(value) : conteiner[x] == value);
        }
        return result;
    }

    public T[] toArray() {
        return Arrays.copyOf(conteiner, index);
    }
}