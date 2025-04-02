import java.util.Objects;

public class Edge<V> implements Comparable<Edge<V>> {

    private int element;
    private V v, u;

    public Edge(V v, V u, int element) {
        this.v = v;
        this.u = u;
        this.element = element;
    }


    public int getElement() {
        return element;
    }


    public V getV() {
        return v;
    }


    public V getU() {
        return u;
    }

    @Override
    public String toString() {
        return "(" + v + "," + u + ") :" + element;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge<?> edge = (Edge<?>) o;
        return getElement() == edge.getElement() && v.equals(edge.v) && u.equals(edge.u);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElement(), v, u);
    }

    @Override
    public int compareTo(Edge<V> o) {
        return element - o.element;
    }

    /**
     * Return the vertex opposite the specified vertex along this edge.
     * Throw a RuntimeException, if the vertex is not part of this edge.
     */
    public V oppositeVertex(V v) {
        if (!v.equals(this.u) && !v.equals(this.v)) {
            throw new RuntimeException(v + " is not a vertex in this edge");
        }
        return (v.equals(this.u)) ? this.v : this.u;
    }

}
