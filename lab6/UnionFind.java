public class UnionFind {

    private int[] intSets;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("It must be positive!");
        }
        intSets = new int[n];
        for (int i = 0; i < n; i++) {
            intSets[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= intSets.length || vertex < 0) {
            throw new IllegalArgumentException("vertex if not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -intSets[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return intSets[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        // no extra path-compression step needed for the case of
        // connected v1 and v2,
        // because the connected(v1, v2) is always called whenever
        // v1 and v2 are connected or not,
        // because there is find() in connected(),
        // so path-compression is already applied.
        if (!connected(v1, v2)) {
            if (sizeOf(v1) > sizeOf(v2)) {
                intSets[find(v1)] -= sizeOf(find(v2));
                intSets[find(v2)] = find(v1);
            } else {
                intSets[find(v2)] -= sizeOf(find(v1));
                intSets[find(v1)] = find(v2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (parent(root) > -1) {
            root = parent(root);
        }
        //Path-compression: let the son node all directly connect to root
        int temp;
        while (vertex != root) {
            temp = vertex;
            intSets[vertex] = root;
            vertex = parent(temp);
        }
        return root;
    }

}
