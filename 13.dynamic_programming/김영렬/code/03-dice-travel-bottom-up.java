import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
  
    private static final int MOD_NUM = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = line[0], M = line[1], K = line[2];

        boolean[][] matrix = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[line[0] - 1][line[1] - 1] = true;
        }

        // DP 배열 선언후 시작지점 값 초기화
        int[][] cases = new int[N][M]; cases[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 주사위에서 나올 수 있는 경우의 수를 모두 계산
                for (int k = 1; k <= 6; k++) {
                    if (i - k >= 0 && !matrix[i - k][j]) {
                        cases[i][j] = (cases[i][j] + cases[i - k][j]) % MOD_NUM;
                    }

                    if (j - k >= 0 && !matrix[i][j - k]) {
                        cases[i][j] = (cases[i][j] + cases[i][j - k]) % MOD_NUM;
                    }
                }
            }
        }

        System.out.println(cases[N - 1][M - 1]);
    }
}