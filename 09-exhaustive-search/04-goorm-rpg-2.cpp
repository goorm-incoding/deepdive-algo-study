#include <cstdio>
#include <cmath>
#include <vector>
using std::vector;

// 에라토스테네스의 체 알고리즘을 바탕으로하는 소수관련 클래스
class Prime {
    vector<bool> primeTable;

    public:
    Prime(int N) {
        // 생성자에서 최대값 N을 입력받아 소수 판정 테이블을 생성한다.
        primeTable = vector<bool>(N + 1);
        int limit = (int)sqrt(N);
        for (int i = 2; i <= limit; i++) {
            if (primeTable[i]) continue;
            for (int j = i; j <= N; j += i) {
                if (j != i) primeTable[j] = true;
            }
        }
    }

    bool isPrime(int N) {
        // primeTable에 체크되어있으면 소수가 아니라는 것.
        return !primeTable[N];
    }
};

int main() {
    int N, max = 0;
    bool isFantastic = true;
    scanf("%d", &N);
    vector<int> A(N);
    for (int i = 0; i < N; i++) {
        scanf("%d", &A[i]);
        if (A[i] > max) max = A[i];
    }
    Prime prime = Prime(max);

    // 여기까진 구름 RPG 1과 동일하다.
    for (int value : A) {
        int cnt = 0;
        // 현재 고유값이 2를 초과하고 소수가 아니면
        // 고유값을 내림과 동시에 카운트를 하나씩 올린다.
        while (!prime.isPrime(value) && value > 2) {
            value--;
            cnt++;
        }
        // 내린 횟수를 출력한다.
        printf("%d\n", cnt);
    }
}