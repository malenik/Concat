package com.malenik;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConcatApp {

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.lines(
                Paths.get("./src/main/resources/words.txt"),
                Charset.forName("UTF-8")).collect(Collectors.toList()
        );

        Concat concat = new ConcatImpl();
        concat.process(lines);

        long start = System.currentTimeMillis();
        System.out.println("First: " + concat.getFirstLongest() + ". Length: " + concat.getFirstLongest().map(String::length));
        System.out.println("Second: " + concat.getSecondLongest() + ". Length: " + concat.getSecondLongest().map(String::length));
        System.out.println("Count: " + concat.getCount());
        long finish = System.currentTimeMillis();
        System.out.println("Processing time: " + (finish - start) + " ms");
    }
}
