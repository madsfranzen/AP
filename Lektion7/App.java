public class App {
    public static void main(String[] args) {
        Graph<Integer> graph = new EdgeListGraph<>();
        graph.addVertex(123);
        graph.addVertex(66);
        graph.addVertex(38);
        graph.addVertex(15);
        graph.addVertex(6);
        graph.addEdge(123, 38, 55);
        graph.addEdge(123, 6, 7);
        graph.addEdge(123, 66, 76);
        graph.addEdge(66, 6, 8);
        graph.addEdge(15, 66, 90);
        graph.addEdge(38, 15, 10);
        graph.addEdge(15, 6, 23);
        graph.addEdge(66, 38, 2);

        System.out.println("🔍 Testing DFS from vertex 123:");
        System.out.println(GraphAlgortihms.dfs(graph, 123));

        System.out.println("\n🔍 Testing BFS from vertex 123:");
        System.out.println(GraphAlgortihms.bfs(graph, 123));

        System.out.println("\n🔍 Is graph connected?");
        System.out.println(GraphAlgortihms.connected(graph));

        System.out.println("\n🔍 Is there a path from 123 to 15?");
        System.out.println(GraphAlgortihms.isPath(graph, 123, 15));

        System.out.println("\n🔍 Finding Minimum Spanning Tree:");
        System.out.println(GraphAlgortihms.mst(graph));

        System.out.println("\n🔍 Dijkstra's algorithm from vertex 123:");
        System.out.println(GraphAlgortihms.dijkstra(graph, 123));
    }
}
