import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];
        for(int y = 0; y < n; ++y)
        {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < n; ++x) board[y][x] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int[] dy = {
                1, 1, 1,
                0,    0,
                -1, -1, -1
        };
        int[] dx = {
                -1, 0, 1,
                -1,    1,
                -1, 0, 1
        };

        for(int y = 0; y < n; ++y)
        {
            for(int x = 0; x < n; ++x)
            {
                int count = 0;
                for(int i = 0; i < 8; ++i)
                {
                    int neighborY = y + dy[i]; // 1, 1, 1, 0, 0, -1, -1, -1
                    int neighborX = x + dx[i]; // -1, 0, 1, -1, 1, -1, 0, 1

                    if(neighborX < 0 || neighborY < 0 || neighborX >= n || neighborY >= n) continue; // 좌표 유효성 검사
                    if((board[y][x] == 0) && (board[neighborY][neighborX] == 1)) ++count; // 구름이 있다면 카운트
                }
                if(count == k) ++answer;
            }
        }
        System.out.print(answer);
    }
}
