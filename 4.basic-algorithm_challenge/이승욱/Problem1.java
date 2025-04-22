import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		long count = 0;

		/*
			N!의 끝에 붙는 0은, 10 = 2 × 5가 몇 번 곱해졌는지에 따라 생긴다.
			2의 배수(2, 4, 6, 8)보다, 5의 배수가 더 적으므로 5가 몇 번 곱해졌는지를 세는 문제가 된다.
			그런데 25, 50, 75, 100 같은 수는 5가 2번 들어감, 125는 5가 3번 들어감 (5의 배수만 세면 25, 125 등에 중복된 5를 중복해서 더하게 된다.)
			따라서 n을 5^k로 나눈 값을 더해야한다.
		*/
		for (long i = 5; i < n + 1; i *= 5) // i -> 5, 25, 125, ...
			count += n / i; // n을 i로 나눈 값을 더함

		System.out.println(count);
	}
}
