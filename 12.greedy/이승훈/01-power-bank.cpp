/*
[문제 설명]
보조배터리는 세가지 종류가 있다.
1. X타입 충전기로만 충전이 가능한 보조배터리
2. Y타입 충전기로만 충전이 가능한 보조배터리
3. X타입 또는 Y타입 충전기로 충전이 가능한 보조배터리

세 종류의 보조배터리 개수가 주어지고, 특정 타입을 충전할 수 있는 충전기가
(가격, 충전가능한 타입) N개 주어진다.

최대한 많은 보조배터리를 동시에 충전할 수 있게끔, 그리고 가장 저렴하게 구매해야한다.
동시에 충전할 수 있는 보조배터리의 개수와 충전기 가격의 합을 구하는 문제.

[해결 과정]
충전기를 가격으로 오름차순 정렬한 뒤, X, Y타입의 보조배터리를 위한 충전기부터 구매 후
그다음에 X 또는 Y타입 보조배터리를 위한 충전기를 구매한다.
*/

#include <cstdio>
#include <vector>
#include <algorithm>
struct Charger {
    int cost, type;
    bool operator<(const Charger& charger) const {
        if (cost == charger.cost) return type < charger.type;
        return cost < charger.cost;
    }
};

int main() {
    int require[3] = { 0 };
    int N, c, t, countSum = 0;
    long long costSum = 0;
    scanf("%d %d %d %d", &require[0], &require[1], &require[2], &N);
    std::vector<Charger> chargers(N);
    for (int i = 0; i < N; i++) scanf("%d %d", &chargers[i].cost, &chargers[i].type);
    std::sort(chargers.begin(), chargers.end());
    for (Charger charger : chargers) {
        int type = require[charger.type] ? charger.type : 2;
        if (require[type]) {
            countSum++;
            costSum += charger.cost;
            require[type]--;
        }
    }
    printf("%d %lld", countSum, costSum);
}