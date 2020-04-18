package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] hashTable = new int[M];

        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            hashTable[bucketNum] += 1;
        }

        int upper = (int) ((double) oomages.size() / 2.5);
        int lower = oomages.size() / 50;
        for (int hashNum : hashTable) {
            if (hashNum < lower || hashNum > upper) {
                return false;
            }
        }

        return true;
    }
}
