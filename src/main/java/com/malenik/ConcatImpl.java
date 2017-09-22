package com.malenik;

import java.io.IOException;
import java.util.*;

public class ConcatImpl implements Concat {

    // We use Optional because a list of lines which we need to process might be empty
    private Optional<String> firstLongest = Optional.empty();
    private Optional<String> secondLongest = Optional.empty();
    private int count = 0;

    public void process(List<String> sortedLines) throws IOException {
        for (final String word : sortedLines) {
            if (isConcatenatedWord(word, sortedLines)) {
                count++;
                if (word.length() > firstLongest.map(String::length).orElse(Integer.MIN_VALUE)) {
                    secondLongest = firstLongest;
                    firstLongest = Optional.of(word);
                } else if (word.length() > secondLongest.map(String::length).orElse(Integer.MIN_VALUE)) {
                    secondLongest = Optional.of(word);
                }
            }
        }

    }

    // To increase computation speed, we use a binary search, because the list of lines is sorted
    private boolean isConcatenatedWord(String word, List<String> sortedLines) {
        for (int i = 1; i < word.length() - 1; i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (Collections.binarySearch(sortedLines, prefix) >= 0) {
                if (Collections.binarySearch(sortedLines, suffix) >= 0) return true;
                if (isConcatenatedWord(suffix, sortedLines)) return true;
            }
        }
        return false;
    }

    public Optional<String> getFirstLongest() {
        return firstLongest;
    }

    public Optional<String> getSecondLongest() {
        return secondLongest;
    }

    public int getCount() {
        return count;
    }

}
