package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGraph {
    int V();

    int E();

    boolean directed();

    void insert(int v, int w, int weight);

    void remove(int v, int w);

    int getWeight(int v, int w);

    boolean edge(int v, int w);
}