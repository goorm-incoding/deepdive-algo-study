package GOORM.이분탐색;

import java.io.*;
import java.util.*;

class 게임마스터 {
    /**
     * 승률을 1% 높이는 최소의 승리 횟수를 구하는 문제
     * 목표 승률이 100% 이상이면 아무리 승리해도 도달할 수 없으므로 X 출력 후 종료
     * 목표 승률 : ( (M * 100) / N ) + 1
     * 이진 탐색을 실행하며 ((M + mid) * 100) / (N + mid)가 목표 승률 이상인 가장 작은 mid를 구함
     */

    public static void main(String[] args) throws Exception {

        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long goalRate = (M * 100) / N + 1;

        if (goalRate >= 100) {
            System.out.println("X");
            return;
        }

        // 메인 로직
        long left = 0;
        long right = (long) Math.pow(10, 12) - 1;
        long result = -1;
        while (left <= right) {

            long mid = (right + left) / 2;
            long currentRate = ((M + mid) * 100) / (N + mid);
            // mid에 대한 승률이 목표 승률보다 크거나 같으면 result 갱신
            if ( currentRate >= goalRate ) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}