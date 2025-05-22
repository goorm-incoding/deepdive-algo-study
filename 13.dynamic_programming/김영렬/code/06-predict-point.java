import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MOD = 1000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// B 학점은 최대 하나(0 또는 1개)
		// C 학점은 세 과목 이상 연속되지 않는다(연속 1, 2개)
		// Cases. B(X) C(0), B(X) C(1), B(X) C(2), B(O) C(0), B(O) C(1), B(O) C(2)
		int[][] dp = new int[N][6];
		dp[0][0] = 1; dp[0][1] = 1; dp[0][3] = 1;
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD; // B(X) C(0)
			dp[i][1] = dp[i - 1][0]; // B(X) C(1)
			dp[i][2] = dp[i - 1][1]; // B(X) C(2)
			dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3] + dp[i - 1][4] + dp[i - 1][5]) % MOD; // B(O) C(0)
			dp[i][4] = dp[i - 1][3]; // B(O) C(1)
			dp[i][5] = dp[i - 1][4]; // B(O) C(1)
		}

		int sum = 0;
		for (int i = 0; i < 6; i++) {
			sum = (sum + dp[N - 1][i]) % MOD;
		}

		System.out.println(sum);
	}
}