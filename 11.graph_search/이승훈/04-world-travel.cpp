/*
[문제 설명]
N개의 나라와 M개의 항로가 있다.
나라는 1번부터 N번까지 차례대로 번호가 부여되어 있다.
구름이는 1번나라에 살고있다.

각 나라에는 사용할 수 있는 언어가 정해져있다.
언어는 총 10가지로, 1번부터 10번까지 번호가 부여되어 있다.

항로는 ((출발 나라 번호) (도착 나라 번호)) 형태로 주어지고,
양방향 통행이 가능하다.

구름이는 1번 나라부터 출발하여, 여행을 떠나려고 한다.
다만 구름이가 알고있는 언어를 사용하는 나라만 갈 수 있다.
구름이는 1번 나라의 언어를 알고있고, 존재하는 언어 중 하나를 더 배울 수 있다.
배울 언어를 적절하게 선택했을 때, 방문할 수 있는 서로 다른 나라의 최대 개수를 구하는 문제.
(방문할 수 있는 나라의 개수에는 1번 나라도 포함된다)

[해결 과정]
1. 나라 별 사용하고 있는 언어를 저장할 배열 (languages)
2. 인접 리스트 (adj)
3. 존재하는 언어 Set (langSet)

나라 별 사용하고 있는 언어를 입력받으면서, 바로 존재하는 언어 Set에 추가한다.
존재하는 언어를 하나씩 꺼내면서 BFS 탐색을 수행한다.
존재하는 언어에 대해 루프를 진행할때마다 BFS Queue, Visited, 방문한 나라 카운트를 초기화한다.
BFS 수행 중 다음 나라를 큐에 넣기 위한 조건으로,
1. 다음 나라에 이미 방문했음.
2. 다음 나라가 사용하는 언어가 1번 나라의 언어 또는 배운 언어(language)가 아님.
두 조건 중 하나라도 만족하면 큐에 넣지않고 루프를 넘어간다.
존재하는 언어에 대해 루프 한번이 끝날때 마다 현재 루프에서 구한 나라 카운트가
최대 카운트보다 크다면 최대 카운트를 갱신한다.
마지막으로 최대 카운트를 출력하고 끝낸다.
*/

#include <cstdio>
#include <vector>
#include <set>
#include <queue>
using namespace std;

int main() {
    int N, M, p, q, maxCount = 0;
    scanf("%d %d", &N, &M);
    vector<int> languages(N + 1); // 나라 별 언어정보
    vector<set<int>> adj(N + 1); // 항로 정보 (양방향)
    set<int> langSet; // 사용가능한 언어 Set (중복 X, 오름차순 정렬)
    for (int i = 1; i <= N; i++) {
        scanf("%d", &languages[i]); // 나라 별 언어정보 입력
        langSet.insert(languages[i]); // 사용가능한 언어 Set에 삽입
    }
    while (M--) {
        scanf("%d %d", &p, &q);
        adj[p].insert(q); // 양방향 간선
        adj[q].insert(p);
    }
    for (int language : langSet) { // 사용가능한 언어 순회
        // 언어에 대해 한번 루프돌때마다 Queue와 Visited 초기화
        queue<int> q; vector<bool> visited(N + 1);
        q.push(1); visited[1] = true; // 1번 나라부터 출발
        int countryCount = 1; // 1번 나라도 방문한 나라에 포함
        while (!q.empty()) {
            int current = q.front(); q.pop(); // 탐색할 나라 번호
            for (int next : adj[current]) { // 현재 나라에서 갈 수 있는 나라 순회
                int nextLang = languages[next]; // 다음 나라가 사용하는 언어 번호
                if (visited[next]) continue; // 다음 나라 방문했으면 루프 생략
                
                // 다음 나라가 사용하는 언어가 1번 나라의 언어 또는 배운 언어가 아니라면 루프 생략
                if (!(nextLang == languages[1] || nextLang == language)) continue;
                q.push(next); visited[next] = true; // 생략되지 않았으면 다음 나라를 방문함
                countryCount++; // 방문한 나라 +1
            }
        }
        
        // 현재 루프에서 방문한 나라가 최대 나라 개수보다 크다면 최대 나라 개수 갱신
        if (countryCount > maxCount) maxCount = countryCount;
    }
    printf("%d", maxCount); // 최대 나라 개수 출력하고 끝냄
}