package uvg;

import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    // Definición de Node como clase interna 
    class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private Node root;

    public T search(T key) {
        return searchRecursive(root, key);
    }

    private T searchRecursive(Node node, T key) {
        if (node == null || node.data.equals(key)) {
            return node == null ? null : node.data;
        }
        if (key.compareTo(node.data) < 0) {
            return searchRecursive(node.left, key);
        } else {
            return searchRecursive(node.right, key);
        }
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, T data) {
        if (root == null) {
            return new Node(data);
        }
        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    public void inOrderAscending() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }

    public void inOrderDescending() {
        inOrderDescRec(root);
    }

    private void inOrderDescRec(Node root) {
        if (root != null) {
            inOrderDescRec(root.right);
            System.out.println(root.data);
            inOrderDescRec(root.left);
        }
    }
    
    public void collectInOrder(List<T> items) {
        collectInOrderTraversal(root, items);
    }

    // Corregido: usar Node (sin <T>) para coincidir con la declaración de la clase
    private void collectInOrderTraversal(Node node, List<T> items) {
        if (node != null) {
            collectInOrderTraversal(node.left, items);
            items.add(node.data);
            collectInOrderTraversal(node.right, items);
        }
    }
}