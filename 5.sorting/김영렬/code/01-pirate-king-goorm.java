import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Island[] island = new Island[N];

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            island[i] = new Island(i, input[0], input[1]);
        }

        Arrays.sort(island);

        int[] result = new int[N];
        for (int i = 0; i < N; i++)
            result[island[i].idx] = N - 1 - i;

        Arrays.stream(result).forEach(System.out::println);
    }

    static class Island implements Comparable<Island> {
        int idx;
        int x;
        int y;

        public Island(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Island o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}