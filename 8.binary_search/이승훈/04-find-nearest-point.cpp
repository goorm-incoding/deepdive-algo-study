#include <algorithm>
#include <cstdio>
#include <vector>

int main() {
    int N, Q;
    long long p;
    scanf("%d %d", &N, &Q);
    std::vector<long long> X(N);
    for (int i = 0; i < N; i++) scanf("%lld", &X[i]);
    std::sort(X.begin(), X.end());
    while (Q--) {
        scanf("%lld", &p); // 비교하고자 하는 좌표
        int left = 0, right = N;
        while (left < right) {
            // 비교하고자 하는 좌표로부터 같거나 큰 좌표 중 가장 가까운 좌표를 찾는다.
            int mid = (left + right) / 2;
            if (X[mid] < p) left = mid + 1;
            else right = mid; // right를 탐색중인 mid값으로 변경
        }
        // 이 시점에서 right는 비교하고자 하는 좌표로부터 같거나 가장 가까운 좌표 인덱스.
        if (right == 0) printf("%lld\n", X[0]); // 제일 처음
        else if (right == N) printf("%lld\n", X[N - 1]); // 마지막일 경우
        else {
            // X[0] < p < X[N - 1] 인경우 양 옆 좌표를 비교하여 가까운 좌표 출력
            long long p1 = X[right - 1];
            long long p2 = X[right];
            printf("%lld\n", (p - p1 <= p2 - p) ? p1 : p2);
        }
    }
}