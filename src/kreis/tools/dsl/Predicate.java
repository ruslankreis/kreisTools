package kreis.tools.dsl;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/4/13
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Predicate<T> {
    boolean f(T src);
}
