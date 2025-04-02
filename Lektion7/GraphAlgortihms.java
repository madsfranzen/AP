import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GraphAlgortihms {
    /**
     * Returnerer en liste af grafens knuder fundet ved et dybddeførst gennemløb af
     * grafen med startknude v.
     */
    public static <V> List<V> dfs(Graph<V> graph, V v) {
        List<V> visited = new ArrayList<>();
        dfs(graph, v, visited);
        return visited;
    }

    private static <V> void dfs(Graph<V> graph, V v, List<V> visited) {
        visited.add(v);
        for (V neighbor : graph.neighbors(v)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited);
            }
        }
    }

    /**
     * Returnerer en liste af grafens knuder fundet ved et breddeførst gennemløb af
     * grafen med startknude v.
     */
    public static <V> List<V> bfs(Graph<V> graph, V v) {
        List<V> visited = new ArrayList<>();
        List<V> queue = new ArrayList<>();
        queue.add(v);
        bfs(graph, v, visited, queue);
        return visited;
    }

    private static <V> void bfs(Graph<V> graph, V v, List<V> visited, List<V> queue) {
        while (!queue.isEmpty()) {
            visited.add(queue.getFirst());
            for (V neighbor : graph.neighbors(queue.getFirst())) {
                if (!visited.contains(neighbor) && !queue.contains(neighbor)) {
                    queue.addLast(neighbor);
                }
            }
            queue.remove(0);
        }
    }

    /**
     * Returnerer om grafen er sammenhængende Pre: grafen er ikke tom
     */
    public static <V> boolean connected(Graph<V> graph) {
        return dfs(graph, graph.vertices().getFirst()).size() == graph.numVertices();
    }

    /**
     * Returnerer om der er en vej fra v1 til v2 i graph
     */
    public static <V> boolean isPath(Graph<V> graph, V v1, V v2) {
        return dfs(graph, v1).contains(v2);
    }

    /**
     * Returnerer en mængde af grafens kanter der udgør det letteste udspændende træ
     * for grafen. Grafen er en simpel vægtet graf
     */
    public static <V> Set<Edge<V>> mst(Graph<V> graph) {
        DisjointSet<V> disjointSet = new GraphAlgortihms().new DisjointSet<>(graph);
        Set<Edge<V>> mst = new HashSet<>();
        List<Edge<V>> edges = new ArrayList<>(graph.edges());
        edges.sort(Comparator.comparingInt(e -> e.getElement()));

        for (Edge<V> edge : edges) {
            V root1 = disjointSet.find(edge.getV());
            V root2 = disjointSet.find(edge.getU());

            if (root1 != root2) {
                disjointSet.union(edge.getV(), edge.getU());
                mst.add(edge);
            }

            if (mst.size() == graph.numVertices() - 1) {
                break;
            }
        }

        return mst;
    }

    public class DisjointSet<V> {
        private Map<V, V> parent;
        private Map<V, Integer> rank;

        public DisjointSet(Graph<V> graph) {
            parent = new HashMap<>();
            rank = new HashMap<>();

            for (V v : graph.vertices()) {
                parent.put(v, v);
                rank.put(v, 0);
            }
        }

        public V find(V v) {
            if (parent.get(v) != v) {
                parent.put(v, find(parent.get(v)));
            }
            return parent.get(v);
        }

        public void union(V v1, V v2) {
            V root1 = find(v1);
            V root2 = find(v2);

            if (root1 != root2) {
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }

    public static <V> Map<V, Integer> dijkstra(Graph<V> graph, V vStart) {
        Map<V, Integer> distance = new HashMap<>();
        PriorityQueue<V> ptq = new PriorityQueue<>((v1, v2) -> Integer.compare(distance.get(v1), distance.get(v2)));

        for (V vertex : graph.vertices()) {
            distance.put(vertex, Integer.MAX_VALUE);
            ptq.offer(vertex);
        }

        distance.put(vStart, 0);

        while (!ptq.isEmpty()) {
            V u = ptq.poll();
            for (V z : graph.neighbors(u)) {
                if (ptq.contains(z)) {
                    int weight = getEdgeWeight(graph, u, z);
                    if (distance.get(u) + weight < distance.get(z)) {
                        distance.put(z, distance.get(u) + weight);
                        ptq.remove(z);
                        ptq.offer(z);
                    }
                }
            }
        }

        // we don't need starting vertex in resultset
        distance.remove(vStart);
        return distance;
    }

    private static <V> int getEdgeWeight(Graph<V> graph, V v1, V v2) {
        int weight = Integer.MAX_VALUE;
        for (Edge<V> edge : graph.incidentEdges(v1)) {
            if (edge.getU().equals(v2) || edge.getV().equals(v2)) {
                weight = edge.getElement();
            }
        }
        return weight;
    }
}