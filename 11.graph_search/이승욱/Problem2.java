import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem3
{
    static int row, col;
    static char[][] lab; // 연구실 지도
    static int[][] fire; // 불이 퍼져나가는 지도
    static boolean[][] visited; // 방문 배열

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // 격자 정보 초기화
        lab = new char[row][col];
        fire = new int[row][col];
        visited = new boolean[row][col];

        int goormY = -1, goormX = -1;
        Queue<int[]> que = new LinkedList<>();

        // 연구실 격자 정보 입력 받기
        for (int y = 0; y < row; ++y)
        {
            String line = br.readLine();
            for (int x = 0; x < col; ++x)
            {
                lab[y][x] = line.charAt(x);

                // 불 위치 저장 - 모든 불 위치를 큐에 추가
                if (line.charAt(x) == '@')
                {
                    que.add(new int[]{y, x});
                    fire[y][x] = 0;
                    visited[y][x] = true;
                }

                // 구름이 위치 저장
                if (line.charAt(x) == '&')
                {
                    goormY = y;
                    goormX = x;
                }
            }
        }

        bfs(que);

        if (!visited[goormY][goormX]) System.out.println(-1);
        else System.out.println(fire[goormY][goormX] - 1);
    }

    static void bfs(Queue<int[]> que)
    {
        int[] dx = {0, 0, -1, 1}; // 좌우
        int[] dy = {-1, 1, 0, 0}; // 상하

        while (!que.isEmpty())
        {
            int[] now = que.poll();
            int nowY = now[0];
            int nowX = now[1];

            // 4방향으로 직접 탐색
            for (int i = 0; i < 4; i++)
            {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];

                // 유효 범위 체크 및 방문하지 않은 노드만 처리
                if (nextY < 0 || nextX < 0 || nextY >= row || nextX >= col) continue;
                if (lab[nextY][nextX] == '#' || visited[nextY][nextX]) continue;

                visited[nextY][nextX] = true;
                fire[nextY][nextX] = fire[nowY][nowX] + 1;
                que.add(new int[]{nextY, nextX});
            }
        }
    }
}
