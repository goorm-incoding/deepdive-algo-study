#include <cstdio>
#include <vector>
enum Status { STATUS_INIT, STATUS_FINISHED };
struct Pole { int id; std::vector<int> disks; };

/*
* 막대기 A, B, C 가 있고 원판이 N개라고 할 때, A 에서 C로 원판을 모두 옮기려면
* 1. A의 N-1개를 B(경유지)에 옮긴 후 A의 제일 큰 원판을 C로 옮긴다.
* 2. B(경유지)에 있는 N-1개를 C로 옮긴다.
* 단, 원판이 한개라면 그냥 옮긴다 (종료 조건)
* 이를 재귀적으로 반복하면 된다.
*/

class Hanoi {
    Pole poles[3];
    Status status = STATUS_INIT;
    int limit, cnt = 0;

    public:
    Hanoi(int N, int K) {
        // Hanoi 클래스 초기화, 막대기 3개의 이름을 1, 2, 3으로 설정
        // 1번 막대에 20개의 원판 삽입
        for (int i = 1; i <= 3; i++) poles[i - 1].id = i;
        for (int i = N; i >= 1; i--) poles[0].disks.push_back(i);

        // 제한 이동횟수 설정
        limit = K;
    }

    void move(Pole &from, Pole &to) {
        // 실제로 원판을 옮기는 함수
        // from 막대기에 있는 원판을 빼고 to 막대기에 꽂음
        int fromDisk = *from.disks.rbegin(); from.disks.pop_back();
        to.disks.push_back(fromDisk);
        // printf("%d -> %d\n", from.id, to.id);
        cnt++;
    }

    void step(int n, Pole &from, Pole &to, Pole &temp) {
        // 재귀 함수. limit를 이용하여 이동횟수를 제한한다.
        if (cnt == limit) return;
        if (n == 1) {
            move(from, to);
            return;
        }
        step(n - 1, from, temp, to);
        if (cnt == limit) return;
        move(from, to);
        step(n - 1, temp, to, from);
    }

    void start() {
        // 원판 이동 시작 함수
        // 이미 이동된상태라면 실행하지 않는다.
        // 실행 완료 후 STATUS_FINISHED 설정한다.
        if (status == STATUS_FINISHED) return;
        step(poles[0].disks.size(), poles[0], poles[2], poles[1]);
        status = STATUS_FINISHED;
    }

    void printSize() {
        // 각 막대기 별 원판 합을 구하여 출력하는 함수
        int size[3] = { 0 };
        for (int i = 0; i < 3; i++) {
            for (int disk : poles[i].disks) size[i] += disk;
        }
        printf("%d %d %d", size[0], size[1], size[2]);
    }
};

int main() {
    int K;
    scanf("%d", &K);
    Hanoi hanoi = Hanoi(20, K);
    hanoi.start();
    hanoi.printSize();
}