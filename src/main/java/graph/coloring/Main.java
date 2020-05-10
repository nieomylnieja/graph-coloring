package graph.coloring;

import graph.coloring.dimacs.Painter;
import graph.coloring.dimacs.Reader;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("graph coloring");
        Map<Integer, List<Integer>> graph;
        if (args.length > 0 && Objects.equals(args[0], "--debug")) {
            graph = Reader.read("fpsol2.i.1.col");
        } else {
            graph = Reader.read();
        }
        new Painter(graph, 10);
    }
}
