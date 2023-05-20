import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<K extends Comparable<K>,V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;
    private class Node{
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value ){
            this.key = key;
            this.value = value;
        }
    }
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return (node != null) ? node.value : null;
    }
    private Node get(Node node, K key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Node successor = findSuccessor(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = delete(node.right, node.key);
            }
        }
        return node;
    }
    private Node findSuccessor(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        inorder(root, keys);
        return keys;
    }


    private void inorder(Node node, List<K> keys) {
        if (node == null)
            return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }

    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }


    public class BSTIterator implements Iterator<Entry<K, V>> {
        private List<Entry<K, V>> entries;
        private int currentIndex;

        public BSTIterator() {
            entries = new ArrayList<>();
            inorderWithEntries(root);
            currentIndex = 0;
        }
        private void inorderWithEntries(Node node) {
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




}
