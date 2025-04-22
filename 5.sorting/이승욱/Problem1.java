import java.io.*;
import java.util.*;

class Main {
	
	static class Island
	{
		int x;
		int y;
		int index;
		
		Island(int x, int y, int index)
		{
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Island[] islands = new Island[n];
		for(int i = 0; i < n; ++i)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			islands[i] = new Island(x, y, i);
		}
		
		Arrays.sort(islands, (i1, i2) -> {
			if(i1.x != i2.x) return i1.x - i2.x;
			else return i1.y - i2.y;
		});
		
		int[] answer = new int[n];
		for (int i = 0; i < n; ++i) answer[islands[i].index] = n - i - 1; // 자신보다 뒤에 있는 섬들의 개수를 저장
		for (int i = 0; i < n; ++i) System.out.println(answer[i]);
		
	}
}