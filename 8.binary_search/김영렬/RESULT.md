# 알고리즘 미션 - 완전 탐색

---

## 방 탈출하기

### 태그

이분 탐색

### 풀이

- **입력**

  - [Line] 1: 수열 $A$의 길이 $N$

  - [Line] 2: 수열 $A$의 요소 ($N$개의 정수)

  - [Line] 3: 모니터에 출력되는 수의 개수 $M$

  - [Line] 4 ~ [Line] 4 + M: 모니터에 출력되는 수 $B$

  - 제약 조건
    - 입력으로 주어지는 모든 수는 **정수**

    - $1 \le N,~M \le 200,000$

    - $-10^{9} \le A_{i},~B_{i} \le 10^{9}$

- **출력**

  - `1` 또는 `0`

- **문제 분석**

  - 입력으로 주어진 수열 A에 B가 포함되었는지를 확인하여

    - 포함된다면 `1`
    - 그렇지 않다면 `0`

    을 출력해야 한다.

  - 배열에 존재하는 요소를 탐색하기 위해서는 이전 챕터에서 처럼 완전탐색을 수행하거나 이분탐색을 수행하면 된다.

  - 이분 탐색(이진 탐색)의 필요조건

    - 배열이 오름차순 또는 내림차순으로 정렬되어 있어야 한다 -> 입력으로 받은 배열을 정렬할 필요가 있다.

  - 이분 탐색은 반복문 또는 재귀를 사용하여 직접 구현하거나 `Arrays.binarySearch()` 메서드를 사용하여 수행할 수 있다.


### 소스코드

```java
import java.io.*;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int mid = M / 2;

        Arrays.sort(A);

				StringBuilder sb = new StringBuilder();
        while (M > 0) {
            int B = Integer.parseInt(br.readLine());
            if (search(A, B, 0, A.length - 1)) {
                sb.append(1).append("\n");
                M--;
                continue;
            }
            sb.append(0).append("\n");
            M--;
        }

				System.out.println(sb);
    }

    private static boolean search(int[] A, int B, int start, int end) {
        if (start > end) return false;
        int mid = (start + end) / 2;
        if (B < A[mid]) return search(A, B, start, mid - 1);
        if (B > A[mid]) return search(A, B, mid + 1, end);

				return true;
    }
}
```

### 실행결과

![01-escape-the-room](./img/01-escape-the-room.png)

---

## 섭외하기 대작전

### 태그

이분 탐색

### 풀이

- 입력

- 출력

- 문제 분석



### 소스코드

```java

```

### 실행결과



---

## 게임 마스터

### 태그

이분 탐색

### 풀이

- 입력
- 출력
- 문제 분석

### 소스코드

```java

```

### 실행결과



---

## 가장 가까운 점 찾기

### 태그

이분 탐색

### 풀이

- 입력
- 출력
- 문제 분석

### 소스코드

```java

```

### 실행결과

