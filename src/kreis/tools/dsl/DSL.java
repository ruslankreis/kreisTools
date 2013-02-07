package kreis.tools.dsl;

import java.util.*;
import java.util.concurrent.*;

/**
 * Библиотека для работы с коллекциями.
 * Поставляет наиболее часто применяемые методы обработки данных.
 *
 * @author Ruslan K.
 * @version 1.0.0
 */
public class DSL {
    public static <T> boolean all(Collection<T> col, Predicate<T> predicate) {
        if (col == null || predicate == null) throw new NullPointerException("Collection or Predicate are not found!");
        if (col.isEmpty()) return false;

        Iterator<T> it = col.iterator();
        while (it.hasNext())
            if (!predicate.f(it.next()))
                return false;
        return true;
    }

    public static <T> boolean any(Collection<T> col, Predicate<T> predicate) {
        if (col == null || predicate == null) throw new NullPointerException("Collection or Predicate are not found!");
        if (col.isEmpty()) return false;

        Iterator<T> it = col.iterator();
        while (it.hasNext())
            if (predicate.f(it.next()))
                return true;
        return false;
    }

    public static <T extends Number> Iterable<T> init(Collection<T> col, T initValue, int count, boolean same) {
        if (col == null) throw new NullPointerException("Collection is not found!");
        if (count == 0) return col;

        if (same) {
            for (int i = (Integer) initValue; i <= count; i++)
                col.add(initValue);
            return col;
        } else {
            for (int i = (Integer) initValue; i <= count; i++)
                col.add((T) (Integer) i);
            return col;
        }
    }

    public static <T, V> List<V> map(Collection<T> col, Func2<T, V> applier) {
        if (col == null || applier == null) throw new NullPointerException("Collection or Predicate are not found!");
        if (col.isEmpty()) return Collections.emptyList();

        List<V> answer = new LinkedList<>();
        Iterator<T> it = col.iterator();
        while (it.hasNext())
            answer.add(applier.f(it.next()));
        return answer;
    }

    public static <T, V, S> List<S> map2(Collection<T> cola, Collection<V> colb, Func3<T, V, S> applier) {
        if (cola == null || colb == null || applier == null)
            throw new NullPointerException("Collection or Predicate are not found!");
        if (cola.isEmpty() || colb.isEmpty()) return Collections.emptyList();

        Iterator<T> ita = cola.iterator();
        Iterator<V> itb = colb.iterator();

        List<S> answer = new ArrayList<S>(Math.min(cola.size(), colb.size()));
        while (ita.hasNext() && itb.hasNext()) {
            T oba = ita.next();
            V obb = itb.next();
            answer.add(applier.f(oba, obb));
        }

        return answer;
    }

    public static <T, V> List<Tuple2<V, Integer>> mapi(Collection<T> col, Func2<T, V> applier) {
        if (col == null || applier == null) throw new NullPointerException("Collection or Predicate are not found!");
        if (col.isEmpty()) return Collections.emptyList();

        List<Tuple2<V, Integer>> answer = new LinkedList<>();
        Iterator<T> it = col.iterator();
        int index = 0;
        while (it.hasNext())
            answer.add(new Tuple2<>(applier.f(it.next()), index++));
        return answer;
    }

    public static <T, V, S> List<Tuple2<S, Integer>> mapi2(Collection<T> cola, Collection<V> colb, Func3<T, V, S> applier) {
        if (cola == null || colb == null || applier == null)
            throw new NullPointerException("Collection or Predicate are not found!");
        if (cola.isEmpty() || colb.isEmpty()) return Collections.emptyList();

        Iterator<T> ita = cola.iterator();
        Iterator<V> itb = colb.iterator();
        int index = 0;

        List<Tuple2<S, Integer>> answer = new ArrayList<>(Math.min(cola.size(), colb.size()));

        while (ita.hasNext() && itb.hasNext()) {
            T oba = ita.next();
            V obb = itb.next();
            answer.add(new Tuple2<S, Integer>(applier.f(oba, obb), index++));
        }

        return answer;
    }

    public static <T> List<T> filter(Collection<T> col, Predicate<T> predicate) {
        if (col == null || predicate == null) throw new NullPointerException("Collection or Predicate are not found!");
        if (col.isEmpty()) return Collections.emptyList();

        List<T> answer = new LinkedList<>();
        Iterator<T> it = col.iterator();
        while (it.hasNext()) {
            T obj = it.next();
            if (predicate.f(obj))
                answer.add(obj);
        }
        return answer;
    }

    public static <T, V> V fold(Collection<T> col, Func3<V, T, V> applier, V init) {
        if (col == null || applier == null || init == null)
            throw new NullPointerException("Collection or Predicate or Init-value are not found!");
        if (col.isEmpty()) return null;

        Iterator<T> it = col.iterator();
        V answer = init;
        while (it.hasNext()) {
            T obj = it.next();
            answer = applier.f(answer, obj);
        }
        return answer;
    }

