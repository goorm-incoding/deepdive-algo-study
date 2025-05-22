import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int MOD = 100000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 앞뒤 양옆 비활성화
		// Single row Cases. (X, X, X), (O, X, X), (X, O, X), (X, X, O), (O, X, O)
		int[][] dp = new int[N][5];

		Arrays.fill(dp[0], 1);
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][4]) % MOD;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2] + dp[i - 1][3]) % MOD;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][3] + dp[i - 1][4]) % MOD;
			dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			dp[i][4] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
		}

		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum = (sum + dp[N - 1][i]) % MOD;
		}

		System.out.println(sum);
	}
}