package graph.coloring.dimacs;

import java.util.*;

public class Painter {
    static private Map<Integer, List<Integer>> graph;
    static private Map<Integer, Integer> colors;
    static private String time;

    public Painter(Map<Integer, List<Integer>> incomingGraph, Integer reps) {
        graph = incomingGraph;
        colors = new HashMap<>();
        graph.keySet().forEach(v -> colors.put(v, 0));
        var tmpTime = 0L;

        for (int i = 0; i < reps; i++) {
            var start = System.nanoTime();
            graph.keySet().forEach(Painter::greedyColor);
            var end = System.nanoTime();
            tmpTime += end - start;

            showColors();
            isEveryColored(tmpTime);
        }
        System.out.println("\n\nTOTAL TIME: " + tmpTime + "ns");
        System.out.println("AVERAGE TIME: " + tmpTime / reps + "ns");
    }

    private void showColors() {
        for (var vertex : colors.keySet()) {
            System.out.println("V:" + vertex + " C:" + colors.get(vertex));
        }
    }

    private static void greedyColor(Integer vertex) {
        var used = new ArrayList<>(Arrays.asList(new Boolean[graph.size()]));
        Collections.fill(used, Boolean.FALSE);

        for (var neighbour : graph.get(vertex)) {
            if (!colors.get(neighbour).equals(0)) {
                used.set(colors.get(neighbour), true);
            }
        }
        for (int i = 1; i <= graph.size(); i++) {
            if (!used.get(i)) {
                colors.replace(vertex, i);
                break;
            }
        }
    }

    private static void isEveryColored(Long time) {
        int max = 0;
        for (var value : colors.values()) {
            if (value.equals(0)) {
                System.out.println("Vertex was not colored!");
            }

            if (value > max) {
                max = value;
            }
        }
        System.out.println(max + " colors were used");
    }
}
