import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Problem1
{
    static boolean[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1}; // 좌우
    static int[] dy = {-1, 1, 0, 0}; // 상하
    static int n;
    static int m;
    static ArrayList<Integer> sizeList = new ArrayList<>(); // 물체의 개수를 저장

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로

        arr = new boolean[m][n];
        visited = new boolean[m][n];

        for (int y = 0; y < m; ++y)
        {
            String line = br.readLine();
            for (int x = 0; x < n; ++x) if (line.charAt(x) == '#') arr[y][x] = true; // #이면 true
        }

        for (int y = 0; y < m; ++y)
            for (int x = 0; x < n; ++x)
                if (!visited[y][x] && arr[y][x]) bfs(x, y); // 방문하지 않았고, #이라면

        System.out.println(sizeList.size()); // 물체 개수
        System.out.println(Collections.max(sizeList)); // 가장 큰 물체 크기
    }

    static void bfs(int x, int y)
    {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x}); // y, x 순서
        visited[y][x] = true;
        int size = 1; // 인접한 물체의 개수

        while (!que.isEmpty())
        {
            int[] current = que.poll();
            int currentY = current[0]; // 현재 y좌표
            int currentX = current[1]; // 현재 x좌표

            for (int i = 0; i < 4; ++i)
            {
                int nextY = currentY + dy[i]; // 다음 y좌표
                int nextX = currentX + dx[i]; // 다음 x좌표

                if (nextY < 0 || nextX < 0 || nextY >= m || nextX >= n) continue; // 좌표 유효성 검사
                if (visited[nextY][nextX] || !arr[nextY][nextX]) continue; // 이미 방문했거나 #이 아니면 pass

                visited[nextY][nextX] = true; // 방문 처리
                ++size; // 인접한 물체의 개수 추가
                que.add(new int[]{nextY, nextX}); // 큐에 추가
            }
        }
        sizeList.add(size); // 인접한 물체의 개수를 리스트에 저장
    }
}
