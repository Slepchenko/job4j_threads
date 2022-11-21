package ru.job4j.io;

import java.io.*;

public class Load {
    public String getContent(File file) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public String getContentWithoutUnicode(File file){
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
