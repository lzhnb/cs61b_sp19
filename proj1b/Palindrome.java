public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> reDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            reDeque.addLast(word.charAt(i));
        }
        return reDeque;
    }

    private String reverseString (String word) {
        String reStr = "";
        for (int i = word.length() - 1; i >= 0; i --) {
            reStr += word.charAt(i);
        }
        return reStr;
    }

    public boolean isPalindromeHelper (Deque<Character> deque) {
        if (deque.size() < 2) { return true; }
        return (deque.removeFirst() == deque.removeLast()) && isPalindromeHelper(deque);
    }

    public boolean isPalindrome (String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    public boolean isPalindrome (String word, CharacterComparator cc) {
        return false;
    }
}
