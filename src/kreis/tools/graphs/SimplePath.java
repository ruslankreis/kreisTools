package kreis.tools.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimplePath {
    private IGraph G;
    private boolean found;
    private ArrayList<Boolean> visited;

    public Deque<Pair<Integer, Integer>> getSpath() {
        return spath;
    }

    private Deque<Pair<Integer, Integer>> spath;

    private boolean SearchR(int v, int w) {
        if (v == w) return true;
        visited.set(v, true);
        adjIterator A = new adjIterator((DenseGraph) G, v);
        for (int t = A.beg(); !A.end(); t = A.nxt())
            if (!visited.get(t))
                if (SearchR(t, w)) {
                    spath.push(new Pair<Integer, Integer>(v, t));
                    return true;
                }
        return false;
    }

    public SimplePath(IGraph G, int v, int w) {
        if(!( G instanceof DenseGraph )) throw new ClassCastException("Need DenseGraph instance!");
        spath = new LinkedList<Pair<Integer, Integer>>();
        this.G = G;
        visited = new ArrayList<Boolean>(G.V() + 1);
        for (int i = 0; i < G.V() + 1; i++)
            visited.add(i, false);
        found = SearchR(v, w);
    }

    public boolean exists() {
        return found;
    }
}