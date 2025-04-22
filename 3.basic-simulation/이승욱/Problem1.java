import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int location = Integer.parseInt(st.nextToken()) - 1;

		int[] height = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; ++i) height[i] = Integer.parseInt(st.nextToken());

		int q = Integer.parseInt(br.readLine());
		String[] d = new String[q];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < q; ++i) d[i] = st.nextToken();

		long answer = 0;
		for(int i = 0; i < q; ++i)
		{
			if(height[location] + i >= m) // i를 더해준 값이 m이상인지 체크
			{
				answer += height[location] + i; // 배열에 저장된 높이 + i를 소지한 목재량으로 더함
				height[location] = -i; // 벌목하였으므로 i 만큼 차감
			}

			if (d[i].equals("L")) location = (location == 0) ? n - 1 : location - 1;
			else if (d[i].equals("R")) location = (location == n - 1) ? 0 : location + 1;

			// if(d[i].equals("L")) location = (location - 1 + n) % n;
			// else if(d[i].equals("R")) location = (location + 1) % n;
		}
		// for(int j = 0; j < h.length; ++j) ++h[j]; X
		System.out.print(answer);
	}
}