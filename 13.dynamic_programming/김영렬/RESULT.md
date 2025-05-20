# 알고리즘 미션 - 동적 프로그래밍

---

## 피보나치 수열

### 태그

DP

### 풀이

- **문제 분석**
  
  - N의 최대값이 1,000,000 이므로 재귀를 활용하는 탑다운 방식을 사용하면 Call Stack이 넘치게되어 StackOverflowException이 발생한다.

  - 따라서, 반복문을 사용하는 바텀업 방식을 채택해서 문제를 해결해야한다.

- **입력**

  - 양의 정수 N

    $1 \le N \le 1,000,000$

- **출력**

  - 피보나치 수열의 $N$번째 항을 $10^{9} + 7$로 나눈 나머지


### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  
  private static final int MOD_NUM = 1000000007;
  
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		if (N == 1 || N == 2) {
			System.out.println(N - 1);
			return;
		}

		// 두번째 값 초기화
		arr[1] = 1;

		for (int i = 2; i < N; i++) {
			arr[i] = (arr[i - 2] + arr[i - 1]) % MOD_NUM;
		}

		System.out.println(arr[N - 1] % MOD_NUM);
	}
}
```

### 실행결과

![01-fibonacci-sequence](./img/01-fibonacci-sequence.png)

---

## 동전 줍기 대회

### 태그

DP

### 풀이

- **문제 분석**

  - 합 배열을 이용해서 정답을 구해야한다.

  - 단, 이 문제의 합 배열은 기존의 합 배열과는 달리 합 배열의 이전값이 음수일 경우 0으로 치환하여 더한다.

  - 또한, 가치의 합이 음수라면 상금을 얻지 못하므로 최종값은 0이 된다.

- **입력 (모두 정수)**

  - 동전의 개수 $N$

    $1 \le N \le 100,000$

  - 동전의 가치 $C_{1},C_{2},\cdots,C_{N}$

    $-100,000 \le C_{i} \le 100,000$

- **출력**

  구름이가 얻을 수 있는 최대 상금


### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] sumArr = new long[N];
        sumArr[0] = C[0];
        for (int i = 1; i < N; i++) {
            sumArr[i] = Math.max(0L, sumArr[i - 1]) + C[i];
        }

        System.out.println(Math.max(0L, Arrays.stream(sumArr).max().getAsLong()));
    }
}
```

### 실행결과

![02-coin-pickup-competition](./img/02-coin-pickup-competition.png)

---

## 주사위 여행

### 태그



### 풀이

- **문제 분석**

- **입력**

- **출력**

### 소스코드

```java

```

### 실행결과



---

## 구슬 게임

### 태그

### 풀이

- **문제 분석**

- **입력**

- **출력**

### 소스코드

```java

```

### 실행결과



---

## 거리두기

### 태그

### 풀이

- **문제 분석**

- **입력**

- **출력**

### 소스코드

```java

```

### 실행결과



---

## 학점 예측하기

### 태그

### 풀이

- **문제 분석**

- **입력**

- **출력**

### 소스코드

```java

```

### 실행결과