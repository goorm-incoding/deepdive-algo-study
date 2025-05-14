# 알고리즘 미션 - 그리디

---

## 보조 배터리

### 태그

그리디

### 풀이

- **문제 분석**
  - 충전기를 조건에 맞게 구매했을 때
    - 동시에 충전할 수 있는 보조 배터리의 개수

    - 충전기의 가격의 합

  - 최대한 많은 보조 배터리를 구매할 수 있게끔 충전기의 정보를 구매
    - 구매 방법이 여러가지 일때는 가장 저렴한 방법을 선택

- **입력 (모두 정수)**
  - X 타입 보조 배터리 $A$, Y 타입 보조 배터리 $B$, X && Y 타입 보조 배터리 $C$
    - $0 \le A,B,C, \le 100,000$

  - 충전기의 개수 $N$
    - $1 \le N \le 100,000$

  - 충전기의 가격 $c$, 충전기의 타입 $t$
    - $1 \le c \le 1,000,000$

    - $0 \le t \le 1$

- **출력**
  - 동시에 충전할 수 있는 보조 배터리의 개수와 충전기의 가격의 합

### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static class Charger implements Comparable<Charger> {
        int cost;
        int type;

        public Charger(int cost, int type) {
            this.cost = cost;
            this.type = type;
        }

        @Override
        public int compareTo(Charger o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = input[0], B = input[1], C = input[2];
        int N = Integer.parseInt(br.readLine());

        Charger[] chargerInfo = new Charger[N];
        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            chargerInfo[i] = new Charger(input[0], input[1]);
        }

        // 충전기의 가격을 기준으로 오름차순 정렬
        Arrays.sort(chargerInfo);

        int count = 0;
        long sum = 0;

        for (Charger c : chargerInfo) {
            // X타입 충전기인 경우
            if (c.type == 0) {
                // A타입 보조 배터리가 아직 남아있는 경우
                if (A > 0) {
                    count++;
                    sum += c.cost;
                    A--;
                } else if (C > 0) { // A타입이 남아있지 않고 C타입이 남아있는 경우
                    count++;
                    sum += c.cost;
                    C--;
                }
                continue;
            }

            // Y 타입 충전기인 경우
            if (c.type == 1){
                // B타입 보조 배터리가 아직 남아있는 경우
                if (B > 0) {
                    count++;
                    sum += c.cost;
                    B--;
                } else if (C > 0) { // B타입이 남아있지 않고 C타입이 남아있는 경우
                    count++;
                    sum += c.cost;
                    C--;
                }
            }
        }

        System.out.println(count + " " + sum);
    }
}
```

### 실행결과

![01-power-bank](./img/01-power-bank.png)

---

## 미사일 발사

### 태그

그리디

### 풀이

- **문제 분석**

- **입력**

- **출력**


### 소스코드

```java

```

### 실행결과



---

## 초코 쿠키

### 태그

그리디

### 풀이

- **문제 분석**

- **입력**

- **출력**


### 소스코드

```java

```

### 실행결과