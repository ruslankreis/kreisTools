package kreis.tools.dsl;

public final class Tuple3<T, V, S> {
    private final T _first;
    private final V _second;
    private final S _third;

    public Tuple3(T t, V v, S s) {
        _first = t;
        _second = v;
        _third = s;
    }

    public S get_third() {
        return _third;
    }

    public V get_second() {
        return _second;
    }

    public T get_first() {
        return _first;
    }

    public String toString() {
        return "(" + _first + ":" + _second + ":" + _third + ")";
    }
}
