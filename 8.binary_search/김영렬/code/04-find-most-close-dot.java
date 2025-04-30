import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], Q = input[1];

        long[] X = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(X);

        StringBuilder sb = new StringBuilder();
        while (Q > 0) {
            long p = Long.parseLong(br.readLine());

            int start = 0, end = N - 1;
            while (start < end) {
                int mid = (start + end) / 2;

              	// 인덱스 mid의 좌표가 p의 좌표보다 작다면 조건 만족 X
                if (X[mid] < p) start = mid + 1;
              	// 인덱스 mid의 값이 p의 좌표보다 크거나 같다면 조건을 만족
              	// 인덱스 mid 이후의 요소들은 고려할 필요 X -> end를 mid로 설정
                else end = mid;
            }

            // start == 0은 p보다 좌표값이 작은 점이 없다는 뜻이므로 X의 첫번째 요소가 가장 가까운 점이 된다.
            if (start == 0) sb.append(X[start]).append("\n");
            else {
                // p의 좌표와 같거나 p보다 큰 좌표를 가지는 점 중에서 p에 더 가까운 점을 찾아 출력한다.
                if (Math.abs(p - X[start - 1]) <= Math.abs(p - X[start])) sb.append(X[start - 1]).append("\n");
                else sb.append(X[start]).append("\n");
            }
            Q--;
        }

        System.out.println(sb);
    }
}