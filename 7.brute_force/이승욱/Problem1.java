import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int forestRow = Integer.parseInt(st.nextToken()); // 숲의 행
		int forestCol = Integer.parseInt(st.nextToken()); // 숲의 열
		int k = Integer.parseInt(st.nextToken()); // 판다 수

		int[] pandaRows = new int[k];
		int[] pandasCols = new int[k];
		for(int i = 0; i < k; ++i)
		{
			st = new StringTokenizer(br.readLine());
			pandaRows[i] = Integer.parseInt(st.nextToken()); // 판다가 있는 x좌표 저장
			pandasCols[i] = Integer.parseInt(st.nextToken()); // 판다가 있는 y좌표 저장
		}

		// 숲을 순차적으로 방문하면서 불만족도를 조사
		int answer = Integer.MAX_VALUE;
		for(int y = 1; y < forestCol + 1; ++y)
		{
			for(int x = 1; x < forestRow + 1; ++x)
			{
				boolean pandaExists = false;
				for(int i = 0; i < k; ++i)
				{
					if((y == pandasCols[i]) && (x == pandaRows[i])) // 입력받은 판다의 좌표와 같다면
					{
						pandaExists = true; // 판다가 있음을 표시
						break; // 현재 좌표 x, y에 판다가 있음으로 탈출
					}
				}

				if(!pandaExists) // 판다가 존재하지 않는다면
				{
					int sum = 0;
					for(int i = 0; i < k; ++i)
						sum += ((x - pandaRows[i]) * (x - pandaRows[i])) + ((y - pandasCols[i]) * (y - pandasCols[i])); // 불만족도를 구하고 더함

					answer = Math.min(answer, sum); // 불만족도 최소값 저장
				}
			}
		}
		System.out.print(answer);
	}
}