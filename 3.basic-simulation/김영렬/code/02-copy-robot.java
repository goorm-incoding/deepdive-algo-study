import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Coord current = new Coord(input[0], input[1]); // 초기 좌표
        int N = Integer.parseInt(br.readLine()); // 웅덩이 개수

        Coord[] ponds = new Coord[N]; // 웅덩이 분포 정보
        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ponds[i] = new Coord(input[0], input[1]);
        }

        int Q = Integer.parseInt(br.readLine()); // 조종 횟수
        String[] commands = br.readLine().split(" "); // 이동 커맨드 정보

        // 이동 명령 처리
        for (String command : commands) {
            DestCoord dest = new DestCoord(current.getX(), current.getY());
            if (command.equals("L")) { // 왼쪽으로 이동
                dest.moveLeft();
                moveToDest(current, dest, ponds);
            } else if (command.equals("R")) { // 오른쪽으로 이동
                dest.moveRight();
                moveToDest(current, dest, ponds);
            } else if (command.equals("U")) { // 상단으로 이동
                dest.moveForward();
                moveToDest(current, dest, ponds);
            } else { // 하단으로 이동
                dest.moveBack();
                moveToDest(current, dest, ponds);
            }
        }

        System.out.println(current.toString());
    }

    // 현재 위치 갱신
    private static void moveToDest(Coord current, DestCoord dest, Coord[] ponds) {
        if (!isDestIsPond(dest, ponds)) {
            current.setX(dest.getX());
            current.setY(dest.getY());
        }
    }

    // 가고자 하는 곳이 연못인지 확인
    private static boolean isDestIsPond(DestCoord dest, Coord[] ponds) {
        for (Coord pond : ponds) {
            if (dest.isThisCoordEqual(pond)) {
                return true;
            }
        }
        return false;
    }

    // 좌표 정보
    static class Coord {
        int x;
        int y;

        public Coord() {
        }

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isThisCoordEqual(Coord coord) {
            return this.x == coord.x && this.y == coord.y;
        }

        public String toString() {
            return x + " " + y;
        }
    }

    // 이동 목표 좌표
    static class DestCoord extends Coord {
        public DestCoord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void moveLeft() {
            this.x -= 1;
        }

        public void moveRight() {
            this.x += 1;
        }

        public void moveForward() {
            this.y += 1;
        }

        public void moveBack() {
            this.y -= 1;
        }
    }
}