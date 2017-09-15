package com.malenik;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ConcatApp {
    public static void main(String[] args) throws IOException {
        // TODO нужно указать правильный путь к файлу
        Set<String> lines = Files.lines(
                Paths.get("C://Users/NIK/Desktop/words.txt"),
                Charset.forName("UTF-8")).collect(Collectors.toCollection(HashSet::new)
        );
        Concat concat = new ConcatImpl();
        concat.process(lines);
        System.out.println("First: " + concat.getFirstLongest());
        System.out.println("Second: " + concat.getSecondLongest());
        System.out.println("Count: " + concat.getCount());
    }
}
