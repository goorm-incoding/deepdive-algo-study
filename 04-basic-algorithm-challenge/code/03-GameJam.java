import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    private static final Map<String, int[]> commandInfo = new HashMap<>() {
        {
            put("U", new int[] { -1, 0 });
            put("D", new int[] { 1, 0 });
            put("L", new int[] { 0, -1 });
            put("R", new int[] { 0, 1 });
        }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Player goorm = initPlayerData("goorm", br.readLine());
        boolean[][] goormVisited = new boolean[N][N];
        Player player = initPlayerData("player", br.readLine());
        boolean[][] playerVisited = new boolean[N][N];

        BoardItem[][] board = new BoardItem[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = new BoardItem(Integer.parseInt(input[j].substring(0, input[j].length() - 1)),
                        input[j].substring(input[j].length() - 1));
            }
        }

        logic(goorm, board, goormVisited);
        logic(player, board, playerVisited);

        if (goorm.score > player.score) {
            System.out.println(goorm.getResult());
        } else {
            System.out.println(player.getResult());
        }
    }

    private static void logic(Player player, BoardItem[][] board, boolean[][] visited) {
        int x = player.curX;
        int y = player.curY;
        visited[x][y] = true;
        boolean flag = true;

        while (flag) {
            BoardItem item = board[x][y];
            for (int i = 0; i < item.count; i++) {
                x += commandInfo.get(item.command)[0];
                y += commandInfo.get(item.command)[1];
                x = calcPos(x, board[0].length);
                y = calcPos(y, board[0].length);

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    player.score += 1;
                } else {
                    flag = false;
                    break;
                }
            }
        }
    }

    private static int calcPos(int pos, int boardSize) {
        // 인덱스 범위를 넘어가는 경우에 대한 처리
        if (pos == -1)
            return boardSize - 1;
        if (pos == boardSize)
            return 0;
        return pos;
    }

    private static Player initPlayerData(String name, String input) {
        int[] data = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        return new Player(name, data[0] - 1, data[1] - 1);
    }

    static class Player {
        String name;
        int curX;
        int curY;
        int score;

        public Player(String name, int curX, int curY) {
            this.name = name;
            this.curX = curX;
            this.curY = curY;
            this.score = 1;
        }

        public String getResult() {
            return name + " " + score;
        }
    }

    static class BoardItem {
        int count;
        String command;

        public BoardItem() {
        }

        public BoardItem(int count, String command) {
            this.count = count;
            this.command = command;
        }

        public String toString() {
            return count + " " + command;
        }
    }
}