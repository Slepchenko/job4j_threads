package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {

    private final File file;

    private final Predicate<Character> filter;

    public ParseFile(File file, Predicate<Character> filter) {
        this.file = file;
        this.filter = filter;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
    public void getContent(GetContent getContent) {
        LoadFile loadFile = new LoadFile(getContent, filter);
        loadFile.getContent(file);
    }

}
