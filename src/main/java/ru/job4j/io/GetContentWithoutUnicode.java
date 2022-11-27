package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class GetContentWithoutUnicode implements GetContent {
    @Override
    public String content(File file, Predicate<Character> filter) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
}
