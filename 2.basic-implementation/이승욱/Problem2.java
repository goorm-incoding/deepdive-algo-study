import java.io.*;
import java.util.*;

class Main {
	
	static class Product
	{
		String name;
		int price;
		
		Product(String name, int price)
		{
			this.name = name;
			this.price = price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		Product[] products = new Product[t]; // 객체 배열 생성
		
		
		for(int i = 0; i < t; ++i)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int price = Integer.parseInt(st.nextToken());
			
			products[i] = new Product(name, price);
		}
		
		Arrays.sort(products, (p1, p2) -> p1.price - p2.price); // Arrays.sort() 의 두 번째 파라미터는 함수형 인터페이스이므로 람다 사용 가능
		
		System.out.println(products[products.length - 1].name + " " + products[products.length - 1].price);
		System.out.println(products[0].name + " " + products[0].price);
	}
}