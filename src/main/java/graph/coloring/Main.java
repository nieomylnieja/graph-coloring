package graph.coloring;

import graph.coloring.dimacs.Reader;

import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("graph coloring");

        var res = Reader.read("fpsol2.i.1.col");
        res.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
