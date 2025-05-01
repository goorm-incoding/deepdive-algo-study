/*
음식의 이름과 개수가 주어지는데, 같은종류의 음식이 여러번 들어올 수 있다.
주어지는 정보를 모아 음식의 종류를 기준으로 사전순 오름차순 정렬하여 출력하는 문제

보자마자 map 자료구조가 떠올라야 한다.
C++ 에서의 map 자료구조는 key-value 쌍을 가지고, key 중복을 허용하지 않으며
key 기준으로 오름차순 정렬한다.

음식의 이름을 key로 하여, 이미 있는 key면 value를 증가시키고,
그렇지 않으면 새로운 key-value를 추가하는것을 반복한다.
*/

#include <iostream>
#include <string>
#include <map>

int main() {
    int N, A;

    // key: 문자열, value: 정수 형을 가지는 map 자료구조 foods
    std::map<std::string, int> foods;
    std::string name; // 음식 이름 문자열 변수
    std::cin >> N;
    while (N--) {
        // 표준입력으로부터 이름과 개수를 name, A 변수에 할당
        std::cin >> name >> A;
        if (foods.find(name) != foods.end()) {
            // foods map에서 name을 찾으려했는데 찾지못하면,
            // ==> 이미 있는경우
            foods[name] += A; // 기존 value에 A 누적
        } else {
            // ==> 그렇지 않으면 없는경우.
            foods.insert({ name, A }); // 새로운 key-value 삽입
        }
    }
    for (auto food : foods) {
        // map을 순회하면서 하나씩 출력
        // 표준출력으로 음식의 이름, 띄어쓰기(' '), 개수, 줄바꿈문자('\n') 출력
        std::cout << food.first << ' ' << food.second << '\n';
    }
}