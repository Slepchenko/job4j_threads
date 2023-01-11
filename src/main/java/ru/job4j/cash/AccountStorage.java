package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return account != null && accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), getById(account.id()).get(), account);
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromAccountOptional = getById(fromId);
        Optional<Account> toAccountOptional = getById(toId);
        if (fromAccountOptional.isEmpty() && toAccountOptional.isEmpty()) {
            return false;
        }
        if (amount > fromAccountOptional.get().amount() || amount < 0) {
            return false;
        }
        accounts.replace(fromId, new Account(fromId, fromAccountOptional.get().amount() - amount));
        accounts.replace(toId, new Account(toId, toAccountOptional.get().amount() + amount));
        return true;
    }
}
