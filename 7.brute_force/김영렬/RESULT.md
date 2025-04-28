# 알고리즘 미션 - 완전 탐색

---

## 판다는 귀여워

### 태그

완전 탐색

### 풀이

- **입력**
  - N M K (숲의 행 개수; 숲의 열 개수; 숲에 살고 있는 판다의 수)
    - $2 \le N,~M \le 100$
    - $1 \le K \le min(N \times M - 1,100)$

  - K개의 라인 [형식 - R C] (판다가 살고있는 행; 판다가 살고있는 열)
    - $1 \le R_{i} \le N$
  
    - $1 \le C_{i} \le M$
  
- **출력**
  - 불만족도가 가장 낮은 칸의 불만족도

- 문제 분석
  - 판다
    - N X M 크기의 숲에는 K마리의 판다가 살고있다.
    - 판다는 각각 1 ~ N 까지 고유 번호를 가지고 있다.
    - 판다는 각자 다른 칸에 살고있으며 절대 다른 칸으로 이동하지 않는다.

  - 구름이
    - 판다들 속에 숨어들기 위해 **판다가 살고있지 않은 칸**에 들어갈 것이다.
    - 구름이는 불만족도가 가장 낮은 칸(r, c)에 들어가기로 했다.
    - 구름이가 선택한 칸의 불만족도를 구하는 공식
      - 모든 판다와의 거리의 합: $\sum_{i=1}^N (r - R_{i})^2 + (c - C_{i})^2$


### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int M = input[1]; int K = input[2]; // N: 숲의 행 개수, M: 숲의 열 개수, K: 숲에 살고 있는 판다의 수
        Panda[] pandas = new Panda[K];
        boolean[][] forest = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) { // 판다 위치 정보
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pandas[i] = new Panda(input[0], input[1]);
            forest[input[0]][input[1]] = true;
        }

        int answer = Integer.MAX_VALUE;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (!forest[r][c]) {
                    int curR = r; int curC = c;
                    int level = Arrays.stream(pandas).mapToInt(panda -> panda.calcLevel(curR, curC)).sum();
                    answer = Math.min(answer, level);
                }
            }
        }

        System.out.println(answer);
    }

    static class Panda {
        int x;
        int y;

        public Panda(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calcLevel(int r, int c) {
            return (int) (Math.pow((r - x), 2) + Math.pow((c - y), 2));
        }
    }
}
```

### 실행결과

![01.panda-is-cute](./img/01.panda-is-cute.png)

---

## 문자열 나누기

### 태그

완전 탐색

### 풀이

- 입력

  - 문자열의 길이 N ($3 \le N \le 100$)
  - 문자열 S (알파벳 소문자로 구성)

- 출력

  - 문자열을 나눠서 얻을 수 있는 최대 점수

- 문제 분석

  - 입력으로 주어진 길이 N의 문자열 S를 서로 겹치지 않는 **3개**의 부분 문자열로 나누어야 한다.

  - 부분 문자열은 모두 길이가 1이상이어야 하며, 원래 문자열에서 연속해야 한다.

    e.g) aabc -> a, ab, c

  - 점수 계산 로직

    - 문자열 S를 위 조건에 따라 나눴을 때, 등장하는 모든 부분문자열을 중복 제거하고 사전순으로 정렬한 결과를 P라고 한다.
    - 나누어진 3개의 문자열이 각각 P에서 $i, j, k$ 번째로 등장하는 문자열이라면, 얻을 수 있는 점수는 $i + j + k$ 이다.

  - 문자열 S를 3개의 부분문자열로 나눴을 때 얻을 수 있는 점수 중 최대 점수를 계산하여 출력해야 한다.


### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Main {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        List<String[]> cases = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                String subStr1 = S.substring(0, i);
                String subStr2 = S.substring(i, j);
                String subStr3 = S.substring(j);
                cases.add(new String[]{subStr1, subStr2, subStr3});
            }
        }

        List<String> dictList = cases.stream()
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < dictList.size(); i++) {
            scores.put(dictList.get(i), i + 1);
        }

        int maxScore = cases.stream()
                .mapToInt(subStrCase -> Arrays.stream(subStrCase).mapToInt(scores::get).sum())
                .max()
                .orElseThrow();

        System.out.println(maxScore);
    }
}
```

