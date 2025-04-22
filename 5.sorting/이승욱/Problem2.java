import java.io.*; 
import java.util.*; 

class Main {
	
	static class DecimalAndBinary
	{
		int value; // 10진수
		int oneCount; // 1의 개수
		
		DecimalAndBinary(int value, int oneCount)
		{
			this.value = value;
			this.oneCount = oneCount;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		DecimalAndBinary[] arr = new DecimalAndBinary[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; ++i)
		{
			int value = Integer.parseInt(st.nextToken());
			String binary = Integer.toBinaryString(value); // 10진수를 2진수로 변환
			int oneCount = (int) binary.chars().filter(c -> c == '1').count();  // 2진수 문자열에서 '1' 의 개수를 카운트, count()의 반환타입이 long 이므로 캐스팅
			arr[i] = new DecimalAndBinary(value, oneCount);
		}
		
		// 내림차순 정렬
		Arrays.sort(arr, (v1, v2) -> {
				if (v1.oneCount != v2.oneCount) return v2.oneCount - v1.oneCount;
				else return v2.value - v1.value;
		});
		
		System.out.print(arr[k - 1].value);
	}
}