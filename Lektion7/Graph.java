import java.util.List;

public interface Graph<V> {

  /** Return the number of vertices in the graph */
  public int numVertices();

  /** Return the number of edges in the graph */
  public int numEdges();

  /** Return a list with the vertices in the graph. */
  public List<V> vertices();

  /** Return a list with the edges in the graph. */
  public List<Edge<V>> edges();

  /**
   * Return a list with the neighbors of the specified vertex.
   * Pre: The vertex is in the graph.
   */
  public List<V> neighbors(V v);

  /**
   * Return the incident edges to the specified vertex.
   * Pre: The vertex is in the graph.
   */
  public List<Edge<V>> incidentEdges(V v);

  /**
   * Return the degree for the specified vertex.
   * Pre: The vertex is in the graph.
   */
  public int degree(V v);

  /**
   * Return true, if the specified vertices are neighbors.
   * Pre: The vertices are vertices in the graph.
   */
  public boolean areAdjacent(V v, V u);

  /** Print the edges */
  public void printGraph();



  /**
   * Add a vertex to the graph.
   * Pre: The vertex is not in the graph before this addition.
   */
  public void addVertex(V v);

  /**
   * Add an edge with the specified weight between the specified vertices to the graph.
   * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
   * Pre: The element is positive.
   */
  public void addEdge(V v, V u, int element);

  /**
   * Add an edge with element 0 between the specified vertices to the graph.
   * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
   */
  public void addEdge(V v, V u);

  /**
   * Remove the specified vertex from the graph.
   * Pre: The vertex is in the graph.
   */
  public void removeVertex(V vertex);

  /**
   * Remove the edge between the specified vertices from the graph.
   * Pre: The vertices are vertices in the graph,
   *   and The graph has an edge between the vertices.
   */
  public void removeEdge(V v, V u);

}

