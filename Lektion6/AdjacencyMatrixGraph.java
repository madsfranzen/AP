import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyMatrixGraph<V> implements Graph<V> {

    private Map<V, Integer> vertices = new HashMap<V, Integer>(); // Store vertices with index as value

    private Edge<V>[][] matrix;
    private int vertexNr; // Next vertex index to use
    private int numEdges; // Number of edges in the Graph

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
     * Return a list with the neighbors of the specified vertex. Pre: The vertex is
     * in the graph.
     */
    public List<V> neighbors(V v) {
        List<V> neighbors = new ArrayList<>();
        Integer index = vertices.get(v);

        if (index == null) {
            return neighbors; // Vertex not in graph
        }

        // Check both rows and columns to handle all possible connections
        for (int i = 0; i < vertexNr; i++) {
            // Check row (outgoing edges)
            if (matrix[index][i] != null) {
                // Find the vertex with this index
                for (Map.Entry<V, Integer> entry : vertices.entrySet()) {
                    if (entry.getValue() == i) {
                        neighbors.add(entry.getKey());
                        break;
                    }
                }
            }

            // Check column (incoming edges) if different from row check
            if (i != index && matrix[i][index] != null && matrix[index][i] == null) {
                // Find the vertex with this index
                for (Map.Entry<V, Integer> entry : vertices.entrySet()) {
                    if (entry.getValue() == i) {
                        neighbors.add(entry.getKey());
                        break;
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    /**
     * Return the incident edges to the specified vertex. Pre: The vertex is in the
     * graph.
     */
    public List<Edge<V>> incidentEdges(V v) {
        List<Edge<V>> result = new ArrayList<>();
        Integer index = vertices.get(v);

        if (index == null) {
            return result; // Vertex not in graph
        }

        // Check row (outgoing edges)
        for (int i = 0; i < vertexNr; i++) {
            if (matrix[index][i] != null) {
                result.add(matrix[index][i]);
            }
        }

        // Check column (incoming edges) if different from row check
        for (int i = 0; i < vertexNr; i++) {
            if (i != index && matrix[i][index] != null && matrix[index][i] == null) {
                result.add(matrix[i][index]);
            }
        }

        return result;
    }

    @Override
    /**
     * Return the degree for the specified vertex. Pre: The vertex is in the graph.
     */
    public int degree(V v) {
        return neighbors(v).size();
    }

    @Override
    /**
     * Return true, if the specified vertices are neighbors. Pre: The vertices are
     * vertices in the graph.
     */
    public boolean areAdjacent(V v, V u) {
        Integer indexV = vertices.get(v);
        Integer indexU = vertices.get(u);

        if (indexV == null || indexU == null) {
            return false;
        }

        return matrix[indexV][indexU] != null || matrix[indexU][indexV] != null;
    }

    @Override
    /** Print the vertices and the edges. */
    public void printGraph() {
        System.out.println("Vertices: " + vertices.keySet());
        System.out.println("Edges:");

        for (int i = 0; i < vertexNr; i++) {
            for (int j = 0; j < vertexNr; j++) {
                if (matrix[i][j] != null) {
                    System.out.println(matrix[i][j]);
                }
            }
        }
    }

    @Override
    /**
     * Add a vertex to the graph. Pre: The vertex is not in the graph before this
     * addition.
     */
    public void addVertex(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, vertexNr);
            vertexNr++;
        }
    }

    @Override
    /**
     * Add an edge with weight 0 between the specified vertices to the graph. Pre:
     * Before addition, the vertices are in the graph, and the edge is not in the
     * graph.
     */
    public void addEdge(V v, V u) {
        addEdge(v, u, 0);
    }

    @Override
    /**
     * Add an edge with the specified weight between the specified vertices to the
     * graph. Pre: Before addition, the vertices are in the graph, and the edge is
     * not in the graph. Pre: The weight is not negative.
     */
    public void addEdge(V v, V u, int e) {
        Integer indexV = vertices.get(v);
        Integer indexU = vertices.get(u);

        if (indexV != null && indexU != null && matrix[indexV][indexU] == null) {
            Edge<V> edge = new Edge<>(v, u, e);
            matrix[indexV][indexU] = edge;
            matrix[indexU][indexV] = edge; // Undirected graph - add in both directions
            numEdges++;
        }
    }

    @Override
    /**
     * Remove the specified vertex from the graph. Pre: The vertex is in the graph
     */
    public void removeVertex(V v) {
        Integer index = vertices.get(v);

        if (index == null) {
            return; // Vertex not in graph
        }

        // Remove all edges containing this vertex
        for (int i = 0; i < vertexNr; i++) {
            if (matrix[index][i] != null) {
                matrix[index][i] = null;
                matrix[i][index] = null;
                numEdges--;
            }
        }

        // Remove the vertex from the map
        vertices.remove(v);

        // Need to shift all vertices with higher indices down by 1
        Map<V, Integer> newVertices = new HashMap<>();
        for (Map.Entry<V, Integer> entry : vertices.entrySet()) {
            if (entry.getValue() > index) {
                newVertices.put(entry.getKey(), entry.getValue() - 1);
            } else {
                newVertices.put(entry.getKey(), entry.getValue());
            }
        }
        vertices = newVertices;

        // Need to compact the matrix by removing the row and column
        for (int i = index; i < vertexNr - 1; i++) {
            for (int j = 0; j < vertexNr; j++) {
                matrix[i][j] = matrix[i + 1][j];
            }
        }

        for (int j = index; j < vertexNr - 1; j++) {
            for (int i = 0; i < vertexNr; i++) {
                matrix[i][j] = matrix[i][j + 1];
            }
        }

        // Clear the last row and column
        for (int i = 0; i < vertexNr; i++) {
            matrix[i][vertexNr - 1] = null;
            matrix[vertexNr - 1][i] = null;
        }

        vertexNr--;
    }

    @Override
    /**
     * Remove the edge between the specified vertices from the graph. Pre: The
     * vertices are vertices in the graph, and The graph has an edge between the
     * vertices.
     */
    public void removeEdge(V v, V u) {
        Integer indexV = vertices.get(v);
        Integer indexU = vertices.get(u);

        if (indexV != null && indexU != null) {
            if (matrix[indexV][indexU] != null) {
                matrix[indexV][indexU] = null;
                matrix[indexU][indexV] = null; // Undirected graph - remove in both directions
                numEdges--;
            }
        }
    }

}
