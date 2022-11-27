package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class LoadFile {
    private GetContent getContent;
    private Predicate<Character> filter;

    public LoadFile(GetContent getContent, Predicate<Character> filter) {
        this.getContent = getContent;
        this.filter = filter;
    }

    public String getContent(File file) {
       return getContent.content(file,filter);
    }
}
