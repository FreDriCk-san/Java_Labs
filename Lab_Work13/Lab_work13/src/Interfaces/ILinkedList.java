package Interfaces;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ILinkedList<TType> {

    Iterable<TType> filter (Predicate<TType> predicate);
    ILinkedList<TType> map (Consumer<TType> function);
    <TResult> ILinkedList<TResult> select (Function<TType, TResult> predicate);

    int count (Predicate<TType> predicate);

    TType first (Predicate<TType> predicate);
    TType last (Predicate<TType> predicate);
    TType single (Predicate<TType> predicate);

    boolean all (Predicate<TType> predicate);
    boolean any (Predicate<TType> predicate);

}
