package ru.job4j.nonblockingalgoritm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CASCountTest {
    @Test
    public void whenIncrement100TimesBy2Threads() throws InterruptedException {
    CASCount casCount = new CASCount();
        Thread t1 = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 50; i++) {
                        casCount.increment();
                    }
                }
        );
        t1.start();
        t2.start();
        Thread.sleep(3000);
        assertEquals(100, casCount.get());
    }
}