import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	private static int N;
	private static int[] A;
	private static long K;
	private static long result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		K = Integer.parseInt(br.readLine());
		
		result = Long.MAX_VALUE;
		solution(0);
		
		System.out.println(result);
	}
	
	private static void solution(long num) {
		if (num > K) {
			result = Math.min(result, num);
			return;
		}
		for (int i = 0; i < N; i++) {
			long val = 10 * num + A[i];
			if (val == 0) continue;
			solution(val);
		}
	}
}