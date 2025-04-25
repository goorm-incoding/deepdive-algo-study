#include <cstdio>
#include <vector>
#include <algorithm>

// int형 정수를 받아 이진수 1의 개수를 카운트하여 반환하는 함수
int itob(int v) {
    int cnt = 0; // 1의 개수를 저장할 변수

    /*
    c를 1부터 시작하여 v와 비트 AND 연산 진행 후 0이 아니면 카운트 1 증가
    연산할때마다 c를 좌측으로 1만큼 비트시프트
    ex. v = 13 (3개)
    01101   01101   01101   01101   01101
    00001   00010   00100   01000   10000
    -----   -----   -----   -----   -----
    00001   00000   00100   01000   00000
    */

    for (int i = 0, c = 1; i < 21; i++, c <<= 1) if (v & c) cnt++;
    return cnt;
}

// 정렬기준 정의함수
bool compare(const int &a, const int &b) {
    // 1의 개수가 같으면 10진수 정수를 기준으로 내림차순 정렬
    // 그게 아니라면 1의 개수를 기준으로 내림차순 정렬
    int aCnt = itob(a);
    int bCnt = itob(b);
    if (aCnt == bCnt) return a > b;
    else return aCnt > bCnt;
}

int main() {
    int N, K;
    scanf("%d %d", &N, &K);
    std::vector<int> a(N);
    for (int i = 0; i < N; i++) scanf("%d", &a[i]);
    std::sort(a.begin(), a.end(), compare);
    printf("%d", a[K - 1]);
}