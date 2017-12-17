package Classes;

import Interfaces.ILinkedList;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<TType> implements Iterable<TType>, ILinkedList<TType>, Cloneable {

    private int length;
    private Container first;
    private Container last;

    public LinkedList() {

        length = 0;
        first = null;
        last = null;
    }

    public LinkedList(Iterable<TType> entities) {

        this();
        add(entities);
    }

    //MARK: Adding
    public void add(TType value) {

        if (isEmpty()) {
            this.last = this.first = new Container(value);

        }
        else {
            Container added = new Container(value);
            added.prev = this.last;

            this.last.next = added;
            this.last = added;
        }

        this.length++;
    }

    public void add(Iterable<TType> range) {

        for (TType element : range) {
            add(element);
        }
    }

    public void addToBegin(TType value) {

        if (isEmpty()) {
            add(value);
        }
        else {
            Container added = new Container(value);
            added.next = this.first;

            this.first.prev = added;
            this.first = added;

            this.length++;
        }

    }

    private Container search(int position) {

        Container element = this.first;

        for (int i = 0; i < position - 1; ++i) {
            element = element.next;
        }

        return element;
    }

    public void insert(TType value, int position) {

        if (length <= position || position < 0) {
            return;
        }

        if (0 == position) {
            addToBegin(value);
            return;
        }
        else if (length - 1 == position) {
            add(value);
            return;
        }

        Container prev = search(position);

        //Adding
        Container added = new Container(value);
        added.prev = prev;
        added.next = prev.next;

        prev.next.prev = added;
        prev.next = added;

        length++;
    }

    //MARK: Removing
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        else if (1 == length) {
            this.first.clear();
            this.first = this.last = null;
            this.length = 0;
        }
        else {
            Container next = this.first.next;
            next.prev = null;

            this.first.clear();
            this.first = next;
            this.length--;
        }

        return true;
    }

    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        else if (1 == length) {
            return removeFirst();
        }
        else {
            Container prev = this.last.prev;
            prev.next = null;

            this.last.clear();
            this.last = prev;
            this.length--;

            return true;
        }
    }

    public boolean remove(TType value) {

        int index = indexOf(value);
        if (-1 == index) {
            return false;
        }
        else {
            return this.remove(index);
        }
    }

    public boolean remove(int position) {

        if (length <= position || position < 0) {
            return false;
        }
        else if (0 == position) {
            return removeFirst();
        }
        else if (length - 1 == position) {
            return removeLast();
        }

        removeByPrev(search(position));

        return true;
    }

    private void removeByPrev(Container prev) {

        Container current = prev.next;
        current.next.prev = prev;
        prev.next = current.next;
        current.clear();
        length--;
    }

    public void removeAll() {

        if (isEmpty()) {
            return;
        }

        while(removeFirst()){}
    }

    //Finding
    public TType find(int index) {

        if (length <= index || index < 0) {
            return null;
        }

        Container current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public int indexOf(TType value) {

        int index = 0;
        for (TType element : this) {
            try {
                if (null == value && null == element) {
                    return index;
                } else if (value.equals(element)) {
                    return index;
                }
            } catch (Throwable t) {
            } finally {
                index++;
            }
        }

        return -1;
    }

    //MARK: Properties
    public TType first() {

        if (isEmpty()) {
            return null;
        }

        return first.data;
    }

    public TType last() {

        if (isEmpty()) {
            return null;
        }

        return last.data;
    }

    public boolean isEmpty() {
        return 0 == length;
    }

    public boolean isFilled() {
        return !isEmpty();
    }

    public int length() {
        return length;
    }

    //Iterable
    @Override
    public Iterator<TType> iterator() {
        return new CustomIterator(this);
    }

    @Override
    public Iterable<TType> filter(Predicate<TType> predicate) {

        LinkedList<TType> filterList = new LinkedList<>();

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    filterList.add(element);
                }
            } catch (Throwable throwable) {

            }

        }

        return filterList;
    }

    //For Copy of LinkedList
    @Override
    public LinkedList<TType> clone() {

        LinkedList<TType> cloneOfList = new LinkedList<>();
        cloneOfList.add(this);

        return cloneOfList;
    }

    @Override
    public ILinkedList<TType> map(Consumer<TType> function) {

        LinkedList<TType> copyOfList = this.clone();

        for (TType element : copyOfList) {
            function.accept(element);
        }

        return copyOfList;
    }

    @Override
    public <TResult> ILinkedList<TResult> select(Function<TType, TResult> predicate) {

        LinkedList<TResult> newList = new LinkedList<>();

        for (TType element : this) {
            newList.add(predicate.apply(element));
        }

        return newList;
    }

    @Override
    public int count(Predicate<TType> predicate) {

        int count = 0;

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    count++;
                }
            } catch (Throwable throwable) {

            }

        }

        return count;
    }

    @Override
    public TType first(Predicate<TType> predicate) {

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    return element;
                }
            } catch (Throwable throwable) {

            }

        }

        return null;
    }

    @Override
    public TType last(Predicate<TType> predicate) {

        TType lastElement = null;

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    lastElement = element;
                }
            } catch (Throwable throwable) {

            }

        }

        return lastElement;
    }

    @Override
    public TType single(Predicate<TType> predicate) {

        TType singleElement = null;

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    if (singleElement != null) {
                        return null;
                    }
                }
            } catch (Throwable throwable) {

            }

        }

        return singleElement;
    }

    @Override
    public boolean all(Predicate<TType> predicate) {

        for (TType element : this) {

            try {
                if (!predicate.test(element)) {
                    return false;
                }
            } catch (Throwable throwable) {

            }

        }

        return true;
    }

    @Override
    public boolean any(Predicate<TType> predicate) {

        for (TType element : this) {

            try {
                if (predicate.test(element)) {
                    return true;
                }
            } catch (Throwable throwable) {

            }

        }

        return false;
    }

    private class Container {

        public TType data;
        public Container next;
        public Container prev;

        public Container(TType data) {

            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public void clear() {

            this.data = null;
        }
    }

    private class CustomIterator implements Iterator<TType> {

        private Container current;

        public CustomIterator(LinkedList<TType> list) {
            this.current = list.first;
        }

        @Override
        public boolean hasNext() {
            return null != current;
        }

        @Override
        public TType next() {

            TType result = current.data;
            current = current.next;

            return result;
        }
    }

}
