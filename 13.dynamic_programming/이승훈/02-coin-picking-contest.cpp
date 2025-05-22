/*
[문제 설명]
가치가 매겨진 동전 1번부터 N개까지 N개의 동전이 있다.
연속된 번호의 동전을 적절하게 선택했을때의 가치 최대 합을 구하는 문제.

[해결 과정]
최대 연속 부분합을 구하는 다양한 알고리즘 중 대표적인 카데인 알고리즘을 사용한다.
마지막 원소가 i번째 원소인 부분 수열의 합 중 최대값을 의미하는 dp[i]를 구할때,
메모이제이션한 값 중 dp[i - 1]만을 사용하기 때문에, 별도의 배열을 유지하지 않고
변수 하나로 구한다.
*/

#include <cstdio>

int main() {
    int N;
    long long memo = 0, result = 0, C;
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        scanf("%lld", &C); // 동전의 가치: C
        memo = (C > memo + C) ? C : memo + C; // 메모이제이션
        if (memo > result) result = memo; // 정답 갱신
    }
    printf("%lld", result > 0 ? result : 0); // 음수 0으로 대체
}