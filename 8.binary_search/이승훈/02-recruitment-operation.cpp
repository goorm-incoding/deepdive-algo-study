#include <cstdio>
#include <vector>
#include <algorithm>

int main() {
    int N;
    long long result = 0;
    scanf("%d", &N);
    std::vector<long long> S(N);
    for (int i = 0; i < N; i++) scanf("%lld", &S[i]);
    std::sort(S.begin(), S.end()); // 오름차순 정렬
    for (int k = 2; k < N; k++) {
        int i = 0, j = k - 1;
        // S[i] + S[j] < S[k] 를 피해야 함. (제일 큰 원소가 나머지 원소의 합보다 큰 경우)
        // S[i] + S[j] >= S[k] 를 만족한다면,
        // S[i] + S[i, i+1 ... j-1] >= S[k] 성립.
        // j - i 개는 모두 조건을 만족하는것이므로 경우의 수에 추가하고 중간인덱스(j) -1
        while (i < j) {
            if (S[i] + S[j] >= S[k]) {
                result += (j - i);
                j--;
            } else {
                // 만족하지 않는다는것은 합이 작다는 것.
                // i를 하나 올린다.
                i++;
            }
        }
    }
    printf("%lld", result);
}