    public static Double sum(Collection<? extends Number> col) {
        if (col == null) throw new NullPointerException("Collection is not found!");
        Iterator<? extends Number> it = col.iterator();
        double sum = 0.0;
        while (it.hasNext()) {
            sum += it.next().doubleValue();
        }
        return sum;
    }

    public static Double product(Collection<? extends Number> col) {
        if (col == null) throw new NullPointerException("Collection is not found!");
        Iterator<? extends Number> it = col.iterator();
        double sum = 1.0;
        while (it.hasNext()) {
            sum *= it.next().doubleValue();
        }
        return sum;
    }

    public static String cat(String sep, String... args) {
        if (args == null) throw new NullPointerException("Collection is not found!");
        if (sep == null) sep = "";
        int len = 0;
        for (int i = 0; i < args.length; i++)
            len += args[i].length();

        StringBuilder sb = new StringBuilder(len + sep.length() * (args.length - 1));
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(sep);
        }

        return sb.toString();
    }

    public static String cat(String sep, Collection<String> args) {
        if (args == null) throw new NullPointerException("Collection is not found!");
        if (sep == null) sep = "";
        int len = 0, count = 0;
        Iterator<String> it = args.iterator();
        while (it.hasNext()) {
            len += it.next().length();
            count++;
        }

        StringBuilder sb = new StringBuilder(len + sep.length() * (count - 1));
        it = args.iterator();

        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(sep);
        }

        return sb.toString();
    }

    public static <T, V> List<Tuple2<T, V>> zip(Collection<T> cola, Collection<V> colb) {
        if (cola == null || colb == null) throw new NullPointerException("Collection is not found!");
        if (cola.isEmpty() || colb.isEmpty()) return Collections.emptyList();
        List<Tuple2<T, V>> answer = new LinkedList<>();
        Iterator<T> ita = cola.iterator();
        Iterator<V> itb = colb.iterator();

        while (ita.hasNext() && itb.hasNext())
            answer.add(new Tuple2<T, V>(ita.next(), itb.next()));

        return answer;
    }

    public static <T, V> Tuple2<List<T>, List<V>> unzip(Collection<Tuple2<T, V>> col) {
        if (col == null) throw new NullPointerException("Collection is not found");
        List<T> cola;
        List<V> colb;

        if (col.isEmpty()) {
            cola = Collections.emptyList();
            colb = Collections.emptyList();
            return new Tuple2<>(cola, colb);
        }

        cola = new ArrayList<>(col.size());
        colb = new ArrayList<>(col.size());

        Iterator<Tuple2<T, V>> it = col.iterator();

        while (it.hasNext()) {
            Tuple2<T, V> obj = it.next();
            cola.add(obj.get_first());
            colb.add(obj.get_second());
        }

        return new Tuple2<>(cola, colb);
    }

    public static <T, V> List<V> Parallel_For(int s, int e, PFunc<T, V> func) {
        if (func == null) throw new NullPointerException("Function is not found!");
        int cores = Parallel_Tools.getCores();

        ExecutorService service = Parallel_Tools.getService();
        List<Future<V>> tasks = new LinkedList<>();
        CountDownLatch cdl;

        int part = (e - s) / cores;
        if (part == 0) {
            part = e - s;
            cdl = new CountDownLatch(1);
        } else cdl = new CountDownLatch(cores);

        for (int i = s; i < e; i += part) {
            tasks.add(service.submit(new ParallelUnit<T, V>(func, i, i + part < e ? i + part : e, cdl)));
        }
        try {
            cdl.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        List<V> answer = new LinkedList<>();
        for (Future<V> ftr : tasks) {
            try {
                answer.add(ftr.get());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }

        return answer;
    }

    private static class ParallelUnit<T, V> implements Callable<V> {

        private final PFunc<T, V> func;
        private final int s;
        private final int e;
        private final CountDownLatch cdl;

        public ParallelUnit(PFunc<T, V> f, int s, int e, CountDownLatch cdl) {
            this.s = s;
            this.e = e;
            this.func = f;
            this.cdl = cdl;
        }

        public V call() {
            try {
                return func.doIt(s, e);
            } finally {
                cdl.countDown();
            }
        }
    }

    private static class Parallel_Tools {
        private static volatile int cores;
        private static volatile ExecutorService service;

        static {
            cores = Runtime.getRuntime().availableProcessors();
            service = Executors.newFixedThreadPool(cores);
        }

        public static int getCores() {
            return cores;
        }

        public static ExecutorService getService() {
            return service;
        }

        public void finalize() {
            service.shutdown();
            service.shutdownNow();
        }
    }
}