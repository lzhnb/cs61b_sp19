    import java.util.Random;

/**
 * Created by hug.
 */

public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int oipl = 0;

        for (int i = 1; i <= N; i ++) {
            double logValue = Math.log(i)/Math.log(2);
            int delta = (int) logValue;
            oipl += delta;
        }

        return oipl;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        double oipl = (double) optimalIPL(N);
        return oipl / (double) N;
    }

    public static void insertAndDeleteSuccessor(BST<Integer> bst) {
        Random r = new Random();
        int randomInt;
        int length = bst.size();
        while (bst.size() == length) {
            randomInt = r.nextInt(4000);
            if (bst.contains(randomInt)) {
                bst.deleteTakingSuccessor(randomInt);
            }
        }
        length = bst.size();
        while (bst.size() == length) {
            randomInt = r.nextInt(4000);
            bst.add(randomInt);
        }
    }

    public static void insertAndDeleteRandom(BST<Integer> bst) {
        Random r = new Random();
        int randomInt;
        int length = bst.size();
        while ( bst.size() == length ) {
            randomInt = r.nextInt(4000);
            if (bst.contains(randomInt)) {
                bst.deleteTakingRandom(randomInt);
            }
        }
        length = bst.size();
        while ( bst.size() == length ) {
            randomInt = r.nextInt(4000);
            bst.add(randomInt);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 9; i ++) {
            System.out.println(i + ": " + optimalIPL(i));
        }
         System.out.println(optimalAverageDepth(5));
    }
}
