package kreis.tools.dsl;

/**
 * <b>Каркас функции от 1-ой переменной.</b>
 * @param <T> Тип входных данных
 * @param <V> Результирующий тип.
 */
public interface Func2<T, V> {
    V f(T src);
}