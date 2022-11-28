package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveContent {
    private final File file;
    private final String content;

    public SaveContent(File file, String content) {
        this.file = file;
        this.content = content;
    }

    public void save() {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
