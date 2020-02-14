package net.bitpot.railways.contracts;

import java.util.stream.Stream;

public interface RoutesFiles<T> {
    public Stream<T> allFiles();
}
