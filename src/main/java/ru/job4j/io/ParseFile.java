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

    public void saveContent(String content) {
        SaveContent saveContent = new SaveContent(file, content);
        saveContent.save();
    }

    public void getContent() {
        GetContent getContent = new GetContent(file);
        System.out.println(getContent.content(filter));
    }
}
