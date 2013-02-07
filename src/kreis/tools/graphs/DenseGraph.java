package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DenseGraph implements IGraph {
    private int Vcnt;
    private int Ecnt;
    private boolean digraph;
    public Vertex[][] map;

    public DenseGraph(int V, boolean digraph) {
        this.Ecnt = 0;
        this.Vcnt = V + 1;
        this.digraph = digraph;
        map = new Vertex[Vcnt][];
        for (int i = 0; i < Vcnt; i++)
            map[i] = new Vertex[Vcnt];
        for (int i = 0; i < Vcnt; i++)
            for (int j = 0; j < Vcnt; j++)
                map[i][j] = new Vertex();

    }

    public Vertex[][] getMap() {
        Vertex[][] copy;
        Vertex cVert = new Vertex();
        copy = new Vertex[Vcnt][];
        for (int i = 0; i < Vcnt; i++)
            copy[i] = new Vertex[Vcnt];
        for (int i = 0; i < Vcnt; i++)
            for (int j = 0; j < Vcnt; j++)
                copy[i][j] = new Vertex();

        for (int i = 0; i < Vcnt; i++)
            for (int j = 0; j < Vcnt; j++)
                copy[i][j] = cVert.clone(map[i][j]);
        return copy;
    }

    @Override
    public void insert(int v, int w, int weight) {
        if (map[v][w].exist || (!digraph && map[w][v].exist)) return;
        else Ecnt++;
        map[v][w].exist = true;
        map[v][w].weight = weight;
        if (!digraph) {
            map[w][v].exist = true;
            map[w][v].weight = weight;
        }
    }

    @Override
    public void remove(int v, int w) {
        if (map[v][w].exist) Vcnt--;
        {
            map[v][w].exist = false;
            map[v][w].weight = Integer.MAX_VALUE;
        }
        if (!digraph) {
            map[w][v].exist = false;
            map[w][v].weight = Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean edge(int v, int w) {
        return map[v][w].exist;
    }

    @Override
    public boolean directed() {
        return digraph;
    }

    public int getWeight(int v, int w) {
        return map[v][w].weight;
    }

    @Override
    public int V() {
        return Vcnt;
    }

    @Override
    public int E() {
        return Ecnt;
    }
}