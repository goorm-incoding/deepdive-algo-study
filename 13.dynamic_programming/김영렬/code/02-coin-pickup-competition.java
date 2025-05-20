import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] sumArr = new long[N];
        sumArr[0] = C[0];
        for (int i = 1; i < N; i++) {
            sumArr[i] = Math.max(0L, sumArr[i - 1]) + C[i];
        }

        System.out.println(Math.max(0L, Arrays.stream(sumArr).max().getAsLong()));
    }
}