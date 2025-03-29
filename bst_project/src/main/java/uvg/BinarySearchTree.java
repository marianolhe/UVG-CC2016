package uvg;

public class BinarySearchTree<T extends Comparable<T>> {
    class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private Node root;

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

    public T search(T key) {
        return searchRec(root, key);
    }

    private T searchRec(Node root, T key) {
        if (root == null || root.data.equals(key)) {
            return (root != null) ? root.data : null;
        }
        return key.compareTo(root.data) < 0 ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }
}