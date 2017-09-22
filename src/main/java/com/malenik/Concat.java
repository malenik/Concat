package com.malenik;

import java.io.IOException;
import java.util.*;

public interface Concat {

    void process(List<String> sortedLines) throws IOException;

    Optional<String> getFirstLongest();

    Optional<String> getSecondLongest();

    int getCount();
}
