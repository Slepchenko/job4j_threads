package ru.job4j.concurrent;

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public final Node<T> getNEXT() {
        return next;
    }

    public final T getValue() {
        return value;
    }
}
