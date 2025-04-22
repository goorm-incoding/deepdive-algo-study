import java.io.*;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());
		
		int sum = (2 * arr.length) + arr[0] + arr[arr.length - 1]; // (울타리 총 너비 * 2) + 좌측 울타리 높이 + 우측 울타리 높이
		
		for(int i = 1; i < n; ++i)
		{
			if(arr[i] > arr[i - 1]) sum += arr[i] - arr[i - 1];
			else sum += arr[i - 1] - arr[i];
		}
		System.out.print(sum);
	}
}