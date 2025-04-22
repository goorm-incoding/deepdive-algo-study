import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int n = Integer.parseInt(br.readLine());
		Set<String> puddles = new HashSet<>(); // 웅덩이 좌표를 저장하는 Set

		for (int i = 0; i < n; ++i)
		{
			st = new StringTokenizer(br.readLine());
			int puddleX = Integer.parseInt(st.nextToken());
			int puddleY = Integer.parseInt(st.nextToken());
			puddles.add(puddleX + "," + puddleY); // "x,y" 형식으로 저장
		}

		int q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; ++i)
		{
			String control = st.nextToken();

			int tmpX = x, tmpY = y; // 임시 좌표
			if (control.equals("L")) tmpX = x - 1;
			else if (control.equals("R")) tmpX = x + 1;
			else if (control.equals("U")) tmpY = y + 1;
			else if (control.equals("D")) tmpY = y - 1;

			if (!puddles.contains(tmpX + "," + tmpY)) // 이동하려는 좌표에 웅덩이가 없다면
			{
				x = tmpX;
				y = tmpY;
			}
		}

		System.out.println(x + " " + y);
	}
}
