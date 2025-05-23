import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int totalMarbles = n + m;
		BigInteger[][] dp = new BigInteger[k + 1][totalMarbles + 1];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j <= totalMarbles; j++) {
				dp[i][j] = BigInteger.ZERO;
			}
		}
		dp[0][n] = BigInteger.ONE;
		BigInteger result = BigInteger.ZERO;
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j <= totalMarbles; j++) {
				for (int offset = -1; offset <= 1; offset++) {
					int idx = j + offset;
					if (idx > 0 && idx < totalMarbles) dp[i][j] = dp[i][j].add(dp[i - 1][idx]);
				}
				if (j == 0 || j == totalMarbles) result = result.add(dp[i][j]);
			}
		}
		System.out.println(result);
		sc.close();
	}
}