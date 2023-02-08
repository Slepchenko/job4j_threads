package ru.job4j.cache;

public class OptimisticException extends Throwable {
    public OptimisticException(String text) {
        System.out.println(text);
    }
}
