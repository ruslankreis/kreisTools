import kreis.tools.dsl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pusher
 * Date: 2/6/13
 * Time: 4:00 PM
 */
public class App {

    public static void main(String[] args) {
        final List<Integer> ls = new ArrayList<>(100);
        DSL.init(ls, 0, 100, false);

        List<Tuple2<Integer, Integer>> a = DSL.mapi(ls, new Func2<Integer, Integer>() {
            public Integer f(Integer src) {
                return src;
            }
        });
        a = DSL.filter(a, new Predicate<Tuple2<Integer, Integer>>() {
            public boolean f(Tuple2<Integer, Integer> src) {
                return src.get_first() % 5 == 0 || src.get_first() % 7 == 0;
            }
        });

        System.out.println(a);
    }
}
