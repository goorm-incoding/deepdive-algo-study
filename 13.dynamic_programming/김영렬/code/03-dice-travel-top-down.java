import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static final int MOD_NUM = 1000000007;

    private static int N, M, K;
    private static boolean[][] matrix;
    private static int[][] cases;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];
        K = line[2];

        matrix = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[line[0] - 1][line[1] - 1] = true;
        }

        // DP 배열 선언후 시작지점 값 초기화
        cases = new int[N][M];
        Arrays.stream(cases).forEach(arr -> Arrays.fill(arr, -1));

        System.out.println(dp(0, 0));
    }

    private static int dp(int i, int j) {
        if (i == N - 1 && j == M - 1) {
            return 1;
        }

        if (cases[i][j] != -1) {
            return cases[i][j];
        }

        cases[i][j] = 0;

        // 주사위에서 나올 수 있는 경우의 수를 모두 계산
        for (int k = 1; k <= 6; k++) {
            if (i + k < N && !matrix[i + k][j]) {
                cases[i][j] += dp(i + k, j);
                cases[i][j] %= MOD_NUM;
            }
            if (j + k < M && !matrix[i][j + k]) {
                cases[i][j] += dp(i, j + k);
                cases[i][j] %= MOD_NUM;
            }
        }

        return cases[i][j];
    }
}