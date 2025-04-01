import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyMatrixGraph<V> implements Graph<V> {

    private Map<V, Integer> vertices = new HashMap<V, Integer>(); // Store vertices with index as value


    private Edge<V>[][] matrix;
    private int vertexNr; // Next vertex index to use
    private int numEdges;  // Number og edges in the Graph

    private static final int N = 15;

    /**
     * Construct an empty graph
     */
    public AdjacencyMatrixGraph() {
        matrix = (Edge<V>[][]) new Edge[N][N];
        vertexNr = 0;

    }

    @Override
    /** Return the number of vertices in the graph */
    public int numVertices() {
        return vertices.size();
    }

    @Override
    /** Return the number of edges in the graph */
    public int numEdges() {
        return numEdges;
    }

    @Override
    /** Return a list with the vertices in the graph. */
    public List<V> vertices() {
        return new ArrayList<>(vertices.keySet());
    }

    @Override
    /** Return a list with the edges in the graph. */
    public List<Edge<V>> edges() {
        List<Edge<V>> toReturn = new ArrayList<>();
        for (int i = 0; i < vertexNr; i++) {
            for (int j = i + 1; j < vertexNr; j++) {
                if (matrix[i][j] != null) {
                    toReturn.add(matrix[i][j]);
                }
            }
        }
        return toReturn;
    }


    @Override
    /**
     * Return a list with the neighbors of the specified vertex.
     * Pre: The vertex is in the graph.
     */
    public List<V> neighbors(V v) {
        // TODO
        return null;
    }


    @Override
    /**
     * Return the incident edges to the specified vertex.
     * Pre: The vertex is in the graph.
     */
    public List<Edge<V>> incidentEdges(V v) {
        //TODO
        return null;
    }

    @Override
    /**
     * Return the degree for the specified vertex.
     * Pre: The vertex is in the graph.
     */
    public int degree(V v) {
        // TODO
        return -1;

    }

    @Override
    /**
     * Return true, if the specified vertices are neighbors.
     * Pre: The vertices are vertices in the graph.
     */
    public boolean areAdjacent(V v, V u) {
        // TODO
        return false;
    }

    @Override
    /** Print the vertices and the edges. */
    public void printGraph() {
        // TODO
    }


    @Override
    /**
     * Add a vertex to the graph.
     * Pre: The vertex is not in the graph before this addition.     */
    public void addVertex(V v) {
        // TODO
    }

    @Override
    /**
     * Add an edge with weight 0 between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     */
    public void addEdge(V v, V u) {
        // TODO
    }

    @Override
    /**
     * Add an edge with the specified weight between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     * Pre: The weight is not negative.
     */
    public void addEdge(V v, V u, int e) {
        //TODO
    }

    @Override
    /**
     * Remove the specified vertex from the graph.
     * Pre: The vertex is in the graph
     */
    public void removeVertex(V v) {
        //TODO
    }

    @Override
    /**
     * Remove the edge between the specified vertices from the graph.
     * Pre: The vertices are vertices in the graph,
     *   and The graph has an edge between the vertices.
     */
    public void removeEdge(V v, V u) {
//       TODO

    }




}

