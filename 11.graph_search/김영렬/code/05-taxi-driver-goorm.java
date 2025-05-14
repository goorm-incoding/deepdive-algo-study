import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Main {

    // 방향
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int N;
    private static int[][] matrix;  // 지도 데이터
    private static int[][] dist;    // 거리 정보 배열

    static class Coords {
        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Route {
        Coords depart;
        Coords dest;

        public Route(Coords depart, Coords dest) {
            this.depart = depart;
            this.dest = dest;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 2차원 공간의 크기 N, 손님의 수 M
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        // 택시 기본 요금 X, 추가 요금 Y, 통행료 Z
        int X = Integer.parseInt(input[0]), Y = Integer.parseInt(input[1]), Z = Integer.parseInt(input[2]);

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        dist = new int[N][N];
        Route[] routes = new Route[M];
        for (int i = 0; i < M; i++) {
            int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Coords depart = new Coords(data[0] - 1, data[1] - 1);
            Coords dest = new Coords(data[2] - 1, data[3] - 1);
            routes[i] = new Route(depart, dest);
        }

        // 현재 위치 값 - 최초 출발지점은 첫번째 손님의 탑승지점
        Coords curLoc = new Coords(routes[0].depart.x, routes[0].depart.y);
        int profit = 0;

        for (int i = 0; i < M; i++) {
            Coords depart = routes[i].depart;
            Coords dest = routes[i].dest;
            int pickupDist = bfs(curLoc, depart);
            int serviceDist = bfs(depart, dest);
            curLoc.x = dest.x;
            curLoc.y = dest.y;

            if (serviceDist > 5) {
                profit += X + (serviceDist - 5) * Y;
            } else {
                profit += X;
            }

            profit -= (pickupDist + serviceDist) * Z;
        }

        System.out.println(profit);
    }

    private static int bfs(Coords depart, Coords dest) {
        Deque<Coords> deque = new ArrayDeque<>();
        // 거리 정보 배열 초기화
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        // 시작 위치의 거리는 0
        dist[depart.y][depart.x] = 0;
        deque.add(new Coords(depart.x, depart.y));

        while (!deque.isEmpty()) {
            Coords curLoc = deque.remove();
            int x = curLoc.x;
            int y = curLoc.y;

            for (int[] dirData : dir) {
                int dy = dirData[0];
                int dx = dirData[1];

                if (0 <= x + dx && x + dx < N && 0 <= y + dy && y + dy < N && dist[y + dy][x + dx] == -1 && matrix[y + dy][x + dx] == 0) {
                    dist[y + dy][x + dx] = dist[y][x] + 1;
                    deque.add(new Coords(x + dx, y + dy));
                }
            }
        }

        return dist[dest.y][dest.x];
    }
}