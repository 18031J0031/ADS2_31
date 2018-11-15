/**
 * Interface for graph.
 */
interface Graph {
	public int numOfVertices();
	public int numOfEdges();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}