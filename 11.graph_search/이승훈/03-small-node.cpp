#include <cstdio>
#include <set>
int main() {
    // 인접리스트를 이용한 그래프 탐색 문제
    // 문제의 노드번호는 1번부터 시작하기 때문에 배열도 맞춰서 +1
    int N, M, K, s, e, last, cnt = 1;
    // 인접리스트: r[노드번호] = set<int>
    // r[N] = [Nn...]: N번 노드에서 Nn번 노드로 갈 수 있음.
    // set 자료형은 기본적으로 중복없이 항상 오름차순으로 정렬된 상태를 유지함
    // 문제의 '방문할 수 있으면서 번호가 가장 작은 노드'를 만족하기 위해 사용
    std::set<int> r[2001];
    bool v[2001] = { 0 }; // 노드 별 방문여부를 체크하기 위함
    scanf("%d %d %d", &N, &M, &K);
    // 가장 마지막지점 == 시작지점 설정하고
    last = K;
    v[K] = true; // 시작지점 방문했다고 표시
    for (int i = 0; i < M; i++) {
        scanf("%d %d", &s, &e);
        // 양방향 그래프, 양쪽 모두 추가해줌.
        r[s].insert(e); r[e].insert(s);
    }
    while (true) {
        // 다음노드 번호 초기화
        int next = 0;
        if (!r[last].empty()) {
            // 만약 현재노드로부터 갈 수 있는 곳이 있으면
            for (const int& el: r[last]) {
                // 0번인덱스부터 하나씩 꺼내서보는데,
                if (!v[el]) {
                    // 만약 그 노드에 방문하지 않았다면
                    // 다음노드를 그 노드로 표시하고 반복문 탈출
                    next = el;
                    break;
                }
            }
        }
        // 만약 반복문을 다 돌았는데도 next가 0이면,
        // 방문할 수 있는곳이 없다는 뜻이니 순회 종료.
        if (next == 0) break;
        // 그게 아니라면 이동하기위해 이동카운트 증가시키고
        // 다음노드를 방문했다고 처리 후 마지막 위치를 다음위치로 옮김
        cnt++;
        v[next] = true;
        last = next;
    }
    printf("%d %d", cnt, last);
}