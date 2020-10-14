package javabook.concurrence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class ProcessTest {

    public static void main(String[] args) throws IOException {
        var builder = new ProcessBuilder("java", "hello.java");
        Path path = Path.of(".");
        builder = builder.directory(path.toFile());

        Process p = builder.start();
        OutputStream processIn = p.getOutputStream();
        InputStream processOut = p.getInputStream();
        InputStream procrssErr = p.getErrorStream();
    }
}
