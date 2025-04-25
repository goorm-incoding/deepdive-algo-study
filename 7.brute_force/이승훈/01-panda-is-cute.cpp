#include <cstdio>
#include <cmath>
#include <set>

// 좌표를 다루기 편하게 사용자 정의 자료형 선언
struct Coord { 
    int y, x; // 좌표 y, x를 가진다.
    
    // 해당 자료형을 Set에서 사용하려면 중복판단을 위해 자료형 간 비교가 가능해야 한다.
    // 이를 위해 해당 자료형 간 '<' 연산을 구현.
    // y를 기준으로 먼저 비교후 같으면 x를 비교한다.
    bool operator<(const Coord &c) const {
        if (y == c.y) return x < c.x;
        return y < c.y;
    }

    // 대상 좌표와의 거리를 계산하는 함수
    // 문제에 나와있는 거리 계산 공식을 사용한다.
    int getDistance(const Coord &target) {
        return pow(y - target.y, 2) + pow(x - target.x, 2);
    }
};

// 문제를 클래스로 생각하여 구현
class Forest {
    int N, M; // 숲의 행, 열
    std::set<Coord> pandas; // 판다의 좌표를 담고있는 Set, 중복은 없다.

    public:
    // 클래스 생성자.
    // 숲의 가로 세로를 입력받아 초기화한다.
    Forest(int N, int M) {
        this->N = N; this->M = M;
    }

    // 판다의 좌표를 받아 판다의 좌표를 담고있는 Set에 삽입하는 함수
    void setPanda(int R, int C) {
        pandas.insert({ R, C });
    }

    // 특정 칸의 불만족도를 구하는 함수
    // 판다 좌표 Set에서 판다의 좌표를 하나씩 꺼내서
    // 현재 위치를 기준으로 판다까지의 거리를 구한 뒤 합하여 불만족도를 반환한다.
    int getValue(Coord &current) {
        int value = 0;
        for (const Coord &panda : pandas) {
            value += current.getDistance(panda);
        }
        return value;
    }

    // 판다가 없는 칸을 모두 돌면서 불만족도를 구한 뒤,
    // 최소값을 반환하는 함수
    int getMinValue() {
        int result = __INT_MAX__;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Coord current = { i, j };
                if (pandas.find(current) != pandas.end()) {
                    // 현재 위치에 판다가 있을때 계산하지 않고 넘어감
                    continue;
                }
                int value = getValue(current);
                if (value < result) result = value;
            }
        }
        return result;
    }
};

int main() {
    /*
    숲의 크기와 판다의 좌표 여러개가 주어집니다.
    판다의 좌표가 아닌 곳의 불만족도를 구하고
    불만족도가 가장 낮은칸의 불만족도롤 출력해야합니다.

    문제에서의 불만족도는, 기준 좌표로부터 판다가 있는 좌표까지의 거리의 합입니다.
    1. 판다가 없는 칸을 기준으로 불만족도를 구해야한다.
    2. 판다가 있는 칸에 대해서만 거리를 구하면된다.
    이 두개만 생각한다면, 배열은 필요없습니다.

    1. 숲의 크기를 이용하여 2중 for문을 수행합니다.
    2. 만약 그 좌표가 판다 좌표 Set에 있을경우 무시하고 넘깁니다.
    3. 그게 아니라면 판다 좌표 Set에 있는 좌표를 하나씩 꺼내보면서 불만족도를 구합니다.
    */
    int N, M, K;
    scanf("%d %d %d", &N, &M, &K);
    Forest forest = Forest(N, M);
    for (int i = 1, R, C; i <= K; i++) {
        scanf("%d %d", &R, &C);
        forest.setPanda(R, C);
    }
    printf("%d", forest.getMinValue());
}