### 실행결과

![02-substring](./img/02-substring.png)

---

## 구름 찾기 깃발

### 태그

완전 탐색

### 풀이

- 입력
  - N K (게임판의 크기; 찾고 싶은 깃발의 값)
    - $1 \le N \le 1,000$, $1 \le K \le 8$ (N과 K는 모두 정수)
  - N개의 라인 [형식 - 0 0 0 1]
    - 각 칸의 정보를 나타내는 문자는 `0` 또는 `1` 이다. (0: 빈 칸; 1: 구름이 있는 칸)
- 출력
  - 값이 K인 깃발의 개수
- 문제 분석
  - N X N 크기의 게임판에 구름이 숨겨져있다.
  - 게임판에 숨겨진 모든 구름의 위치를 찾으면 게임에서 승리할 수 있다.
  - 조금 더 구름을 쉽게 찾을 수 있도록 도와주는 깃발
    - 구름이 없는 칸이면서, 상하좌우와 대각선으로 인접한 여덟칸 중 구름이 하나 이상 있는 칸에 설치 가능
    - 설치한 깃발에는 인접한 여덟 칸 중 구름이 있는 칸의 개수에 해당하는 값이 적힌다.
  - 값이 K인 깃발의 개수를 도출해야 한다.

### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    // 좌, 우, 하, 좌하, 우하, 상좌, 상, 상우
    private static final int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] dy = {-1, 1, 0, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int K = input[1];

        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    int flagVal = 0;
                    for (int k = 0; k < 8; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x >= 0 && x < N && y >= 0 && y < N) if (board[x][y] == 1) flagVal++;
                    }
                    if (flagVal == K) count++;
                }
            }
        }

        System.out.println(count);
    }
}
```

### 실행결과

![03-goorm-find-flag](./img/03-goorm-find-flag.png)

---

## 구름 PRG 2

### 태그

완전 탐색

### 풀이

- 입력 (모두 정수)
  - 갑옷의 개수 N
    - $1 \le N \le 5,000$
  - N개의 라인 - 갑옷 고유값
    - $2 \le A_{i} \le 100,000$
- 출력
  - 입력으로 주어진 갑옷들을 판타스틱한 갑옷으로 만들기 위해 필요한 갑옷 변형 시스템의 최소 횟수
- 문제 분석
  - 무기와 갑옷에는 고유값이 부여되어있다. ($고유값 \ge 2$)
  - 데미지 계산식 (고유값은 항상 2이상의 정수)
    - H, A, W (나의 체력; 갑옷의 고유값; 무기의 고유값)
    - 아래 두 조건을 만족하면 공격력만큼 체력이 감소한다.
      - $A \gt W$
      - $A~~mod~~W = 0$
  - 판타스틱한 갑옷
    - 어떤 경우에도 체력이 감소하지 않는 갑옷
    - 즉, 갑옷의 고유값(방어력)이 **소수**이면 그 갑옷은 판타스틱한 갑옷이다.
  - 갑옷 변형 시스템
    - 일정 재화를 소비해서 갑옷의 고유값을 1씩 내릴 수 있는 시스템
  - 구름이가 갑옷 변형 시스템에 소비할 수 있는 재화는 제한이 없다.

### 소스코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int armor = Integer.parseInt(br.readLine());
            int cnt = 0;

            while (!isPrime(armor - cnt)) cnt++;
            System.out.println(cnt);
        }
    }

    private static boolean isPrime(int armor) {
        if (armor == 2) return true; // 2는 소수
        if (armor % 2 == 0) return false; // 짝수는 2 제외하고 소수가 아님
        for (int i = 3; i <= (int)Math.sqrt(armor); i += 2) {
            if (armor % i == 0) return false;
        }
        return true;
    }
}
```

### 실행결과

![04-goorm-rpg-2](./img/04-goorm-rpg-2.png)

