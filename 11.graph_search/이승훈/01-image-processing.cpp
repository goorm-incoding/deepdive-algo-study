/*
[문제 요약]
'.', '#' 두 문자로 이루어진 격자형태의 사진이 주어진다.
마스킹 처리한 픽셀인 '#' 문자의 경우, 서로 다른 픽셀이 인접하면 같은 물체라 할 수 있다.
해당 물체의 개수와 가장 큰 물체의 크기를 구하는 문제.

[해결 과정]
'#' 문자에 한해서만 DFS 또는 BFS 를 수행하면된다.
수행횟수가 물체의 개수고, 한 물체에 대해 탐색 수행 시 Stack 또는 Queue에 삽입되는 횟수가
물체의 크기다.
*/

#include <iostream>
#include <vector>
#include <string>
#include <queue>
using namespace std;
struct YX { int y, x; }; // 좌표 및 벡터를 다루기 위한 사용자 구조체

int main() {
    int N, M, objectCount = 0, objectSize = 0;
    cin >> N >> M;
    vector<string> image(M); // 사진 픽셀 격자 2차월 배열
    vector<vector<bool>> visited(M, vector<bool>(N)); // BFS 탐색에 사용할 방문확인 배열
    vector<YX> dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우 벡터값
    for (int i = 0; i < M; i++) cin >> image[i]; // 한줄씩 사진 데이터 입력
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            // 모든 픽셀을 순회
            // 만약 픽셀이 '#'이고, 방문하지 않은 픽셀이라면
            if (image[i][j] == '#' && !visited[i][j]) {
                // BFS 수행
                queue<YX> q;

                // 현재 탐색중인 물체의 크기를 1로 설정하고, 전체 물체 개수 +1
                int currentSize = 1; objectCount++;
                q.push({ i, j });
                visited[i][j] = true;
                while (!q.empty()) {
                    // BFS 알고리즘
                    YX current = q.front(); q.pop();
                    for (YX dir : dirs) {
                        // 상하좌우 픽셀탐색
                        YX next = { current.y + dir.y, current.x + dir.x };

                        // 다음 픽셀이 사진 범위를 벗어나면 탐색을 수행하지 않는다.
                        // 다음 픽셀이 '.'이라면('#'이 아니라면) 탐색을 수행하지 않는다.
                        // 다음 픽셀이 방문한 픽셀이라면 탐색을 수행하지 않는다.
                        if (next.y < 0 || next.y >= M || next.x < 0 || next.x >= N) continue;
                        if (image[next.y][next.x] == '.') continue;
                        if (visited[next.y][next.x]) continue;

                        // 유효한 픽셀이라면 다음으로 탐색할 픽셀로 설정하고
                        // 현재 탐색중인 물체의 크기를 +1, 그리고 다음 픽셀 방문처리.
                        q.push(next); currentSize++;
                        visited[next.y][next.x] = true;
                    }
                }

                // 만약 현재 BFS로 탐색한 물체의 크기가 가장 큰 물체의 크기보다 크다면
                // 크기가 가장 큰 물체의 크기를 현재 탐색한 물체의 크기로 설정
                if (currentSize > objectSize) objectSize = currentSize;
            }
        }
    }

    // 출력
    cout << objectCount << '\n' << objectSize;
}