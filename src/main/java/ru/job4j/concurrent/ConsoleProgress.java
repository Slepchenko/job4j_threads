package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static final int SIZE = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }

    @Override
    public void run() {
        String[] process = {"\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (index == SIZE) {
                index = 0;
            }
            System.out.print("\rload: " + process[index++]);
        }
    }
}
