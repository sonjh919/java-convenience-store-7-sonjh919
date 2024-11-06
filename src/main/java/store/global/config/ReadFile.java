package store.global.config;

import static store.global.exception.ExceptionHandler.IOExceptionHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

abstract class ReadFile<T> {
    private static final String DELIMITER = ",";

    public final List<T> read(final String path) {
        return IOExceptionHandler(this::readLines).apply(path);
    }

    private List<T> readLines(String path) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines()
                    .skip(1)  // 첫 번째 줄(헤더)을 건너뜀
                    .map(this::parseLine)
                    .toList();
        }
    }

    private T parseLine(String line) {
        String[] fields = line.split(DELIMITER);
        return createInstance(fields);
    }

    protected abstract T createInstance(String[] fields);
}
