/*
[문제 정리]
N개의 섬이 있으며, 섬은 1번부터 N번까지 번호가 붙어있다.
M개의 단방향 간선(다리)이 주어졌을때, 만약 섬과 섬을 잇는 간선이
양방향으로 연결됐을경우(A to B, B to A) 해당 섬끼리는 연합으로 취급한다.
또한 연합과 연합이 연결되면, 두 연합은 한 연합으로 취급한다.
최종적인 연합의 개수를 구하는 문제.

[해결 과정]
단방향 간선이 여러개 주어졌을때 동일한 섬을 잇는 단방향 간선으로
섬을 양방향으로 왕복할 수 있으면 해당 간선을 사용하여 Union-Find 알고리즘을 수행한다.
부모의 개수가 곧 연합의 개수다.

(시작 섬, 도착 섬) 쌍을 요소로 사용한 Set 자료구조를 사용한다.
시작 섬, 도착 섬을 입력받고, 입력받은 도착 섬에서 시작 섬으로 가는 역방향 간선이 있는지 찾은 뒤
만약 역방향 간선이 있다면 입력받은 간선으로 양방향 관계가 성립하기 때문에
입력받은 간선을 Union-Find에서 사용할 간선으로 취급한다.

사용할 간선을 간추린 뒤 Union-Find 알고리즘을 수행하고
부모의 개수(연합의 개수)를 출력한다.
*/

#include <cstdio>
#include <vector>
#include <set>
using std::vector;

// 간선을 편하게 다루기 위한 간선 구조체
struct Edge { 
    int A, B;

    // 해당 구조체를 Set에서 사용하기 위해 비교 연산자 정의
    bool operator<(const Edge &e) const {
        if (B == e.B) return A < e.A;
        return B < e.B;
    }
};

// Union-Find 알고리즘을 다루는 클래스
class UnionFind {
    vector<Edge> edges; // 간선 배열
    vector<int> parent; // 부모 번호 배열

    public:
    // 생성자
    // 부모 번호 배열을 N사이즈로 초기화한 뒤 번호를 자기자신으로 할당
    UnionFind(int N) {
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

    // 부모 개수를 반환하는 메서드
    int getParentCount() {
        int size = parent.size(), result = 0;
        for (int i = 1; i <= size; i++) if (parent[i] == i) result++;
        return result;
    }
};

int main() {
    int N, M, s, e;
    scanf("%d %d", &N, &M);
    UnionFind unionFind = UnionFind(N);
    std::set<Edge> edges; // 양방향 간선인지 확인하기 위해 사용할 간선 Set
    while (M--) {
        scanf("%d %d", &s, &e); // 출발 번호, 도착 번호를 입력받고,

        // 입력받은 도착 번호에서 출발 번호로 가는 간선(역방향 간선)이 있는 지 확인.
        // 발견되면 true, 발견되지 않으면 false
        // (find 메서드는 발견지점 포인터를 반환, 발견되지 않는다면 Set의 마지막 요소를 넘어선 포인터(end)를 반환)
        bool hasReverse = edges.find({ e, s }) != edges.end();
        if (hasReverse) unionFind.addEdge(s, e); // 만약 역방향 간선이 발견된다면 '사용할 간선'에 추가
        else edges.insert({ s, e }); // 그렇지 않다면 '간선 Set'에 간선 추가
    }
    unionFind.process(); // Union-Find 알고리즘 수행
    printf("%d", unionFind.getParentCount()); // 연합 개수 출력
}