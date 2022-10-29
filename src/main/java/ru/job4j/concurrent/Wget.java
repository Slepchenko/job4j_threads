package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            Instant startTime = Instant.now();
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            Instant finishTime = Instant.now();
            FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml");
            long downloadTime = Duration.between(startTime, finishTime).toMillis();
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                try {
                    if (downloadTime < speed) {
                        Thread.sleep(speed - downloadTime);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            in.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
