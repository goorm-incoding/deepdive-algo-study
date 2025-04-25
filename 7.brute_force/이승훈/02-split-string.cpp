#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;

int main() {
    int N, result = -1;
    string str;
    cin >> N >> str;
    // 나누는 방법을 저장할 '배열' A와,
    // 부분문자열을 중복없이 저장할 'Set' P.
    // Set은 삽입 시 자동으로 중복을 제거하고 오름차순 정렬한다.
    vector<string> A; set<string> P;
    for (int i = 1; i < N - 1; i++) {
        for (int j = i + 1; j < N; j++) {
            // 기준점 두개를 두고 옮겨보면서 문자열 분리
            string str1 = str.substr(0, i);
            string str2 = str.substr(i, j - i);
            string str3 = str.substr(j, N - j);

            // 나누는 방법을 A 배열에 저장
            // 부분문자열을 P Set에 저장
            A.push_back(str1); P.insert(str1);
            A.push_back(str2); P.insert(str2);
            A.push_back(str3); P.insert(str3);
        }
    }

    // Set의 인덱스를 바로 구할 수 없기때문에 '배열'로 변환
    vector<string> PV(P.begin(), P.end());

    // 나누는 방법을 저장한 A배열을 3칸씩 확인하면서 점수 계산
    for (int i = 0; i < A.size(); i += 3) {
        int sum = 0;
        for (int j = 0; j < 3; j++) {
            // 각 방법의 요소를 부분문자열을 담은 배열에서 찾고, 인덱스 계산.
            auto it = find(PV.begin(), PV.end(), A[i + j]);
            sum += it - PV.begin() + 1; // 인덱스에 1을 더한 값을 누적
        }
        if (sum > result) result = sum; // 해당 누적값이 결과보다 크면 갱신
    }
    cout << result;
}