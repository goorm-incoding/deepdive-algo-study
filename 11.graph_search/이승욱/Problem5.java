import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem5
{
    static int n, m;
    static int[][] map;

    // 승차 및 하차 위치 클래스
    static class Position
    {
        int getOnX, getOnY; // 승차 위치
        int getOffX, getOffY; // 하차 위치

        Position(int getOnX, int getOnY, int getOffX, int getOffY)
        {
            this.getOnX = getOnX;
            this.getOnY = getOnY;
            this.getOffX = getOffX;
            this.getOffY = getOffY;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int minimumFee = Integer.parseInt(st.nextToken());
        int additionalFee = Integer.parseInt(st.nextToken());
        int toll = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int y = 0; y < n; ++y)
        {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; ++x) map[y][x] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        // 승차 및 하차 위치 저장
        Position[] Positions = new Position[m];

        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int getOnX = Integer.parseInt(st.nextToken()) - 1;
            int getOnY = Integer.parseInt(st.nextToken()) - 1;
            int getOffX = Integer.parseInt(st.nextToken()) - 1;
            int getOffY = Integer.parseInt(st.nextToken()) - 1;

            Positions[i] = new Position(getOnX, getOnY, getOffX, getOffY);
        }

        // 구름이 초기 위치는 첫 손님의 승차 위치
        int goormX = Positions[0].getOnX;
        int goormY = Positions[0].getOnY;

        for (int i = 0; i < m; ++i)
        {
            Position customerPosition = Positions[i];

            // 현재 위치에서 손님 승차 위치까지 이동
            int moveDistance = bfs(goormY, goormX, customerPosition.getOnY, customerPosition.getOnX);

            // 손님 태우고 목적지까지 이동
            int rideDistance = bfs(customerPosition.getOnY, customerPosition.getOnX, customerPosition.getOffY, customerPosition.getOffX);

            // 구름이 위치 업데이트
            goormX = customerPosition.getOffX;
            goormY = customerPosition.getOffY;

            // 손님에게 받는 요금 계산
            if (rideDistance <= 5) answer += minimumFee;
            else answer += minimumFee + (rideDistance - 5) * additionalFee;

            // 통행료 차감 = 통행료 * (승객 탑승 거리 + 이동 거리)
            answer -= toll * (moveDistance + rideDistance);
        }

        System.out.println(answer);
    }

    static int bfs(int startY, int startX, int endY, int endX)
    {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> que = new LinkedList<>();
        int[][] distance = new int[n][n];

        for (int i = 0; i < n; ++i) Arrays.fill(distance[i], -1); // 거리 배열 초기화 (-1: 방문하지 않음)

        distance[startY][startX] = 0;
        que.offer(new int[]{startY, startX});

        while (!que.isEmpty())
        {
            int[] now = que.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int i = 0; i < 4; ++i)
            {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];

                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= n) continue; // 맵 범위를 벗어나면 pass
                if (distance[nextY][nextX] != -1 || map[nextY][nextX] != 0) continue; // 이미 방문했거나 이동 불가능한 칸이면 pass

                distance[nextY][nextX] = distance[nowY][nowX] + 1;
                que.offer(new int[]{nextY, nextX});
            }
        }

        return distance[endY][endX];
    }
}
