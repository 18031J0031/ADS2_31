import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
  /**
   * Constructs the object.
   */
  Percolation() {
    // Blank constructor.
  }
  /**
   * its a boolean array.
   */
  private boolean[] array;
  /**
   * int size variable.
   */
  private int size;
  /**
   * graph.
   */
  private Graph graph;
  /**
   *array size.
   */
  private int arrSize;
  /**
   * Intializing count.
   */
  private int count;
  /**
   * first row.
   */
  private int top;
  /**
   * last row.
   */
  private int bottom;
  /**
   * Constructs the object.
   *
   * @param      num     { parameter_description }
   */
  Percolation(final int num) {
        this.arrSize = num;
        this.size = num * num;
        this.top = size;
        this.bottom = size + 1;
        this.count = 0;
        graph = new Graph(size + 2);
        array = new boolean[size];
        for (int i = 0; i < arrSize; i++) {
            graph.addEdge(top, i);
            graph.addEdge(bottom, size - i - 1);
        }
    }
  /**
     * This method converts two dimensional to one dimensional.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return  oneDimensional array
     */
    public int toOneDimension(final int row, final int col) {
        return (arrSize * (row - 1)) + (col - 1);
    }
    /**
     * This method Connects the open sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void connectOpenSites(final int row, final int col) {
        if (array[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * This method opens the blocked site.
     *
     * @param      row     The row
     * @param      col  The column
     */
    public void open(final int row, final int col) {
        int index = toOneDimension(row, col);
        array[index] = true;
        count++;
        int toprow = index - arrSize;
        int bottomrow = index + arrSize;
        if (arrSize == 1) {
            graph.addEdge(top, index);
            graph.addEdge(bottom, index);
        }
        if (bottomrow < size) {         //bottom
            connectOpenSites(index, bottomrow);
        }
        if (toprow >= 0) {              //top
            connectOpenSites(index, toprow);
        }
        if (col == 1) {                 //left
            if (col != arrSize) {
                connectOpenSites(index, index + 1);
            }
            return;
        }
        if (col == arrSize) {         //right
            connectOpenSites(index, index - 1);
            return;
        }
        connectOpenSites(index, index + 1);
        connectOpenSites(index, index - 1);
    }
    /**
     * Checks if open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return array[toOneDimension(row, col)];
    }
    /**
     * return number of open sites.
     *
     * @return count
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * method to check whether there is a flow.
     *
     * @return boolean
     */
    public boolean percolates() {
        CC connectedComponents = new CC(graph);
        return connectedComponents.connected(top, bottom);
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
      // Blank constructor.
    }
    /**
     * this main method performs operations.
     *
     * @param args String
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        Percolation obj = new Percolation(vertices);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            obj.open(Integer.parseInt(tokens[0]),
                      Integer.parseInt(tokens[1]));
        }
        System.out.println(obj.percolates()
                           && obj.numberOfOpenSites() != 0);
    }
}
