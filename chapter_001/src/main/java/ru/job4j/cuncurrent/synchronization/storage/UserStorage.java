package ru.job4j.cuncurrent.synchronization.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@ThreadSafe
public class UserStorage {
    private List<User> list = new CopyOnWriteArrayList<>();

    public boolean add(User user) {
        return list.add(user);
    }

    private boolean update(User user, int id) {
        boolean result = false;
        int index = IntStream.range(0, list.size()).filter(i -> list.get(i).getId() == id).findFirst().orElse(-1);
        if (index >= 0) {
            user.setId(id);
            list.set(index, user);
            result = true;
        }
        return result;
    }

    private boolean delete(User user) {
        return list.remove(user);
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = getUser(fromId);
        User to = getUser(toId);
        if (from != null && to != null) {
            result = to.transfer(amount, from);
            }
        return result;
    }

    public User getUser(int id) {
        return list.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

}
