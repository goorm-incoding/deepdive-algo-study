package GOORM.이분탐색;

import java.io.*;
import java.util.*;

class 가장가까운점찾기 {

    /**
     * 수열에서 입력값과 가장 가까운 값을 출력하는 문제
     * 이진 탐색을 돌며 mid와 인접한 index(mid - 1, mid + 1) 사이에 입력값이 있다면 더 가까운 index를 반환
     */

    public static void main(String[] args) throws Exception {

        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        long[] points = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            points[i] = Long.parseLong(st.nextToken());
        }

        // 이진 탐색을 위한 정렬
        Arrays.sort(points);

        // 출력이 여러 줄이므로 성능 향상을 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();

        // Q 번 반복하며 각 입력에 대한 가장 가까운 index 계산
        while (Q-- > 0) {
            long input = Long.parseLong(br.readLine());
            int index = closestIndex(input, points);
            sb.append(points[index]).append("\n");
        }

        // 결과 출력
        System.out.println(sb);
    }

    // 메인 로직
    private static int closestIndex(long input, long[] points) {
        int left = 0;
        int right = points.length - 1;

        // 배열의 최소값보다 입력이 작으면 최소값 반환
        if (input <= points[left]) return left;
        // 배열의 최대값보다 입력이 크면 최대값 반환
        if (input >= points[right]) return right;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 입력값과 동일한 값이 있으면 반환
            if (points[mid] == input) return mid;

            // 현재 mid와 인접한 원소 사이에 input이 있는지 확인하고 있다면 더 가까운 값 반환
            // 인접한 두 원소로부터 거리가 같을 때 작은 index를 반환하도록 함
            if (mid > 0 && points[mid - 1] <= input && input <= points[mid]) {
                return Math.abs(points[mid - 1] - input) <= Math.abs(points[mid] - input) ? mid - 1 : mid;
            }
            if (mid < points.length - 1 && points[mid] <= input && input <= points[mid + 1]) {
                return Math.abs(points[mid] - input) <= Math.abs(points[mid + 1] - input) ? mid : mid + 1;
            }

            // mid와 인접한 요소 사이에 input이 없었으면 다음 탐색
            if (points[mid] > input) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 예외 처리
        return 0;
    }
}