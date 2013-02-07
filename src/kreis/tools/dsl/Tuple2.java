package kreis.tools.dsl;


public final class Tuple2<T, V> {
    private final T _first;
    private final V _second;

    public Tuple2(T f, V s) {
        _first = f;
        _second = s;
    }

    public V get_second() {
        return _second;
    }

    public T get_first() {
        return _first;
    }

    public String toString() {
        return "(" + _first + ":" + _second + ")";
    }
}
