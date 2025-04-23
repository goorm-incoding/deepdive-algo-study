import java.io.*;
import java.util.*;

public class Main {
	static long answer = Long.MAX_VALUE; // 최댓값으로 초기화
	static int[] numbers; // 사용할 수 있는 숫자 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		numbers = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; ++i) numbers[i] = Integer.parseInt(st.nextToken()); // 사용할 수 있는 숫자 저장

		long k = Long.parseLong(br.readLine());

		func(0, k);
		System.out.print(answer);
	}

	static void func(long nowNumber, long k)
	{
		if (nowNumber > k) // 현재 숫자가 K보다 크다면
		{
			answer = Math.min(answer, nowNumber); // 정답과 현재 숫자를 비교하여 더 작은 수를 저장
			return;
		}

		for (int i = 0; i < numbers.length; ++i)
		{
			long nextNumber = nowNumber * 10 + numbers[i];
			if (nextNumber == 0) continue; // 다음 숫자가 0이면 pass
			func(nextNumber, k);
		}
	}
}