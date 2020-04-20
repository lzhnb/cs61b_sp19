import javax.swing.*;
import java.security.cert.TrustAnchor;
import java.util.*;

public class MyTrieSet implements TrieSet61B {

    private TrieNode root;

    public MyTrieSet() {
        root = new TrieNode('0', false);
    }

    private class TrieNode {
        private char nodeChar;
        private boolean isLeaf;
        private Map<Character, TrieNode> children;

        public TrieNode(char nodeChar, boolean isLeaf) {
            this.nodeChar = nodeChar;
            this.isLeaf = isLeaf;
            children = new HashMap<>();
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return false;
        }
        TrieNode currNode = root;
        for (int i = 0; i < key.length(); i++) {
            char node = key.charAt(i);
            if (!currNode.children.containsKey(node)) {
                return false;
            }
            currNode = currNode.children.get(node);
        }
        return currNode.isLeaf;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() == 0 || root == null) {
            return;
        }
        TrieNode currNode = root;
        for (int i = 0; i < key.length(); i++) {
            char node = key.charAt(i);
            if (!currNode.children.containsKey(node)) {
                currNode.children.put(node, new TrieNode(node, false));
            }
            currNode = currNode.children.get(node);
        }
        currNode.isLeaf = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0 || root == null) {
            throw new IllegalArgumentException();
        }
        List<String> result = new ArrayList<>();
        TrieNode startNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char node = prefix.charAt(i);
            if (!startNode.children.containsKey(node)) {
                return result;
            }
            startNode = startNode.children.get(node);
        }
        if (startNode.isLeaf) {
            result.add(prefix);
        }
        for (TrieNode currNode : startNode.children.values()) {
            keysWithPrefixHelper(result, prefix, currNode);
        }
        return result;
    }

    public void keysWithPrefixHelper(List<String> result, String word, TrieNode currNode) {
        if (currNode.isLeaf) {
            result.add(word + currNode.nodeChar);
        }
        for (TrieNode nextNode : currNode.children.values()) {
            if (nextNode != null) {
                keysWithPrefixHelper(result, word + currNode.nodeChar, nextNode);
            }
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        StringBuilder longestPrefix = new StringBuilder();
        TrieNode currNode = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!currNode.children.containsKey(c)) {
                return longestPrefix.toString();
            } else {
                longestPrefix.append(c);
                currNode = currNode.children.get(c);
            }
        }
        return longestPrefix.toString();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
