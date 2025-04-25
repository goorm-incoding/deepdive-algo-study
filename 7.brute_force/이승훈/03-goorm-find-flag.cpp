#include <cstdio>
#include <vector>
using std::vector;
struct Coord { int y, x; };
int main() {
    // 탐색할 좌표를 기준점과의 Y, X차로 표현하여 저장.
    Coord dir[8] = {
        { -1, -1 }, { -1, +0 }, { -1, +1 },
        { +0, -1 },             { +0, +1 },
        { +1, -1 }, { +1, +0 }, { +1, +1 }
    };
    int N, K, cnt = 0;
    scanf("%d %d", &N, &K);
    vector<vector<int>> board(N, vector<int>(N));

    // 게임판 입력
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            scanf("%d", &board[i][j]);
        }
    }

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            // 모든 좌표를 순회하는데, 구름이 있는 칸이면 넘어간다
            if (board[y][x] == 1) continue;
            int sum = 0;
            for (int k = 0; k < 8; k++) {
                // 8방향 탐색
                int nY = y + dir[k].y;
                int nX = x + dir[k].x;
                if (!(nY >= 0 && nY < N) || !(nX >= 0 && nX < N)) continue;
                if (board[nY][nX] == 1) sum++; // 탐색하는 좌표에 구름이 있으면 카운트한다.
            }
            if (sum == K) cnt++;
        }
    }
    printf("%d", cnt);
}