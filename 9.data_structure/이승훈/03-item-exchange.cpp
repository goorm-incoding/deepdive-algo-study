/*
구름이가 가지고 있는 아이템의 목록, 친구가 가지고 있는 아이템의 목록이 주어지고,
서로 교환해야할 아이템 A, B가 주어지면 구름이가 A를 가지고 있고, 친구가 B를 가지고 있을때
서로 아이템을 교환한다.

필요한 동작
1. 목록에서 아이템이 포함되어있는지 확인
2. 포함되어 있다면 각각 아이템을 삭제하고 교환받은 아이템을 다시 삽입
3. 아이템 목록은 항상 사전순으로 정렬되어 있어야 함.

빈번한 리스트 조회, 삭제, 삽입이 빨라야 한다. ==> set 자료구조 사용
*/

#include <iostream>
#include <string>
#include <set>
using std::string;

bool hasItem(std::set<string> &target, string name) {
    // set에 name 요소가 있는지 확인, 있으면 true, 없으면 false
    return target.find(name) != target.end();
}

int main() {
    int N, M;
    string name, A, B;
    std::set<string> goorm, friend_; // 아이템을 저장할 set 자료구조
    std::cin >> N >> M;
    for (int i = 0; i < N; i++) {
        // 구름 아이템 입력 및 삽입
        std::cin >> name;
        goorm.insert(name);
    }
    for (int i = 0; i < N; i++) {
        // 친구 아이템 입력 및 삽입
        std::cin >> name;
        friend_.insert(name);
    }
    while (M--) {
        std::cin >> A >> B;
        if (hasItem(goorm, A) && hasItem(friend_, B)) {
            // 구름이 A를 가지고 있고 친구가 B를 가지고 있을때,
            // 구름 아이템 리스트에서 A를 지우고 B 삽입.
            // 친구 아이템 리스트에서 B를 지우고 A 삽입.
            goorm.erase(A); goorm.insert(B);
            friend_.erase(B); friend_.insert(A);
        }
    }

    // 구름 아이템 목록 출력
    for (string item : goorm) std::cout << item << ' ';
}