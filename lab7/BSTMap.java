import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private N root;

    private class N {
        private K key;
        private V value;
        private N left, right;
        private int size;

        private N(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(N node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }


    private V get(N node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private N put(K key, V value, N node) {
        if (node == null) {
            return new N(key, value);
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node.left = put(key, value, node.left);
            } else if (cmp > 0) {
                node.right = put(key, value, node.right);
            } else {
                node.value = value;
            }
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(N node) {
        if (node.left == null && node.right == null) {
            printNode(node);
        } else if (node.right == null) {
            printInOrder(node.right);
            printInOrder(node);
        } else if (node.left == null) {
            printInOrder(node.left);
            printInOrder(node);
        } else {
            printInOrder(node.left);
            printInOrder(node);
            printInOrder(node.right);
        }
    }

    private void printNode(N x) {
        System.out.print(x.key);
        System.out.println(" " + x.value);
    }
}
