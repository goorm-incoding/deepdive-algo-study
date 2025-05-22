import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.math.BigInteger;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0], m = input[1], k = input[2];

        BigInteger[][] table = new BigInteger[k + 1][n + m + 1];
        Arrays.stream(table).forEach(arr -> Arrays.fill(arr, BigInteger.ZERO));
        table[0][n] = BigInteger.ONE; // 초기값 설정

        for (int i = 0; i < k; i++) {
            for (int j = 1; j < n + m; j++) {
                table[i + 1][j - 1] = table[i + 1][j - 1].add(table[i][j]);
                table[i + 1][j] = table[i + 1][j].add(table[i][j]);
                table[i + 1][j + 1] = table[i + 1][j + 1].add(table[i][j]);
            }
        }

        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= k; i++) {
            sum = sum.add(table[i][0]).add(table[i][n + m]);
        }

        System.out.println(sum);
    }
}