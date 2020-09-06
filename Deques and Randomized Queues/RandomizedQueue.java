/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;

    private Item[] storage;

    public RandomizedQueue() {
        storage = (Item[]) new Object[1];
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        storage[size] = item;
        size++;
        sizing();

    }

    public Item dequeue() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        int index = StdRandom.uniform(size);
        Item item = storage[index];
        storage[index] = storage[size - 1];
        size--;
        sizing();
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return storage[StdRandom.uniform(size)];
    }

    private void sizing() {
        if (size == storage.length) {
            resize(2 * storage.length);
        }
        else if (size <= storage.length / 4) {
            resize(storage.length / 2);
        }
    }

    private void resize(int newsize) {
        Item[] newstorage = (Item[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            newstorage[i] = storage[i];
        }
        storage = newstorage;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private int[] indices = new int[size];

        public RandomIterator() {
            for (int i = 0; i < size; i++) {
                indices[i] = i;
            }

            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }


            Item item = storage[indices[currentIndex]];
            currentIndex++;

            return item;
        }


    }

    public static void main(String[] args) {

    }
}
