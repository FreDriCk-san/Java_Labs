# Lab_Work13

For the list written in laboratory work No. 9 of the class, the following operations are performed:

interface ILinkedList<TType> {
    
    ILinkedList<TType> filter(Predicate<TType> predicate);
    ILinkedList<TType> map(Consumer<TType> function);
    ILinkedList<TResult> select<TResult>(Function<TType, TResult> predicate);
 
    int count(Predicate<TType> predicate);
 
    TType first(Predicate<TType> predicate);
    TType last(Predicate<TType> predicate);
    TType single(Predicate<TType> predicate);
 
    boolean all(Predicate<TType> predicate);
    boolean any(Predicate<TType> predicate);
}
