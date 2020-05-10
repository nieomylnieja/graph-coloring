package graph.coloring.dimacs;

import java.util.*;

public class Painter {
    static private Map<Integer, List<Integer>> graph;
    static private Map<Integer, Integer> colors;

    public Painter(Map<Integer, List<Integer>> incomingGraph) {
        graph = incomingGraph;
        colors = new HashMap<>();
        for (var vertex : graph.keySet()) {
            colors.put(vertex, 0);
        }

        for (var vertex : graph.keySet()){
            greedyColor(vertex);
        }
        showColors();
        isEveryColored();
    }

    private void showColors(){
        for (var vertex : colors.keySet()){
            System.out.println("V:"+vertex+" C:"+colors.get(vertex));
        }
    }

    private static void greedyColor(Integer vertex){
        int n = graph.size();
        var used = new ArrayList<Boolean>();
        for (var j = 0 ; j < n; j++) {
            used.add(false);
        }

        for (var neighbor : graph.get(vertex)) {
            if (!colors.get(neighbor).equals(0)) {
                used.set(colors.get(neighbor), true);
            }
        }
        for (int i = 1; i <= n; i++){
            if (!used.get(i)){
                colors.replace(vertex, i);
                break;
            }
        }
    }

    private static void isEveryColored(){
        int max = 0;
        for (var value : colors.values()){
            if(value.equals(0)){
                System.out.println("Some vertex didn't colored!");
            }

            if (value > max) {
                max = value;
            }
        }
        System.out.println("There is used "+max+" colors");
    }
    


}
