package kreis.tools.dsl;

/**
 * <b>Каркас функции от 2-ух переменных</b>
 * @param <T1> Тип 1-ого элемента
 * @param <T2> Тип 2-ого элемента
 * @param <V> Результирующий тип
 */
public interface Func3<T1, T2, V> {
    V f(T1 acc, T2 src);
}
