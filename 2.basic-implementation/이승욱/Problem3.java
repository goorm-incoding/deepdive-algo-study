import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int aliceScore = 0;
		int bobScore = 0;
		int[] alice = new int[n];
		int[] bob = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; ++i) alice[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; ++i)
		{
				bob[i] = Integer.parseInt(st.nextToken());

				int alicePower = alice[i];
				int bobPower = bob[i];
				int diff = Math.abs(alice[i] - bob[i]);

				if(alicePower == bobPower) // 두 명의 점수가 같을 때
				{
						++bobScore;
						++aliceScore;
				}
				else if(diff == 7) // 점수 차이가 7이 난다면
				{
						if(alicePower > bobPower) // Alice 점수가 더 높을 때
						{
								bobScore += 3;
								aliceScore -= 1;
						}
						else // Bob 점수가 더 높을 때
						{
								aliceScore += 3;
								bobScore -= 1;
						}
				}
				else // 단순히 누군가의 점수가 더 높을 때
				{
						if(alicePower > bobPower) aliceScore += 2;
						else bobScore += 2;
				}
		}
		System.out.print(aliceScore + " " + bobScore);
  }
}