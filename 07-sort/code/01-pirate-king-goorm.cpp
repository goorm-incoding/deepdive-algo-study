#include <cstdio>
#include <utility>
#include <vector>
#include <algorithm>

// 섬의 좌표쌍을 별도의 타입으로 지정하고,
// 해당 타입과 섬의 인덱스를 묶어 섬 타입을 정의한다.
typedef std::pair<int, int> Pos;
typedef std::pair<Pos, int> Island;

int main() {
    int N, x, y; // 변수 선언
    scanf("%d", &N);
    std::vector<Island> islands(N); // 섬 배열
    std::vector<int> result(N); // 인덱스 차 결과 배열
    for (int i = 0; i < N; i++) {
        // 좌표쌍을 입력받고, 섬 인덱스와 묶어 섬 배열에 저장
        scanf("%d %d", &x, &y);
        islands[i] = {{ x, y }, i};
    }

    // 정렬
    std::sort(islands.begin(), islands.end());

    // 저장해 둔 섬 인덱스와 정렬 결과 인덱스를 이용하여
    // 뒤에 섬이 몇개 존재하는지 계산하여 결과 배열에 저장 후 출력
    for (int i = 0; i < N; i++) result[islands[i].second] = N - 1 - i;
    for (int i = 0; i < N; i++) printf("%d\n", result[i]);
}