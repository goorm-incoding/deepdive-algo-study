import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], M = input[1];

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int aggregateCount = N;
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = input[0], b = input[1];
            if (union(a, b)) aggregateCount--;
        }

        System.out.println(aggregateCount);
    }

    private static int find(int num) {
        if (parent[num] != num) parent[num] = find(parent[num]);
        return parent[num];
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        if (rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;

        return true;
    }
}