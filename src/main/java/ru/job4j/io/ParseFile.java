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

    public void getSimpleContent() {
        GetContent getContent = new GetContent(file);
        Predicate<Character> predicate = (c) -> true;
        System.out.println(getContent.content(predicate));
    }

    public void getContentWithoutUnicode() {
        GetContent getContent = new GetContent(file);
        Predicate<Character> predicate = (c) -> c < 0x80;
        System.out.println(getContent.content(predicate));
    }
}
