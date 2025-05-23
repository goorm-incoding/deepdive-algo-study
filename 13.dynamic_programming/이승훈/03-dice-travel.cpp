/*
[문제 설명]
N개의 행과 M개의 열을 가진 격자에서, (1, 1)에서 출발하여 (N, M)에 도착해야한다.
주사위를 굴려 나온 눈만큼 오른쪽 또는 아래로 이동할 수 있다.
다만 갈 수 없는 좌표도 존재한다.
(1, 1)에서 출발하여 (N, M)에 도착하는 경우의 수를 구하는 문제.

[해결 과정]
어차피 갈 수 있는 방법은 우측으로 1 ~ 6칸이동하는 방법과 아래로 1 ~ 6칸 이동하는 방법뿐이다.
새롭게 도달하는 좌표에서 반대로 갈 수 있는 경우의 수를 누적하면 끝.
(1, 1)좌표에서 시작하니, 해당 좌표의 경우의 수는 1이다.

# 예시 1번 기준

0   1   2   3   4

1   01  01  02  04

2   01  02  XX  07

3   02  XX  04  17

4   04  07  17  56
*/

#include <cstdio>
#include <vector>
using std::vector;

int main() {
    int N, M, K, R, C;
    scanf("%d %d %d", &N, &M, &K);
    vector<vector<int>> dp(N + 1, vector<int>(M + 1));
    while (K--) {
        scanf("%d %d", &R, &C);
        dp[R][C] = -1;
    }
    dp[1][1] = 1;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            if (dp[i][j] == -1) continue;
            for (int k = 1; k <= 6; k++) {
                if (i - k >= 1 && dp[i - k][j] != -1) dp[i][j] = (dp[i - k][j] + dp[i][j]) % 1'000'000'007;
                if (j - k >= 1 && dp[i][j - k] != -1) dp[i][j] = (dp[i][j - k] + dp[i][j]) % 1'000'000'007;
            }
        }
    }
    printf("%d", dp[N][M]);
}