import java.io.*;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int mid = M / 2;

        Arrays.sort(A);

				StringBuilder sb = new StringBuilder();
        while (M > 0) {
            int B = Integer.parseInt(br.readLine());
            if (search(A, B, 0, A.length - 1)) {
                sb.append(1).append("\n");
                M--;
                continue;
            }
            sb.append(0).append("\n");
            M--;
        }

				System.out.println(sb);
    }

    private static boolean search(int[] A, int B, int start, int end) {
        if (start > end) return false;
        int mid = (start + end) / 2;
        if (B < A[mid]) return search(A, B, start, mid - 1);
        if (B > A[mid]) return search(A, B, mid + 1, end);

				return true;
    }
}