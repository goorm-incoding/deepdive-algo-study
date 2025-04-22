import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] hp = new int[100000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) hp[i] = Integer.parseInt(st.nextToken());

		long answer = 0;
		int attackCount = 0;

		for (int i = 0; i < N; ++i)
		{
			while (hp[i] > 0)
			{
				// 공격 횟수가 0 그리고 체력이 10 이상이라면
				if ((attackCount == 0) && (hp[i] >= 10))
				{
					int cycle = hp[i] / 10; // 한 번에 몇 번의 "10 데미지 사이클" 을 버틸 수 있는지 계산
					hp[i] -= cycle * 10; // 그만큼 체력을 한꺼번에 깎아버림
					answer += cycle * 4; // 1 + 2 + 3 + 4 총 4차례 합이 정확히 10이므로(공격 패턴이 총 4개이므로)
				}
				else // 공격횟수가 0이 아니거나 체력이 10 미만일 때
				{
					hp[i] -= attackCount + 1;
					attackCount = (attackCount + 1) % 4; // 1 + 2 + 3 + 4 총 4차례 합이 정확히 10이므로(공격 패턴이 총 4개이므로)
					++answer;
					/*
					(0 + 1) % 4 = 1
					(1 + 1) % 4 = 2
					(2 + 1) % 4 = 3
					(3 + 1) % 4 = 0 -> 다시 처음으로 돌아옴
					*/
				}
			}
		}

		System.out.print(answer);
	}
}