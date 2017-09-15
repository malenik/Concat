package com.malenik;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public interface Concat {

    void process(Set<String> lines) throws IOException;

    Optional<String> getFirstLongest();

    Optional<String> getSecondLongest();

    int getCount();
}
