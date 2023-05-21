# Assignment 5
This is the 5th assignment of the Algorithms and Data Structure course

_Description:_ This is a Java implementation of a Binary Search Tree (BST) data structure. It provides the ability to store key-value pairs, perform operations like insertion, deletion, and retrieval, and iterate over the elements in an in-order traversal.

## Features

1. **Size:** Binary Search Tree keeps track of the number of elements in the tree, allowing you to retrieve the size of the BST at any time.

2. **In-order traversal:** Binary Search Tree implements an iterator that performs an in-order traversal of the elements, allowing you to iterate over the keys and values in a sorted order.
3. **Access to key and its value during iteration:** during iteration, both the key and value associated with each node are accessible enabling you to work with of key and value.

## Methods

* `put(K key, V value)`: inserts key and value into the BST.
* `get(K key)`: retrieves value associated with its key.
* `delete(K key)`: deletes a key and value from tree.
* `Iterable<K> keys()`: returns iterable collection of all keys in the tree.
* `Iterator<Entry<K, V>> iterator()`: returns iterator for the BST that uses in-order traversal.

## Assignment tasks

1. Defined variable size

```
public class BST<K extends Comparable<K>,V> implements Iterable<BST.Entry<K, V>> {
    private Node root; 
    private int size;
```

2. Implemented in-order traversal for `iterator()`

```
    private void inorder(Node node, List<K> keys) {  //method for in-order traversal of BST
        if (node == null)
            return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }
```

3. Made possible to be accessible for both key-value pair

```
    public class BSTIterator implements Iterator<Entry<K, V>> {
        private List<Entry<K, V>> entries;  // list of entries to iterate
        private int currentIndex; // index on the list

        public BSTIterator() {
            entries = new ArrayList<>();
            inorderWithEntries(root);
            currentIndex = 0;
        }
        private void inorderWithEntries(Node node) {  // method to perform in-order traversal
            if (node == null)
                return;
            inorderWithEntries(node.left);
            entries.add(new Entry<>(node.key, node.value));
            inorderWithEntries(node.right);
        }

        public boolean hasNext() {
            return currentIndex < entries.size();
        }

        public Entry<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Entry<K, V> entry = entries.get(currentIndex);
            currentIndex++;
            return entry;
        }
    }
```

## Notes

* The Entry class at the end has key-value pair and it is used for iteration, that gives access to key and value.
