#include <cstdio>
#include <vector>
using std::vector;

// 전선 구조체
struct Line { int A, B; };

int main() {
    int N, M, A, B;
    scanf("%d %d", &N, &M);
    // 몇 개의 기폭장치와 연결되어있는지 기록할 배열과
    // 안전한 전선의 번호를 기록할 배열
    vector<int> conn(N + 1), safeLines;
    vector<Line> lines(M); // 전선 배열
    for (int i = 0; i < M; i++) {
        scanf("%d %d", &A, &B); // A와 B가 연결되어있다고 했을때,
        conn[A]++; conn[B]++; // A, B에 연결되어있는 기폭장치 수 증가
        lines[i] = { A, B };
    }
    for (int i = 0; i < M; i++) {
        Line line = lines[i];
        if (conn[line.A] > 1 && conn[line.B] > 1) {
            // 만약 A-B 전선을 지우게 되면, 해당 기폭장치와 연결되어있는 기폭장치가 하나 줄어듦.
            // 원래 연결되어있던 기폭장치의 수가 1이라면, 전선 제거 후 연결되어있는게 아무것도 없음.
            // 두 기폭장치 모두 연결되어있던 기폭장치의 수가 1 초과라면 안전한 전선임.
            safeLines.push_back(i + 1); // 안전한 전선 배열에 추가
        }
    }
    if (safeLines.empty()) printf("-1"); // 안전한 전선이 없을경우 -1 출력
    else for (int line : safeLines) printf("%d ", line); // 그렇지 않으면 번호 출력
}