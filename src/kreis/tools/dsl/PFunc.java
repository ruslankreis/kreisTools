package kreis.tools.dsl;

import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/6/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PFunc<T, V> {
    V doIt(int s, int e);
}
