package graph.coloring.dimacs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class Reader {

    private Reader() {

    }

    public static Map<Integer, List<Integer>> read(String filename) {
        var result = new HashMap<Integer, List<Integer>>();

        try (var bf = new BufferedReader(new FileReader("../instances/" + filename))) {
            while (bf.ready()) {
                var line = bf.readLine();
                if (line.startsWith("e") && !line.trim().isEmpty()) {
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
}
