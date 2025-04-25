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
