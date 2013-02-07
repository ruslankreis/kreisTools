package kreis.tools.graphs;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/3/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vertex implements Cloneable {
    public boolean exist;
    public int weight;

    public Vertex() {
        exist = false;
        weight = 999999;
    }

    public Vertex(boolean ex, int w) {
        exist = ex;
        weight = w;
    }

    public Vertex clone(Vertex src) {
        Vertex dest = new Vertex();
        dest.exist = src.exist;
        dest.weight = src.weight;
        return dest;
    }
}
