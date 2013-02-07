package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShortestWayFloyd {
    private Vertex[][] map;
    private int Vcnt;

    public ShortestWayFloyd(DenseGraph G) {
        map = G.getMap();
        Vcnt = G.V();
    }

    private int Minimum(int a, int b) {
        if (a > b)
            return b;
        else return a;
    }

    public void setWeights() {
        for (int k = 0; k < Vcnt; k++)
            for (int i = 0; i < Vcnt; i++)
                for (int j = 0; j < Vcnt; j++)
                    map[i][j].weight = Minimum(map[i][j].weight, map[i][k].weight + map[k][j].weight);
    }
}
