package lab9.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<T> implements Iterable<T> {

    private int length;
    private Container first;
    private Container last;

    public LinkedList() {

        length = 0;
        first = null;
        last = null;
    }

    public LinkedList(Iterable<T> entities) {

        this();
        add(entities);
    }

    public void add(T value) {

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

    public void add(Iterable<T> range) {

        for (T element : range) {
            add(element);
        }
    }

    public void addToBegin(T value) {

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

    public void insert(T value, int position) {

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

        Container added = new Container(value);
        added.prev = prev;
        added.next = prev.next;

        prev.next.prev = added;
        prev.next = added;

        length++;
    }

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

    public boolean remove(T value) {

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

    public T find(int index) {

        if (length <= index || index < 0) {
            return null;
        }

        Container current = this.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public T first(Predicate<T> predicate) {

        for (T element : this) {
            try {
                if (predicate.test(element)) {
                    return element;
                }
            } catch (Throwable t) {
            }
        }

        return null;
    }

    public Iterable<T> filter(Predicate<T> predicate) {

        ArrayList<T> result = new ArrayList<>();

        for (T element : this) {
            try {
                if (predicate.test(element)) {
                    result.add(element);
                }
            } catch (Throwable t) {
            }
        }

        return result;
    }

    public int indexOf(T value) {

        int index = 0;
        for (T element : this) {
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

    public T first() {

        if (isEmpty()) {
            return null;
        }

        return first.data;
    }

    public T last() {

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

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator(this);
    }

    private class Container {

        public T data;
        public Container next;
        public Container prev;

        public Container(T data) {

            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public void clear() {

            this.data = null;
        }
    }

    private class CustomIterator implements Iterator<T> {

        private Container current;

        public CustomIterator(LinkedList<T> list) {
            this.current = list.first;
        }

        @Override
        public boolean hasNext() {
            return null != current;
        }

        @Override
        public T next() {

            T result = current.data;
            current = current.next;

            return result;
        }
    }

}
