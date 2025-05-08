import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

    private static int N, M;
    private static char[][] matrix;
    private static boolean[][] visited;
    private static final Queue<Coords> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        visited = new boolean[M][N];

        matrix = new char[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) matrix[i][j] = line.charAt(j);
        }

        List<Integer> objs = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || matrix[i][j] == '.') continue;
                objs.add(bfs(i, j));
            }
        }

        System.out.println(objs.size());
        System.out.println(Collections.max(objs));
    }

    private static int bfs(int i, int j) {
        visited[i][j] = true;
        queue.offer(new Coords(i, j));
        int size = 1;

        while (!queue.isEmpty()) {
            Coords coords = queue.poll();
            int x = coords.x, y = coords.y;
            if (x - 1 >= 0 && !visited[x - 1][y] && matrix[x - 1][y] == '#')    // 상단 탐색
                size += process(x - 1, y);
            if (x + 1 < M && !visited[x + 1][y] && matrix[x + 1][y] == '#')     // 하단 탐색
                size += process(x + 1, y);
            if (y - 1 >= 0 && !visited[x][y - 1] && matrix[x][y - 1] == '#')    // 좌측 탐색
                size += process(x, y - 1);
            if (y + 1 < N && !visited[x][y + 1] && matrix[x][y + 1] == '#')     // 우측 탐색
                size += process(x, y + 1);
        }

        return size;
    }

    private static int process(int x, int y) {
        visited[x][y] = true;
        queue.offer(new Coords(x, y));

        return 1;
    }

    static class Coords {
        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}