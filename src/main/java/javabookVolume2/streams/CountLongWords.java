package javabookVolume2.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CountLongWords {

    public static void main(String[] args) throws IOException {

        var contents = new String(Files.readAllBytes(
                Paths.get("/Users/alex/javaStudy/corejava/gutenberg/alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        Stream<String> words1 = Stream.of(contents.split("\\PL+"));
        Stream<String> words2 = Pattern.compile("\\PL+").splitAsStream(contents);
        Stream<String> words3 = new Scanner(contents).tokens();
        try (Stream<String> lines = Files.lines(
                Paths.get("/Users/alex/javaStudy/corejava/gutenberg/alice30.txt"))) {
            // Process lines
        }

        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        Optional optional = Optional.of(1);
        optional.stream();
    }
}
