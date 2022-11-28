package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContent {

    private final File file;

    public GetContent(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}
