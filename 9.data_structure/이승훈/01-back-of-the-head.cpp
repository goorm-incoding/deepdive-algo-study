/*
산봉우리의 높이가 주어지고 산봉우리마다 신선이 서있다고 했을때,
각 산봉우리에 서있는 신선의 뒤통수를 볼 수 있는 신선의 인원을 구하는 문제.

다음 산봉우리의 높이가 자신이 서있는 산봉우리 높이보다 같거나 크면
그 다음 산봉우리를 보지 못한다.
A   B   C
4   4   7 일경우
A봉우리는 C봉우리를 보지 못한다.

특정 산봉우리를 볼 수있는 배열이 있다고할때, [a1, a2, a3, an...]
a1 > a2 > ... > an 을 만족해야 한다.
따라서 스택 자료구조를 사용한다.
높이를 스택에 넣기 전 상황의 스택 요소개수가 튀통수를 볼 수 있는 신선의 수고,
이후에 만약 스택의 최상단 요소가 넣으려고하는 높이보다 같거나 작으면
넣으려고 하는 높이보다 큰 높이가 나올때까지 요소를 뺀다.
(중복을 허용하지 않는 내림차순 구조를 유지하기 위함)

이를 코드로 구현한다.
*/

#include <cstdio>
#include <vector>
#include <stack>

int main() {
    int N, H;
    scanf("%d", &N);
    std::vector<int> results;
    std::stack<int> view;
    while (N--) {
        scanf("%d", &H);
        results.push_back(view.size());
        while (!view.empty() && view.top() <= H) view.pop();
        view.push(H);
    }
    for (int result : results) printf("%d ", result);
}