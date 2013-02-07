package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class adjMapIterator implements IadjIterator {
    private Vertex[][] map;
    private int i;
    private int v;
    private int Vcnt;

    public adjMapIterator(Vertex[][] map, int V, int Vcnt) {
        this.map = map;
        v = V;
        this.Vcnt = Vcnt;
    }

    @Override
    public int beg() {
        i = -1;
        return nxt();
    }

    @Override
    public int nxt() {
        for (i++; i < Vcnt; i++)
            if (map[v][i].exist) return i;
        return -1;
    }

    @Override
    public boolean end() {
        return i >= Vcnt;
    }
}
