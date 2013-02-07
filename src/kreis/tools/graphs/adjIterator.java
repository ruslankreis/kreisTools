package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class adjIterator implements IadjIterator {
    private DenseGraph G;
    private int i;
    private int v;

    public adjIterator(DenseGraph g, int V) {
        G = g;
        v = V;
        i = -1;
    }

    @Override
    public int beg() {
        i = -1;
        return nxt();
    }

    @Override
    public int nxt() {
        for (i++; i < G.V(); i++)
            if (G.map[v][i].exist) return i;
        return -1;
    }

    @Override
    public boolean end() {
        return i >= G.V();
    }
}
