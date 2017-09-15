package com.malenik;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class ConcatImpl implements Concat {

    private Optional<String> firstLongest = Optional.empty();
    private Optional<String> secondLongest = Optional.empty();
    private int count;

    public void process(Set<String> lines) throws IOException {
        Optional<String> first = Optional.empty();
        Optional<String> second = Optional.empty();
        int count = 0;
        for (final String head : lines) {
            if (isConcatenatedWord(head, lines)) {
                count++;
                if (head.length() > first.map(String::length).orElse(Integer.MIN_VALUE)) {
                    second = first;
                    first = Optional.of(head);
                } else if (head.length() > second.map(String::length).orElse(Integer.MIN_VALUE)) {
                    second = Optional.of(head);
                }
            }
        }

        this.firstLongest = first;
        this.secondLongest = second;
        this.count = count;
    }

    private boolean isConcatenatedWord(String word, Set<String> lines) {
        return isConcatenatedWord("", word, word, lines, false);
    }

    private boolean isConcatenatedWord(String left, String right, String word, Set<String> lines, boolean acc) {
        if (right.isEmpty()) {
            return acc;
        } else {
            String current = left + right.substring(0, 1);
            boolean isConcatenatedPart = lines.contains(current) &&
                    lines.stream().filter(line -> line.equals(current)).anyMatch(line -> word.length() > line.length() && line.contains(current));

            return isConcatenatedPart ?
                    isConcatenatedWord("", right.substring(1), word, lines, true) :
                    isConcatenatedWord(current, right.substring(1), word, lines, false);
        }
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
