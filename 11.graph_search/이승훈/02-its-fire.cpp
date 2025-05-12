/*
[문제 요약]
R × C 크기의 지도 데이터가 주어진다.
발화점(@) 에서 불이 붙기 시작하면, 불은 1초가 지날때마다 상하좌우로 번진다.
구름이가 있는 위치(&)의 상하좌우에 불이 붙으면 구름이가 비상구로 탈출하게 된다.
비상구로 탈출하기 전까지 걸린 시간을 구하는 문제.

[해결 과정]
BFS로 그래프 탐색을 수행하는데, 발화점으로부터 구간을 몇번 거쳤는지 횟수를 누적한다.
*/

#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;
struct YX { 
    int y, x;
    YX operator+(const YX &v) const {
        return { y + v.y, x + v.x };
    }
};

int main() {
    int R, C, min = __INT_MAX__;
    cin >> R >> C;
    string str;

    // 발화점으로부터 각 칸까지의 거리를 저장할 2차원 배열
    // 못 지나간다는 뜻인 -2로 모두 초기화한다.
    vector<vector<int>> lab(R, vector<int>(C, -2));
    vector<vector<bool>> visited(R, vector<bool>(C)); // BFS 방문확인 2차원배열
    queue<pair<YX, int>> q;
    YX goorm; // 구름이의 위치
    YX dirs[4] = {{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
    for (int i = 0; i < R; i++) {
        cin >> str; // 연구실 한 줄 입력
        for (int j = 0; j < C; j++) { // 입력받은 줄을 한칸씩 확인
            if (str[j] == '&') goorm = { i, j }; // '&'이라면 구름이의 위치 갱신

            // '.'이라면 아직 불이 붙지 않았지만 지나갈 수 있음을 의미하는 -1로 설정
            else if (str[j] == '.') lab[i][j] = -1;
            else if (str[j] == '@') { // '@'이라면 발화점을 의미하는 0으로 설정
                q.push({{ i, j }, 0});
                lab[i][j] = 0;
                visited[i][j] = true;
            }
        }
    }
    while (!q.empty()) {
        // BFS 수행
        YX current = q.front().first; // 현재 칸 좌표
        int step = q.front().second; q.pop(); // 현재 칸까지의 거리
        for (YX dir : dirs) {
            YX next = current + dir; // 다음 좌표 계산

            // 다음좌표가 좌표 범위를 벗어나면 무시.
            // 또한 이미 방문한 칸이거나 지나갈 수 있는 칸(-1)이 아니면 무시
            if (next.y < 0 || next.y >= R || next.x < 0 || next.x >= C) continue;
            if (visited[next.y][next.x] || lab[next.y][next.x] != -1) continue;
            lab[next.y][next.x] = step + 1; // 다음칸의 거리 정보를 현재 거리정보 +1 로 설정
            visited[next.y][next.x] = true; // 다음칸 방문했다고 표시
            q.push({{ next.y, next.x }, step + 1}); // 다음칸 거리하나 증가시키고 큐에 삽입
        }
    }
    for (YX dir : dirs) {
        // 구름이 위치 기준 상하좌우 확인
        YX next = goorm + dir; // 다음 좌표 계산
        if (next.y < 0 || next.y >= R || next.x < 0 || next.x >= C) continue;
        int v = lab[next.y][next.x]; // 다음 칸 거리정보
        if (v >= 0 && v < min) min = v; // 불이 붙었고(거리정보 0이상), 최소값 조건을 만족하면 갱신
    }

    // 최소값이 변경되지않았다면 불이 붙을 수 없는 것. -1 출력
    // 그게 아니라면 최소 거리정보 출력 (min 기본값은 int 최대값)
    printf("%d", min == __INT_MAX__ ? -1 : min);
}