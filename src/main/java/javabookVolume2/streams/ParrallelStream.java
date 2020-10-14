package javabookVolume2.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class ParrallelStream {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/Users/alex/javaStudy/corejava/gutenberg/alice30.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));

        // wrong
        var shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        // diff & wrong
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        // remedy: group & count
        Map<Integer, Long> shortWordCount = wordList.parallelStream()
                .filter(s -> s.length() < 10)
                .collect(groupingBy(String::length, counting()));

        System.out.println(shortWordCount);

        Map<Integer, List<String>> result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));

        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));

        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
                groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
    }
}
