package ru.job4j.cuncurrent.synchronization.storage;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public boolean transfer(int amount, User srcUser) {
        boolean result = false;
        int value = srcUser.getAmount() - amount;
        if (value > 0) {
            this.amount += amount;
            srcUser.setAmount(value);
            result = true;
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
