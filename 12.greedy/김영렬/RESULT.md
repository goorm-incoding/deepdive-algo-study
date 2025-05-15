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
  - $N$명의 외계인에게 미사일을 발사해야한다.

  - 각 미사일은 구름 우주정거장과 외계인 사이의 유클리드 거리의 제곱 ${d_{i}}^2$만큼 날아간다.
    - $d_{i} = \sqrt{(x_{1} - x_{2})^{2} + (y_{1} - y_{2})^{2}}$

  - 부스터 시스템에 대한 서술이 복잡한데 정리하자면
    - 기존: 거리 1을 날아가는데 2의 시간이 소요

    - 부스터 사용: 그 시점에는 거리 1을 날아가는데 1의 시간이 소요

  - 날아가고 있는 미사일이 가장 많은 시점의 미사일의 개수를 구한 뒤 전체 시간합에서 해당값을 빼면 된다.

- **입력 (모두 정수)**
  - 미사일의 개수 $N$
    - $1 \le N \le 100,000$

  - 외계인의 좌표 $(X,~Y)$, 미사일 발사 시각 $T$
    - $-100,000 \le X_{i},Y_{i} \le 100,000$
      - 우주정거장의 좌표와 외계인의 좌표가 같은 경우는 주어지지 않는다.

      - 서로 다른 두 외계인의 좌표가 같은 경우는 주어지지 않는다.

    - $0 \le T_{i} \le 10^{10}$ (항상 짝수)

- **출력**
  - 모든 $S'_{t}$ 중 최소값



### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Missile[] missiles = new Missile[N * 2];
        long[] input;
        long S = 0;
        for (int i = 0; i < N * 2; i += 2) {
            input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long X = input[0], Y = input[1], T = input[2];

            // 거리 계산 후 S 갱신
            long d = (X * X + Y * Y) * 2;
            S += d;
            missiles[i] = new Missile(T, false);
            missiles[i + 1] = new Missile(T + d, true);
        }
        Arrays.sort(missiles);

        int ct = 0;
        int res = 0;
        for (Missile m : missiles) {
            if (!m.hit) {
                ct++;
            } else {
                ct--;
            }
            res = Math.max(ct, res);
        }

        System.out.println(S - res);
    }

    static class Missile implements Comparable<Missile> {
        long launchTime;
        boolean hit;

        public Missile(long launchTime, boolean hit) {
            this.launchTime = launchTime;
            this.hit = hit;
        }

        @Override
        public int compareTo(Missile o) {
            if (this.launchTime != o.launchTime) {
                return Long.compare(this.launchTime, o.launchTime);
            }
            return Boolean.compare(o.hit, this.hit);
        }
    }

}
```

### 실행결과

![02-missile-launch](./img/02-missile-launch.png)

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