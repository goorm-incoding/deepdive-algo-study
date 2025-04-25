# 알고리즘 미션 - 기초 구현

---

## 합 계산기

### 소스코드

```java
import java.io.*;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int result = 0;
		
		StringTokenizer st;
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			char symbol = st.nextToken().charAt(0);
			int num2 = Integer.parseInt(st.nextToken());
			
			switch(symbol) {
				case '+':
					result += (num1 + num2);
					break;
				case '-':
					result += (num1 - num2);
					break;
				case '*':
					result += (num1 * num2);
					break;
				case '/':
					result += (num1 / num2);
					break;
			}
		}
		
		System.out.println(result);
	}
}
```

### 실행결과

![01-total-calculator](./img/01-total-calculator.png)

---

## 합리적 소비

### 소스코드

```java
import java.io.*;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String expensiveItemName = "";
		int expensiveItemPrice = 0;
		
		String cheapItemName = "";
		int cheapItemPrice = 0;
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String itemName = st.nextToken();
			int itemPrice = Integer.parseInt(st.nextToken());
			
			// 첫번째 항목일 때
			if (i == 0) {
				expensiveItemName = itemName;
				expensiveItemPrice = itemPrice;
				
				cheapItemName = itemName;
				cheapItemPrice = itemPrice;
				
				continue;
			}
			
			// 지금까지 나온 물건 중 가장 비쌀 경우
			if (itemPrice > expensiveItemPrice) {
				expensiveItemName = itemName;
				expensiveItemPrice = itemPrice;
				
				continue;
			}
			
			// 지금까지 나온 물건 중 가장 저렴할 경우
			if (itemPrice < cheapItemPrice) {
				cheapItemName = itemName;
				cheapItemPrice = itemPrice;
			}
		}
		
		System.out.println(expensiveItemName + " " + expensiveItemPrice);
		System.out.println(cheapItemName + " " + cheapItemPrice);
	}
}
```

### 실행결과

![02-rational-consumption](./img/02-rational-consumption.png)

---

## 과연 승자는?

### 소스코드

```java
import java.io.*;
import java.util.StringTokenizer;

class Main {
	private static int aliceScore = 0;
	private static int bobScore = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int alicePower = Integer.parseInt(st1.nextToken());
			int bobPower = Integer.parseInt(st2.nextToken());
			
			if (alicePower > bobPower) {
				if (checkSpecialCase("Alice", alicePower, bobPower)) {
					continue;
				}
				aliceScore += 2;
				continue;
			}
			
			if (bobPower > alicePower) {
				if (checkSpecialCase("Bob", bobPower, alicePower)) {
					continue;
				}
				bobScore += 2;
				continue;
			}
			
			aliceScore += 1;
			bobScore += 1;
		}
		
		System.out.println(aliceScore + " " + bobScore);
	}
	
	// 공통 로직 별도 메서드로 분리
	private static boolean checkSpecialCase(String morePowerPlayer, int player1Power, int player2Power) {
		if (player1Power - player2Power == 7) {
			if (morePowerPlayer == "Alice") {
				bobScore += 3;
				aliceScore -= 1;
				return true;
				
			}
			aliceScore += 3;
			bobScore -= 1;
			
			return true;
		}
		
		return false;
	}
}
```

### 실행결과

![03-who-is-winner](./img/03-who-is-winner.png)