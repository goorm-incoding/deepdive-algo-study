#include <cstdio>
#include <vector> // C++에서 vector는 곧 배열
#include <algorithm>

bool hasNumber(std::vector<int> &A, int B) {
    // A 배열에서 B 원소가 있는지 이진 탐색으로 확인하는 함수.
    // 있다면 true, 없다면 false를 반환한다.

    // left ~ right 까지 탐색
    int left = 0;
    int right = A.size() - 1;

    // left가 right를 넘어설때까지 반복.
    // 범위의 중간인덱스로 참조했을때 원소가 있다면 true 반환,
    // 참조값이 B보다 작으면 left 인덱스를 중간인덱스 + 1로 설정. (오른쪽 구간 탐색)
    // 참조값이 B보다 크면 right 인덱스를 중간인덱스 - 1로 설정. (왼쪽 구간 탐색)
    // left 인덱스가 right인덱스를 넘어선다면 찾고자 하는 원소가 없다는것임.
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (A[mid] == B) return true;
        else if (A[mid] < B) left = mid + 1;
        else right = mid - 1;
    }
    return false;
}

int main() {
    int N, M, B;
    scanf("%d", &N);
    std::vector<int> A(N);
    for (int i = 0; i < N; i++) scanf("%d", &A[i]);
    std::sort(A.begin(), A.end());
    scanf("%d", &M);
    while (M--) {
        scanf("%d", &B);
        printf("%d\n", hasNumber(A, B) ? 1 : 0);
    }
}