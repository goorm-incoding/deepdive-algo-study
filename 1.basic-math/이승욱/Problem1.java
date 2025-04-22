import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		double oneRM = W * (1 + R / 30.0);
		
		System.out.println((int)oneRM); // 소수점 이하 버림
	}
}