/*
[문제 설명]
이동 가능한 지역, 이동 불가능한 지역으로 이루어진 지도와 M개의 (출발 좌표, 도착 좌표) 쌍이 주어진다.
구름이는 택시 기사로, 순수익을 계산해야 한다.
택시 운행 중 이동한 총 거리 1당 Z의 통행료가 부과된다.
손님을 출발 좌표에서 태우고, 도착 좌표에서 내려준다.
수익은 X의 기본 요금은 무조건 받고, 만약 이동 거리가 5를 초과한다면 초과한 거리 1당 Y의 추가요금을 더한다.
그리고 다음 손님의 위치로 이동한다. 이 때 이동하는 거리는 총 거리에 포함된다.
순수익은 손님을 태우고 번 수익에서 통행료를 뺀 금액이다.

[해결 과정]
1.  입력
2.  만약 첫 손님일 경우 구름이의 위치를 첫 손님의 출발 좌표로 설정
3.  구름이의 위치로부터 손님의 출발 위치까지의 거리를 계산하고 총 거리 누적 후
    구름이의 위치를 손님의 출발 위치로 갱신
4.  손님을 태우고 도착 위치까지의 거리(유효 거리)를 계산 후
    구름이의 위치를 손님의 도착 위치로 갱신
5.  유효 거리를 총 거리에 누적
6.  수익에 기본요금을 누적하고, 거리에 따른 추가요금 누적
7.  순수익(수익 - 통행료)을 계산하여 출력

각 손님에 대해 2 ~ 6 과정을 반복한다.
*/

#include <cstdio>
#include <vector>
#include <queue>
using namespace std;
struct YX { 
    int y, x;
    YX operator+(const YX &v) const {
        return { y + v.y, x + v.x };
    }
} dirs[4] = {{ -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};

// start 위치로부터 end 위치까지의 거리를 BFS 알고리즘으로 구하여 반환하는 함수
int distance(vector<vector<int>> &board, YX &start, YX &end) {
    // 좌표를 인덱스로 사용하기 위해 board 2차원 배열은 이미 (N + 1) X (N + 1) 크기.
    // 실제 입력받았던 N을 구하기 위해서는 board 배열 크기에서 1을 빼줘야 함.
    int N = board.size() - 1;

    vector<vector<bool>> visited(N + 1, vector<bool>(N + 1));
    vector<vector<int>> distance(N + 1, vector<int>(N + 1, -1));
    queue<YX> q;
    visited[start.y][start.x] = true;
    distance[start.y][start.x] = 0; // 시작 위치 거리 0으로 초기화
    q.push(start);
    while (!q.empty()) {
        YX current = q.front(); q.pop();
        for (YX dir : dirs) {
            YX next = current + dir;
            
            // 조건필터링
            // 다음 좌표가 좌표 범위를 벗어나거나, 갈 수 없는 좌표 또는 이미 방문한 좌표라면 생략
            if (next.y < 1 || next.y > N || next.x < 1 || next.x > N) continue;
            if (board[next.y][next.x] == 1 || visited[next.y][next.x]) continue;
            q.push(next);

            // 다음 좌표의 거리를 (현재 위치까지의 거리 + 1)로 설정
            distance[next.y][next.x] = distance[current.y][current.x] + 1;

            visited[next.y][next.x] = true;
        }
    }

    // 거리 2차원 배열은 모든칸이 -1로 초기화 된 상태,
    // '어떤 이동 가능한 지역에서 항상 다른 이동 가능한 지역으로 이동할 수 있다' 라는 조건에 의해
    // -1을 반환하는 경우는 절대 없음.
    return distance[end.y][end.x];
}

int main() {
    int N, M, X, Y, Z, a, b, c, d, totalDistance = 0, profit = 0;
    YX goorm;

    // 1. 입력
    scanf("%d %d %d %d %d", &N, &M, &X, &Y, &Z);
    vector<vector<int>> board(N + 1, vector<int>(N + 1));
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            scanf("%d", &board[i][j]);
        }
    }

    // 손님에 대해 반복
    for (int i = 0; i < M; i++) {
        scanf("%d %d %d %d", &a, &b, &c, &d); // 시작, 도착 정보 입력
        YX start = { b, a }, end = { d, c }; // Y, X 순서기때문에 각각 뒤집음

        // 2.   만약 첫 손님일 경우 구름이의 위치를 첫 손님의 출발 좌표로 설정
        if (i == 0) goorm = start;

        // 3.   구름이의 위치로부터 손님의 출발 위치까지의 거리를 계산하고 총 거리 누적 후
        //      구름이의 위치를 손님의 출발 위치로 갱신
        totalDistance += distance(board, goorm, start); goorm = start;

        // 4.   손님을 태우고 도착 위치까지의 거리(유효 거리)를 계산 후
        //      구름이의 위치를 손님의 도착 위치로 갱신
        int fareDistance = distance(board, goorm, end); goorm = end;

        // 5.   유효 거리를 총 거리에 누적
        totalDistance += fareDistance;

        // 6.   수익에 기본요금을 누적하고, 거리에 따른 추가요금 누적
        profit += X;
        if (fareDistance > 5) profit += (fareDistance - 5) * Y;
    }

    // 7. 순수익(수익 - 통행료)을 계산하여 출력
    printf("%d", profit - (totalDistance * Z));
}