import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {

    private static int N;
    private static int M;
    private static int X;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);
        X = Integer.parseInt(firstLine[2]) - 1;

        int[] H = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int Q = Integer.parseInt(br.readLine());
        String[] D = br.readLine().split(" ");

        long result = logic(H, D);

        System.out.println(result);
    }

    private static long logic(int[] H, String[] D) {
        long result = 0L;

        for (int i = 0; i < D.length; i++) {
            result += cuttingTree(H, i);
            movePosition(D[i]);
        }

        return result;
    }

    private static long cuttingTree(int[] H, int i) {
        if (H[X] + i >= M) {
            long val = H[X] + i;
            H[X] -= val;

            return val;
        }
        return 0L;
    }

    private static void movePosition(String direction) {
        if (direction.equals("L")) {
            X = (X - 1 + N) % N;
        } else if (direction.equals("R")) {
            X = (X + 1) % N;
        }
    }
}