import java.util.List;

public class App {
    public static void main(String[] args) {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        graph.addVertex(15);
        graph.addVertex(6);
        graph.addVertex(38);
        graph.addVertex(123);
        graph.addVertex(66);

        graph.addEdge(15, 6, 23);
        graph.addEdge(15, 38, 10);
        graph.addEdge(15, 66, 90);
        graph.addEdge(6, 66, 8);
        graph.addEdge(6, 123, 7);
        graph.addEdge(38, 66, 2);
        graph.addEdge(38, 123, 55);
        graph.addEdge(66, 123, 76);
        graph.printGraph();
        System.out.println("\n");
        // System.out.println(graph.degree(6));
        // System.out.println(graph.neighbors(6));
        // System.out.println(graph.incidentEdges(6));

        // System.out.println(getBiggestV(graph));

        // System.out.println(graph.areAdjacent(15, 66));

        graph.removeVertex(6);
        graph.printGraph();

        System.out.println("\n");
        graph.removeEdge(15, 66);
        graph.printGraph();

        // System.out.println(graph.areAdjacent(15, 66));
    }

    private static <V extends Comparable<V>> V getBiggestV(Graph<V> graph) {
        List<V> vertices = graph.vertices();
        V biggestV = vertices.get(0);
        for (V vertex : vertices) {
            biggestV = vertex.compareTo(biggestV) > 0 ? vertex : biggestV;
        }
        return biggestV;
    }
}