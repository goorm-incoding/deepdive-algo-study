import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    // 좌, 우, 하, 좌하, 우하, 상좌, 상, 상우
    private static final int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] dy = {-1, 1, 0, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int K = input[1];

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    int flagVal = 0;
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < N && y >= 0 && y < N) if (board[x][y] == 1) flagVal++;
                    }
                    if (flagVal == K) count++;
                }
            }
        }

        System.out.println(count);
    }
}