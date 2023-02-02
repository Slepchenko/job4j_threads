package ru.job4j.blockingqueue;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenEmptyQueue() {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        Thread consumer = new Thread(sbq::poll);
        consumer.start();
    }

    @Test
    public void whenFullQueue() {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(3);
        sbq.offer(1);
        sbq.offer(1);
        sbq.offer(1);

        Thread producer = new Thread(() -> sbq.offer(1));
        producer.start();
    }
}