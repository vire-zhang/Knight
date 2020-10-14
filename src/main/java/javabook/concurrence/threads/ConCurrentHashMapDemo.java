package javabook.concurrence.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConCurrentHashMapDemo {

    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path file) {
        try (var in = new Scanner(file)) {
            while (in.hasNext()) {
                String word = in.next();
                map.merge(word, 1L, Long::sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path pathToRoot = Path.of(".");
        for (Path p : descendants(pathToRoot)) {
            if (p.getFileName().toString().endsWith(".java"))
                executor.execute(() -> process(p));
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        map.forEach((k, v) -> {
            if (v >= 10)
                System.out.println(k + " occurs " + v + " times");
        });
        map.forEach(1, (k, v) -> v > 1000 ? k + " -> " + v : null, System.out::println);
        Long count = map.reduceValues(1, v -> v > 100 ? 1L : null, Long::sum);
        System.out.println("count > 100: " + count);
        Long count2 = map.reduceValues(1, v -> v > 10 ? 1L : null, Long::sum);
        System.out.println("count > 10: " + count2);

        // 并行数组算法
        Set<String> set = map.keySet();
        String[] words = set.toArray(new String[0]);
        Arrays.parallelSort(words, Comparator.comparing(String::length).reversed());

        int[] values = {1,2,3,4,5,6};
        Arrays.parallelPrefix(values, (x, y) -> x * y);
        Arrays.parallelSetAll(values, i -> i % 10);
    }
}
