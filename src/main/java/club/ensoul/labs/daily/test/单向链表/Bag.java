package club.ensoul.labs.daily.test.单向链表;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 单链表实现
 *
 * @param <I>
 */
public class Bag<I> implements Iterable<I>, Collection<I>, java.io.Serializable {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private I[] elementData;
    
    public Bag() {
        clear();
    }
    
    public void clear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
    
    private void ensureCapacity(int newCapacity) {
        if(newCapacity < size) {
            return;
        } else {
            I[] oldIs = elementData;
            elementData = (I[]) new Object[newCapacity];
            for(int i = 0; i < size(); i++) {
                elementData[i] = oldIs[i];
            }
        }
    }
    
    @Override
    public boolean add(I item) {
        if(elementData.length == size()) {
            ensureCapacity(2 * size() + 1);
        }
        elementData[size()] = item;
        size++;
        return true;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    
    public int size() {
        return size;
    }
    
    @Override
    public Iterator<I> iterator() {
        return new BagIterator();
    }
    
    @Override
    public Object[] toArray() {
        return elementData;
    }
    
    @Override
    public boolean remove(Object o) {
        return false;
    }
    
    @Override
    public boolean addAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean removeAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
    
    public Stream<I> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
    
    private class BagIterator implements Iterator<I> {
        private int current = 0;
        
        @Override
        public boolean hasNext() {
            return current < size();
        }
        
        @Override
        public I next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return elementData[current++];
        }
        
        @Override
        public void remove() {
        
        }
        
    }
    
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<Integer>();
        bag.add(1);
        bag.add(3);
        bag.add(6);
        bag.add(4);
        Iterator<Integer> it = bag.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
        System.out.println();
    
        bag.stream().filter(b -> b <= 4).forEach(System.out::println);
        
    }
}