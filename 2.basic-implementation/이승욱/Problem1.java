import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		int sum = 0;
		for(int i = 0; i < t; ++i)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			String operator = st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			
			if(operator.equals("+")) sum += a + b;
			else if(operator.equals("-")) sum += a - b;
			else if(operator.equals("/")) sum += a / b;
			else if(operator.equals("*")) sum += a * b;
		}
		System.out.print(sum);
	}
}