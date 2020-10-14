package javabookVolume2.streams.collecting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {

    public static Stream<String> noVowels() throws IOException {
        var contents = new String(Files.readAllBytes(
                Paths.get("/Users/alex/javaStudy/corejava/gutenberg/alice30.txt")),
                StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String lable, Set<T> set) {
        System.out.print(lable + ": " + set.getClass().getName());
        System.out.println("["
            + set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", "))
            + "]");
    }

    public static void main(String[] args) {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
    }
}
