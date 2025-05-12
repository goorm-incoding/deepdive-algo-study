import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final Queue<Coords> queue = new LinkedList<>();

    private static int R, C;
    private static Cell[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        matrix = new Cell[R][C];

        Coords goormLoc = null;
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char info = line.charAt(j);
                matrix[i][j] = new Cell(info);
                if (info == '&') {
                    goormLoc = new Coords(i, j);
                }
            }
        }

        detectIgnitionPoint();
        bfs();
        printResult(goormLoc);
    }

    // 발화지점 확인 메소드
    private static void detectIgnitionPoint() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j].type == '@') {
                    queue.offer(new Coords(i, j));
                    matrix[i][j].visited = true;
                }
            }
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Coords coords = queue.poll();
            int row = coords.row, col = coords.col;

            for (int i = 0; i < 4; i++) {
                int x = row + dx[i];
                int y = col + dy[i];

                if (calcCoordsInRange(x, y) && matrix[x][y].type != '#' && !matrix[x][y].visited) {
                    process(row, col, x, y);
                }
            }
        }
    }

    // 불이 옮겨갈 장소가 범위 내의 좌표인지 계산하는 메소드
    private static boolean calcCoordsInRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    // 불이 옮겨간 이후의 작업들을 수행하는 메소드
    private static void process(int row, int col, int x, int y) {
        matrix[x][y].visited = true;
        matrix[x][y].distance = matrix[row][col].distance + 1;
        queue.offer(new Coords(x, y));
    }

    // 결과 출력 메서드
    private static void printResult(Coords goormLoc) {
        int row = goormLoc.row;
        int col = goormLoc.col;

        if (matrix[row][col].type == '&') {
            if (!matrix[row][col].visited) {
                System.out.println(-1);
            } else {
                System.out.println(matrix[row][col].distance - 1);
            }
        }
    }

    static class Coords {
        int row;
        int col;

        public Coords(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Cell {
        char type;
        boolean visited;
        int distance;

        public Cell(char type) {
            this.type = type;
            this.visited = false;
            this.distance = 0;
        }
    }
}