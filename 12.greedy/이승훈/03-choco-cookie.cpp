/*
[문제 설명]
맛 수치가 있는 쿠키가 N개 있다.
구름이는 N개의 쿠키를 매일 하나씩 먹기로 했다.
쿠키의 맛 수치는 하루가 지날때마다 1 감소한다.
구름이가 먹은 모든 쿠키의 맛있는 정도의 곱을 최대화 해야 한다.
구름이가 쿠키를 최대한 맛있게 모든 쿠키를 먹는 순서를 구하는 문제.
최대한 맛있게 모든 쿠키를 먹는 순서가 여러가지라면, 사전순으로 출력.

[해결 과정]
맛 수치가 0인 쿠키를 하나라도 먹으면 맛있는 정도의 곱은 0이 되어버린다.
맛 수치가 작은것부터 빨리 먹어야한다.
만약 작은것부터 차례대로 먹어도 맛 수치가 0이 되어버리는 쿠키가 하나라도 있다면
뭘 해도 0이 되는 경우이므로 동일한 결과에 대한 먹는 순서가 여러가지라면
사전순으로 출력한다는 조건에 따라 1부터 N까지 차례대로 출력한다.

1. (맛 수치, 쿠키 번호) 형태로 배열에 입력 후 맛 수치 기준으로 오름차순 정렬
2. 정렬된 순서대로 차례대로 먹었을 때 만약 맛 수치가 0인 쿠키를 먹게된다면 불가능 표시
3. 가능 여부에 따라 순서 출력
    A. 가능하다면 정렬된 결과의 '쿠키 번호'를 차례대로 출력
    B. 불가능하다면 1부터 N까지 차례대로 숫자 출력
*/

#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N;
    bool isPossible = true;
    scanf("%d", &N);

    // cookies[idx].first: 맛 수치, cookies[idx].second: 쿠키 번호
    vector<pair<int, int>> cookies(N);
    for (int i = 0; i < N; i++) {
        cookies[i].second = i + 1;
        scanf("%d", &cookies[i].first);
    }
    sort(cookies.begin(), cookies.end());
    for (int i = 0; i < N; i++) {
        if (cookies[i].first - i <= 0) {
            isPossible = false;
            break;
        }
    }
    for (int i = 0; i < N; i++) {
        printf("%d ", isPossible ? cookies[i].second : i + 1);
    }
}