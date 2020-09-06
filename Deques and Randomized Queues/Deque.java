/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    public Deque() {
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.item = item;

        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            Node oldhead = head;
            node.next = oldhead;
            oldhead.prev = node;
            head = node;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node node = new Node();
        node.item = item;
        if (tail == null) {
            head = node;
            tail = node;
        }
        else {
            Node oldtail = tail;
            node.prev = oldtail;
            oldtail.next = node;
            tail = node;
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = head.item;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        if (size == 2) {
            tail = head;
        }
        size--;
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = tail.item;
        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        if (size == 2) {
            head = tail;
        }
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }


            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}
