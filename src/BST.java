public class BST<K extends Comparable<K>,V> {
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
                Node successor = findMin(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = deleteMin(node.right);
            }
        }
        return node;
    }
    private Node findMin(Node node) {
        if (node.left == null)
            return node;
        return findMin(node.left);
    }
    public Iterable<K> iterator(){}


}
