/*
[문제 정리]
N개의 지점이 있으며 지점마다 1번부터 N번까지 번호가 부여되어 있다.
M개의 양방향 간선이 주어졌을때, M개 중 최소한의 간선만 선택하여
모든 N개의 지점을 연결했을때의 간선의 개수를 출력하는 문제.

[해결 과정]
Union-Find 알고리즘을 사용하면서, 간선과 연결된 노드 중 부모가 같지않은 노드에 대해
부모를 같게 만든다는 것이 의미하는 것은 수로를 연결하여 지점을 연결한다는 것이기 때문에
결국 연결할 수로의 개수를 의미한다.
부모를 같게만들 때(unionParent 메서드를 사용할 때) 횟수를 카운트한것이 곧 정답이다.
*/

#include <cstdio>
#include <vector>
using std::vector;

// 간선을 편하게 다루기 위한 간선 구조체
struct Edge { int A, B; };

// Union-Find 알고리즘을 다루는 클래스
class UnionFind {
    vector<Edge> edges; // 간선 배열
    vector<int> parent; // 부모 번호 배열
    int mergeCount; // 부모 병합 횟수

    public:
    // 생성자
    // 병합 횟수를 0으로 초기화하고, 부모번호 배열을 초기화한 뒤 번호를 자기자신으로 할당
    UnionFind(int N) {
        mergeCount = 0;
        parent = vector<int>(N + 1);
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    // 해당 노드의 부모번호를 찾는 메서드
    int findParent(int x) {
        if (parent[x] == x) return x;
        return findParent(parent[x]);
    }

    // 노드의 부모번호를 같게 만드는 메서드
    void unionParent(int A, int B) {
        mergeCount++; // 병합할때 카운트를 증가시킨다.
        A = findParent(A);
        B = findParent(B);
        if (A < B) parent[B] = A;
        else parent[A] = B;
    }

    // 간선을 추가하는 메서드
    void addEdge(int A, int B) {
        edges.push_back({ A, B });
    }

    // 존재하는 간선을 확인하면서 간선과 연결된 노드의 부모번호가 같지않으면 병합하는 메서드
    void process() {
        for (Edge edge : edges) {
            if (findParent(edge.A) == findParent(edge.B)) continue;
            unionParent(edge.A, edge.B);
        }
    }

    // 병합횟수를 반환하는 메서드
    int getMergeCount() {
        return mergeCount;
    }
};

int main() {
    int T;
    scanf("%d", &T);
    while (T--) { // 테스트케이스만큼 반복
        int N, M, A, B;
        scanf("%d %d", &N, &M);
        UnionFind unionFind = UnionFind(N);
        while (M--) {
            scanf("%d %d", &A, &B);
            unionFind.addEdge(A, B); // 간선 추가
        }
        unionFind.process();
        printf("%d\n", unionFind.getMergeCount()); // 병합횟수 출력
    }
}

/*
사실 다 필요없고 코드 10줄만으로 통과된다.
문제의 입력은 무조건 모든 지점을 연결하는 경우만 주어지고,
어찌됐던 모든 지점을 연결하려면 적어도 N - 1개의 수로가 필요하기 때문.

#include <cstdio>
int main() {
    int T, N, M, A, B;
    scanf("%d", &T);
    while (T--) {
        scanf("%d %d", &N, &M);
        while (M--) scanf("%d %d", &A, &B);
        printf("%d\n", N - 1);
    }
}
*/