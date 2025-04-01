import java.util.List;

/**
 * Thorough test class for AdjacencyMatrixGraph implementation
 */
public class AdjacencyMatrixGraphTest {
    
    public static void main(String[] args) {
        // Run all test methods
        testInitialization();
        testAddVertices();
        testAddEdges();
        testNeighbors();
        testIncidentEdges();
        testDegree();
        testAreAdjacent();
        testRemoveVertex();
        testRemoveEdge();
        testCompleteGraph();
        
        System.out.println("All tests completed successfully! 😎");
    }
    
    /**
     * Test initialization of an empty graph
     */
    private static void testInitialization() {
        System.out.println("Testing initialization...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Check that a new graph has 0 vertices and 0 edges
        assert graph.numVertices() == 0 : "New graph should have 0 vertices";
        assert graph.numEdges() == 0 : "New graph should have 0 edges";
        assert graph.vertices().isEmpty() : "New graph should have empty vertices list";
        assert graph.edges().isEmpty() : "New graph should have empty edges list";
        
        System.out.println("✅ Initialization test passed");
    }
    
    /**
     * Test adding vertices to the graph
     */
    private static void testAddVertices() {
        System.out.println("Testing addVertex()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        // Check number of vertices
        assert graph.numVertices() == 3 : "Graph should have 3 vertices";
        
        // Check vertices list contains added vertices
        List<String> vertices = graph.vertices();
        assert vertices.contains("A") : "Vertices list should contain A";
        assert vertices.contains("B") : "Vertices list should contain B";
        assert vertices.contains("C") : "Vertices list should contain C";
        
        System.out.println("✅ addVertex() test passed");
    }
    
    /**
     * Test adding edges to the graph
     */
    private static void testAddEdges() {
        System.out.println("Testing addEdge()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        // Add edges
        graph.addEdge("A", "B"); // Unweighted edge (weight 0)
        graph.addEdge("B", "C", 5); // Weighted edge
        
        // Check number of edges
        assert graph.numEdges() == 2 : "Graph should have 2 edges";
        
        // Check edges list
        List<Edge<String>> edges = graph.edges();
        assert edges.size() == 2 : "Should be 2 edges in the list";
        
        // Check edge weights
        boolean foundABEdge = false;
        boolean foundBCEdge = false;
        
        for (Edge<String> edge : edges) {
            if ((edge.getV().equals("A") && edge.getU().equals("B")) || 
                (edge.getV().equals("B") && edge.getU().equals("A"))) {
                assert edge.getElement() == 0 : "Edge A-B should have weight 0";
                foundABEdge = true;
            }
            
            if ((edge.getV().equals("B") && edge.getU().equals("C")) || 
                (edge.getV().equals("C") && edge.getU().equals("B"))) {
                assert edge.getElement() == 5 : "Edge B-C should have weight 5";
                foundBCEdge = true;
            }
        }
        
        assert foundABEdge : "Edge A-B should exist";
        assert foundBCEdge : "Edge B-C should exist";
        
        System.out.println("✅ addEdge() test passed");
    }
    
    /**
     * Test neighbors() method
     */
    private static void testNeighbors() {
        System.out.println("Testing neighbors()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        
        // Test neighbors
        List<String> neighborsOfA = graph.neighbors("A");
        assert neighborsOfA.size() == 2 : "A should have 2 neighbors";
        assert neighborsOfA.contains("B") : "A should be connected to B";
        assert neighborsOfA.contains("C") : "A should be connected to C";
        
        List<String> neighborsOfB = graph.neighbors("B");
        assert neighborsOfB.size() == 2 : "B should have 2 neighbors";
        assert neighborsOfB.contains("A") : "B should be connected to A";
        assert neighborsOfB.contains("D") : "B should be connected to D";
        
        List<String> neighborsOfD = graph.neighbors("D");
        assert neighborsOfD.size() == 1 : "D should have 1 neighbor";
        assert neighborsOfD.contains("B") : "D should be connected to B";
        
        System.out.println("✅ neighbors() test passed");
    }
    
    /**
     * Test incidentEdges() method
     */
    private static void testIncidentEdges() {
        System.out.println("Testing incidentEdges()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 7);
        
        // Test incident edges
        List<Edge<String>> incidentToA = graph.incidentEdges("A");
        assert incidentToA.size() == 2 : "A should have 2 incident edges";
        
        boolean foundABEdge = false;
        boolean foundACEdge = false;
        
        for (Edge<String> edge : incidentToA) {
            if ((edge.getV().equals("A") && edge.getU().equals("B")) || 
                (edge.getV().equals("B") && edge.getU().equals("A"))) {
                assert edge.getElement() == 3 : "Edge A-B should have weight 3";
                foundABEdge = true;
            }
            
            if ((edge.getV().equals("A") && edge.getU().equals("C")) || 
                (edge.getV().equals("C") && edge.getU().equals("A"))) {
                assert edge.getElement() == 7 : "Edge A-C should have weight 7";
                foundACEdge = true;
            }
        }
        
        assert foundABEdge : "Edge A-B should be incident to A";
        assert foundACEdge : "Edge A-C should be incident to A";
        
        List<Edge<String>> incidentToB = graph.incidentEdges("B");
        assert incidentToB.size() == 1 : "B should have 1 incident edge";
        
        System.out.println("✅ incidentEdges() test passed");
    }
    
    /**
     * Test degree() method
     */
    private static void testDegree() {
        System.out.println("Testing degree()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "C");
        
        // Test degrees
        assert graph.degree("A") == 3 : "A should have degree 3";
        assert graph.degree("B") == 2 : "B should have degree 2";
        assert graph.degree("C") == 2 : "C should have degree 2";
        assert graph.degree("D") == 1 : "D should have degree 1";
        
        System.out.println("✅ degree() test passed");
    }
    
    /**
     * Test areAdjacent() method
     */
    private static void testAreAdjacent() {
        System.out.println("Testing areAdjacent()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        
        // Test adjacency
        assert graph.areAdjacent("A", "B") : "A and B should be adjacent";
        assert graph.areAdjacent("B", "A") : "B and A should be adjacent";
        assert graph.areAdjacent("B", "C") : "B and C should be adjacent";
        assert graph.areAdjacent("C", "B") : "C and B should be adjacent";
        
        assert !graph.areAdjacent("A", "C") : "A and C should not be adjacent";
        assert !graph.areAdjacent("A", "D") : "A and D should not be adjacent";
        assert !graph.areAdjacent("B", "D") : "B and D should not be adjacent";
        assert !graph.areAdjacent("C", "D") : "C and D should not be adjacent";
        
        System.out.println("✅ areAdjacent() test passed");
    }
    
    /**
     * Test removeVertex() method
     */
    private static void testRemoveVertex() {
        System.out.println("Testing removeVertex()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        
        // Before removal
        assert graph.numVertices() == 4 : "Should have 4 vertices before removal";
        assert graph.numEdges() == 4 : "Should have 4 edges before removal";
        
        // Remove vertex B
        graph.removeVertex("B");
        
        // After removal
        assert graph.numVertices() == 3 : "Should have 3 vertices after removal";
        assert graph.numEdges() == 2 : "Should have 2 edges after removal";
        
        // Check vertices list
        List<String> vertices = graph.vertices();
        assert !vertices.contains("B") : "B should be removed";
        assert vertices.contains("A") : "A should still exist";
        assert vertices.contains("C") : "C should still exist";
        assert vertices.contains("D") : "D should still exist";
        
        // Check edges
        assert !graph.areAdjacent("A", "B") : "Edge A-B should be removed";
        assert !graph.areAdjacent("B", "C") : "Edge B-C should be removed";
        assert graph.areAdjacent("A", "C") : "Edge A-C should still exist";
        assert graph.areAdjacent("C", "D") : "Edge C-D should still exist";
        
        System.out.println("✅ removeVertex() test passed");
    }
    
    /**
     * Test removeEdge() method
     */
    private static void testRemoveEdge() {
        System.out.println("Testing removeEdge()...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Add vertices and edges
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");
        
        // Before removal
        assert graph.numEdges() == 3 : "Should have 3 edges before removal";
        assert graph.areAdjacent("A", "B") : "A and B should be adjacent before removal";
        
        // Remove edge A-B
        graph.removeEdge("A", "B");
        
        // After removal
        assert graph.numEdges() == 2 : "Should have 2 edges after removal";
        assert !graph.areAdjacent("A", "B") : "A and B should not be adjacent after removal";
        assert graph.areAdjacent("B", "C") : "B and C should still be adjacent";
        assert graph.areAdjacent("A", "C") : "A and C should still be adjacent";
        
        System.out.println("✅ removeEdge() test passed");
    }
    
    /**
     * Test a complete graph scenario
     */
    private static void testCompleteGraph() {
        System.out.println("Testing complete graph scenario...");
        Graph<String> graph = new AdjacencyMatrixGraph<>();
        
        // Create a complete graph with 5 vertices
        String[] vertices = {"A", "B", "C", "D", "E"};
        
        // Add vertices
        for (String vertex : vertices) {
            graph.addVertex(vertex);
        }
        
        // Add edges to make it complete (each vertex connected to all others)
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                graph.addEdge(vertices[i], vertices[j], i + j);
            }
        }
        
        // Check number of vertices and edges
        assert graph.numVertices() == 5 : "Complete graph should have 5 vertices";
        assert graph.numEdges() == 10 : "Complete graph with 5 vertices should have 10 edges";
        
        // Check degrees
        for (String vertex : vertices) {
            assert graph.degree(vertex) == 4 : vertex + " should have degree 4 in a complete graph with 5 vertices";
        }
        
        // Check adjacency
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i != j) {
                    assert graph.areAdjacent(vertices[i], vertices[j]) : 
                           vertices[i] + " and " + vertices[j] + " should be adjacent in a complete graph";
                }
            }
        }
        
        System.out.println("✅ Complete graph test passed");
    }
} 