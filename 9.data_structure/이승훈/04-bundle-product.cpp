/*
물건의 번호 a, b가 M번 주어진다.
a번 물건이 포함된 묶음 상품과 b번 물건이 포함된 묶음 상품을 합쳐 하나의 묶음 상품으로 만든다.
전형적인 Union-Find 문제.
*/

#include <cstdio>
#include <vector>

class Store {
    // 물건의 부모 번호를 저장할 배열
    std::vector<int> parent;

    public:
    // 생성자 함수, parent 배열을 N + 1크기로 초기화한다.
    // 초기 부모 번호는 자기 자신으로 설정한다. (묶음 상태 아님)
    Store(int N) {
        parent = std::vector<int>(N + 1);
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    // 부모인 물건의 개수를 반환하는 함수.
    // 물건 번호와 부모 물건 번호가 같으면 부모 물건이라는 것.
    int getParentCount() {
        int size = parent.size(), result = 0;
        for (int i = 1; i <= size; i++) if (parent[i] == i) result++;
        return result;
    }

    // 해당 물건의 최상위 부모를 찾는 함수
    int getParent(int N) {
        if (parent[N] == N) return N;
        return getParent(parent[N]);
    }

    // 두 개의 물건을 합치는 함수.
    // 두 물건의 부모 물건 번호를 같은 값으로 만든다.
    void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
};

int main() {
    int N, M, a, b;
    scanf("%d %d", &N, &M);
    Store store = Store(N);
    while (M--) {
        scanf("%d %d", &a, &b);
        // 이미 같은 그룹이면 넘어간다.
        if (store.getParent(a) == store.getParent(b)) continue;
        store.unionParent(a, b); // 최상위 부모 병합
    }
    printf("%d", store.getParentCount());
}