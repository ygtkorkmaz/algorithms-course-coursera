# Deques and Randomized Queues

Write a generic data type for a deque and a randomized queue. The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators. 

## Dequeue
A _double-ended queue_ or _deque_ (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. Create a generic data type `Deque` that implements the following API: 

```
public class Deque<Item> implements Iterable<Item> {
   public Deque()                           // construct an empty deque
   public boolean isEmpty()                 // is the deque empty?
   public int size()                        // return the number of items on the deque
   public void addFirst(Item item)          // add the item to the front
   public void addLast(Item item)           // add the item to the end
   public Item removeFirst()                // remove and return the item from the front
   public Item removeLast()                 // remove and return the item from the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing (optional)
}
```

#### Corner cases
Throw the specified exception for the following corner cases:


- Throw a `java.lang.IllegalArgumentException` if the client calls either `addFirst()` or `addLast()` with a null argument.
- Throw a `java.util.NoSuchElementException` if the client calls either `removeFirst()` or `removeLast()` when the deque is empty.
- Throw a `java.util.NoSuchElementException` if the client calls the `next()` method in the iterator when there are no more items to return.
- Throw a `java.lang.UnsupportedOperationException` if the client calls the `remove()` method in the iterator. 


## Randomized Queue
A _randomized queue_ is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure. Create a generic data type `RandomizedQueue` that implements the following API:

```
public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the randomized queue empty?
   public int size()                        // return the number of items on the randomized queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // remove and return a random item
   public Item sample()                     // return a random item (but do not remove it)
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing (optional)
}
```

#### Iterator
Each iterator must return the items in uniformly random order. The order of two or more iterators to the same randomized queue must be _mutually independent_; each iterator must maintain its own random order. 


#### Corner cases
Throw the specified exception for the following corner cases:

- Throw a `java.lang.IllegalArgumentException` if the client calls `enqueue()` with a `null` argument.

- Throw a `java.util.NoSuchElementException` if the client calls either `sample()` or `dequeue()` when the randomized queue is empty.

- Throw a `java.util.NoSuchElementException` if the client calls the `next()` method in the iterator when there are no more items to return.

- Throw a `java.lang.UnsupportedOperationException` if the client calls the `remove()` method in the iterator. 
