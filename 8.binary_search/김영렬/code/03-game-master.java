import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long N = input[0], M = input[1];

        long currentRate = M * 100 / N, winningRate = currentRate + 1;

        long from = 1L, to = 999_999_999_999L;
        long criteria = 1_000_000_000_000L;

        while (from <= to) {
            long mid = (from + to) / 2;
            long rate = (M + mid) * 100 / (N + mid);

            if (rate >= winningRate) {
                criteria = mid;
                to = mid - 1;
            } else {
                from = mid + 1;
            }
        }

        if (criteria == 1_000_000_000_000L) System.out.println("X");
        else System.out.println(criteria);
    }
}