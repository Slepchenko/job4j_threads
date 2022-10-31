package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class Wget implements Runnable {

    private static final int SEC = 1000;
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(parser(url))) {

            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            int downloadData = 0;
            Instant start = Instant.now();
            long downloadTime;

            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    Instant finish = Instant.now();
                    downloadTime = Duration.between(start, finish).toMillis();
                    if (downloadTime < SEC) {
                        try {
                            Thread.sleep(SEC - downloadTime);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                start = Instant.now();
                downloadData = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parser(String str) {
        String[] parsPath = str.split("/");
        return parsPath[parsPath.length - 1];
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
