package bearmaps;
import edu.princeton.cs.algs4.Shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private HashMap<T, Integer> items;
    private ArrayList<Node> heap;

    private class Node {
        private T item;
        private double priority;

        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() { return item; }
        public double getPriority() { return priority; }
        public void setPriority(double priority) { this.priority = priority; }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        items = new HashMap<>();
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * (i + 1); }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        items.put(heap.get(i).getItem(), i);
        items.put(heap.get(j).getItem(), j);
    }

    private boolean lessThan(int i, int j) {
        return heap.get(i).getPriority() < heap.get(j).getPriority();
    }

    private void swim(int i) {
        while (i > 0) {
            int p = parent(i);
            if (lessThan(p, i)) { break; }
            swap(i, p);
            i = p;
        }
    }

    private void sink(int i) {
        while (leftChild(i) < size()) {
            int s = leftChild(i);
            if (rightChild(i) < size() && lessThan(rightChild(i), s)) {
                s = rightChild(i);
            }
            if (lessThan(i, s)) { break; }
            swap(i, s);
            i = s;
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        heap.add(new Node(item, priority));
        items.put(item, size() - 1);
        swim(size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        T min = heap.get(0).getItem();
        swap(0, size() - 1);
        heap.remove(size() - 1);
        sink(0);
        items.remove(min);
        return min;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int i = items.get(item);
        double oldPriority = heap.get(i).getPriority();
        heap.get(i).setPriority(priority);
        if (priority > oldPriority) {
            sink(i);
        } else {
            swim(i);
        }
    }
}
