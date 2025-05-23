/*
[문제 설명]
두 플레이어가 각각 구슬을 n, m개 가지고 있고, 서로 가위바위보를 하여 구슬을 주고받는다.
이기면 상대방으로부터 1개를 가져오고, 패배하면 빼앗긴다. 무승부일경우 교환하지 않는다.
보유하고 있는 구슬이 0개가 될 경우 게임에서 패배하게 된다.
최대 K번 가위바위보를 한다고 했을때, 둘 중 한명이 구슬을 모두 잃는 경우의 수를 구하는 문제.

[해결 과정]
dp[진행횟수][구름이가 가지고 있는 구슬의 수] 배열을 두고, 이긴경우, 비긴경우, 진경우를 나누어 본다.
초기상태(dp[0][n])는 경우가 하나밖에 없으니 1로 초기화한다.
dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i - 1][j + 1] 로 구한다.
dp[i][j]로 전이되기 위해서는 전 라운드에서 이기거나, 비기거나, 진경우이기 때문.
단, (j - 1), (j), (j + 1)이 0 이거나 n + m 이면 합산하지 않는다.
해당 경우는 누군가가 구슬을 전부 차지했거나 패배한 경우를 의미하기 때문이다.

# 예시 1번 기준 (n = 2, m = 2, k = 3) => 6

    0   1   2   3   4

0           01

1       01  01  01

2   01  02  03  02  01

3   02  05  07  05  02
*/

#include <cstdio>
#include <vector>
using std::vector;

int main() {
    int n, m, k, totalMarbles;
    long long result = 0;
    scanf("%d %d %d", &n, &m, &k);
    totalMarbles = n + m;
    vector<vector<long long>> dp(k + 1, vector<long long>(totalMarbles + 1));
    dp[0][n] = 1;
    for (int i = 1; i <= k; i++) {
        for (int j = 0; j <= totalMarbles; j++) {
            for (int offset = -1; offset <= 1; offset++) {
                int idx = j + offset;
                if (idx > 0 && idx < totalMarbles) dp[i][j] += dp[i - 1][idx];
            }
            if (j == 0 || j == totalMarbles) result += dp[i][j];
        }
    }
    printf("%lld", result);
}