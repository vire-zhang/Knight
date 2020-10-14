package javabookVolume2.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamSample {

    public static void main(String[] args) throws IOException {

        var contents = new String(Files.readAllBytes(
                Paths.get("/Users/alex/javaStudy/corejava/gutenberg/alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        // filter、map
        Stream<String> longwords = words.stream().filter(w -> w.length() > 12);
        CreatingStream.show("longwords", longwords);
        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        CreatingStream.show("lowercaseWords", lowercaseWords);
        Stream<String> firstLetters = words.stream().skip(1).map(s -> s.substring(0, 1));
        CreatingStream.show("firstLetters", firstLetters);

        // flatMap
        Stream<Stream<String>> result = words.stream().map(w -> codePoints(w)).limit(100);
        CreatingStream.show("result", result);
        Stream<String> flatResult = words.stream().flatMap(w -> codePoints(w));
        CreatingStream.show("flatResult", flatResult);

        // takeWhile、dropWhile、concat
        String str = "   132  d9skjhfkafh2mwbfajeft72ie3782   1i6gjhag78   253612 u5e421i6491";
        Stream<String> initialDigits = codePoints(str).takeWhile(s -> "0123456789".contains(s));
        CreatingStream.show("initialDigits", initialDigits);

        Stream<String> withoutInitialWhiteSpace = codePoints(str).dropWhile(
                s -> s.trim().length() == 0);
        CreatingStream.show("withoutInitialWhiteSpace", withoutInitialWhiteSpace);
        Stream<String> combined = Stream.concat(
                codePoints("Hello"), codePoints("World"));
        CreatingStream.show("combined", combined);

        // distinct、sort、peek
        Stream<String> uniqueWords =  codePoints(str).dropWhile(
                s -> s.trim().length() == 0).distinct();
        CreatingStream.show("uniqueWords", uniqueWords);
        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        CreatingStream.show("longestFirst", longestFirst);

        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetching" + e))
                .limit(20).toArray();
    }

    public static Stream<String> codePoints(String s) {

        var result = new ArrayList<String>();
        int i = 0;
        while (i < s.length()) {
            // 正确处理用两个 char 值表示的 Unicode 字符
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        return result.stream();
    }
}
