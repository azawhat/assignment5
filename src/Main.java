public class Main {
    public static void main(String[] args) {
        BST<Integer, Integer> tree = new BST<>();
        tree.put(7, 7);
        tree.put(11, 11);
        tree.put(14, 14);
        tree.put(6, 6);
        tree.put(9, 9);
        tree.put(4, 4);
        tree.put(3, 3);
        tree.put(12, 12);

        for (BST.Entry<Integer, Integer> entry : tree) {
            System.out.println("key is " + entry.key + " and value is " + entry.value);
        }
    }
}