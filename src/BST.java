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

    public V get(K key) {}
    public void delete(K key) {}
    public Iterable<K> iterator(){}


}
