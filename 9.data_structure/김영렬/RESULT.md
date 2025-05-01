# 알고리즘 미션 - 자료구조

---

## 뒤통수가 따가워

### 태그

자료구조, 스택

### 풀이

- **입력**
  - [Line] 1: 봉우리의 수 $N$

  - [Line] 2: N개의 봉우리의 높이 $h_{1}, \cdots , h_{N}$ (공백을 기준으로 구분)

  - 제약 조건
    - $5 \le N \le 100,000$

    - $1 \le h_{i} \le 10^{9}$
- **출력**
  - 해당 봉우리에 있는 신선의 뒤통수를 볼 수 있는 다른 신선의 수를, 가장 서쪽의 봉우리에 있는 신선부터 순서대로 출력 (공백을 기준으로 구분)
- **문제 분석**
  - 무릉도원의 높은 산맥에는 왼쪽에서 오른쪽으로 일렬로 위치한 높은 산봉우리들이 존재
  - 모든 산봉우리에는 동쪽을 바라보며 명상을 하는 신선이 한 명씩 존재
  - 좌측을 기준으로 a번째 봉우리의 신선이 b번째 봉우리에 있는 신선의 뒤통수를 보려면
    - $a < b$ 이면서
    - 두 봉우리 사이에 있는 모든 봉우리의 높이가 a번째 봉우리의 높이보다 낮아야 한다.


### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] h = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(stack.size()).append(" ");
            while (!stack.isEmpty() && h[stack.peek()] <= h[i]) stack.pop();
            stack.push(i);
        }
        System.out.println(sb.toString().trim());
    }
}
```

### 실행결과

![01-back-of-my-head-feels-hot](./img/01-back-of-my-head-feels-hot.png)

---

## 재고 정리

### 태그



### 풀이

- **입력**
  
- **출력**
  
- **문제 분석**


### 소스코드

```java

```

### 실행결과



---

## 아이템 교환

### 태그



### 풀이

- **입력**
  
- **출력**
  
- **문제 분석**


### 소스코드

```java

```

### 실행결과



---

## 가장 가까운 점 찾기

### 태그



### 풀이

- **입력**
  
- **출력**
  
- **문제 분석**


### 소스코드

```java

```

### 실행결과

