#include <cstdio>
#define LIMIT 1000000000000 // 10^12

int main() {
    long long N, M;
    scanf("%lld %lld", &N, &M);
    int oldRate = (M * 100) / N;

    // 승리해야하는 횟수를 이진탐색으로 구함.
    long long left = 0, mid, right = LIMIT, result = -1;
    while (left <= right) {
        mid = (left + right) / 2;
        long long newRate = ((M + mid) * 100) / (N + mid);
        if (newRate >= oldRate + 1) {
            // 만약 탐색하고있는 횟수를 더 진행했을때 승률 1% 이상 올라가면
            // 문제의 정답을 탐색하고 있는 횟수로 설정.
            result = mid;
            right = mid - 1;
        } else {
            // 그렇지 않으면 하한선 올리기
            left = mid + 1;
        }
    }

    // 만약 정답이 갱신되지 않았거나 정답이 10^12를 벗어났을 경우 'X' 출력
    if (result < 0 || result > LIMIT) printf("X");
    else printf("%lld", result); // 그게 아니라면 정답 출력
}