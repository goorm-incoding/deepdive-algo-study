import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int M = input[1]; int K = input[2]; // N: 숲의 행 개수, M: 숲의 열 개수, K: 숲에 살고 있는 판다의 수
        Panda[] pandas = new Panda[K];
        boolean[][] forest = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) { // 판다 위치 정보
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pandas[i] = new Panda(input[0], input[1]);
            forest[input[0]][input[1]] = true;
        }

        int answer = Integer.MAX_VALUE;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (!forest[r][c]) {
                    int curR = r; int curC = c;
                    int level = Arrays.stream(pandas).mapToInt(panda -> panda.calcLevel(curR, curC)).sum();
                    answer = Math.min(answer, level);
                }
            }
        }

        System.out.println(answer);
    }

    static class Panda {
        int x;
        int y;

        public Panda(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calcLevel(int r, int c) {
            return (int) (Math.pow((r - x), 2) + Math.pow((c - y), 2));
        }
    }
}