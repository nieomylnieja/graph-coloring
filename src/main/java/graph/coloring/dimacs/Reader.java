package graph.coloring.dimacs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Reader {

    private Reader() {

    }

    public static Map<Integer, List<Integer>> read(String filename) {
        var file = getPath() + filename;
        return readFile(file);
    }

    public static Map<Integer, List<Integer>> read() {
        var file = getDimacsFile(getPath());
        return readFile(file);
    }

    private static Map<Integer, List<Integer>> readFile(String file) {
        var result = new HashMap<Integer, List<Integer>>();

        try (var bf = new BufferedReader(new FileReader(file))) {
            while (bf.ready()) {
                var line = bf.readLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.length() > 2 && (line.startsWith("c") || line.startsWith("p"))) {
                    System.out.println(line.substring(2));
                }
                if (line.startsWith("e")) {
                    var split = line.split("\\s+");
                    if (split.length != 3) {
                        throw new IllegalArgumentException("Line " + line + " is not valid");
                    }
                    var v1 = Integer.parseInt(split[1]);
                    var v2 = Integer.parseInt(split[2]);

                    result.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
                    result.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getDimacsFile(String path) {
        var result = "";
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            List<String> files = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            var ctr = new AtomicInteger();
            files.forEach(f -> {
                        System.out.printf("%s: %s\n", ctr, f);
                        ctr.incrementAndGet();
                    }
            );
            System.out.println("Choose the file by providing correct index number");
            var scan = new Scanner(System.in);
            var i = scan.nextInt();

            result = files.get(i);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getPath() {
        String userDir = System.getProperty("user.dir");
        var path = "instances/";
        if (userDir.endsWith("target")) {
            path = "../" + path;
        }
        return path;
    }
}
