/*
처음에 순열을 중복제거, 오름차순 정렬을 위해 Set에 모두 넣는 식으로 했는데,
시간초과가 발생했습니다.
따라서 변수 하나를 유지하면서 최소값을 할당하는 식으로 해결했습니다.
*/

#include <climits>
#include <cstdio>
#include <vector>
using std::vector;

class Permutation {
    vector<int> numbers;
    int K;
    long long result;

    public:
    Permutation(vector<int> numbers, int K) {
        this->numbers = numbers;
        this->K = K;
    }

    void step(long long current) {
        // 현재 경우의 수를 계속하여 넘기면서 모든 경우의 수를 찾습니다.
        // 단, 현재 경우의 수가 K를 넘어갈경우 뒤는 볼 필요없기때문에 종료합니다.
        if (current > K) {
            result = (result < current) ? result : current;
            return;
        }
        for (int number : numbers) {
            // 있는 수를 다써보면서 경우의수를 만듭니다.
            long long nextCurrent = current * 10 + number;
            if (!nextCurrent) continue;
            step(nextCurrent);
        }
    }

    void make(int current) {
        result = LLONG_MAX;
        step(current);
    }

    long long getMinNumber() {
        return result;
    }
};

int main() {
    long long N, K;
    scanf("%lld", &N);
    vector<int> A(N);
    for (int i = 0; i < N; i++) scanf("%d", &A[i]);
    scanf("%lld", &K);
    Permutation permutation = Permutation(A, K);
    permutation.make(0);
    printf("%lld", permutation.getMinNumber());
}