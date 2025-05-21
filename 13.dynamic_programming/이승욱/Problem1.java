import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int MOD = (int) (Math.pow(10, 9) + 7);
        long[] memo = new long[N + 2];
        memo[1] = 0;
        memo[2] = 1;

        for (int i = 3; i <= N; ++i) memo[i] = (memo[i - 1] + memo[i - 2]) % MOD;

        System.out.println(memo[N]);
    }
}
