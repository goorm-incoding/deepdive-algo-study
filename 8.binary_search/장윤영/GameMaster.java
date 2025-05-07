package com.javatraining.binarysearch;

import java.io.*;

public class GameMaster {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 구름이가 현재까지 게임을 진행한 횟수
        long N = Long.parseLong(input[0]);
        // 승리한 횟수
        long M = Long.parseLong(input[1]);

        // 승률 구하는 방법(마스터 승률을 만들기 위한 설정)
        int curRate = (int)(M*100/N);
        // 마스터 승률
        int goalRate = curRate +1;

        // 이분탐색 조건을 위한 초기 설정
        // 최소 한번 이상은 이겨야 한다.
        long start = 1;
        // 10^12 미만
        long end = 999_999_999_999L;
        // 결과 저장, 최소 승리
        long result = 1_000_000_000_000L;

        // 이분 탐색 반복
        while (start<=end){
            // 중간값 설정
            long mid = (start + end)/2;
            // 중간값을 넣어서 이분 탐색 승률 계산
            int rate = (int) ((M+mid)*100/(N+mid));

            if(rate >= goalRate){
                // 조건 만족했으니 일단 정답 후보 저장 해놓는다
                result = mid;
                // 더 적은 시도로도 될 수 있는지 확인하러 왼쪽 탐색
                end = mid - 1;
            } else{
                // 조건에 맞지 않은 경우 더 높은 횟수를 찾아야 한다.
                start = mid + 1;
            }
        }

        // 최소 횟수의 범위 확인
        if(result == 1_000_000_000_000L){
            System.out.println("X");
        }else{
            System.out.println(result);
        }
    }
}
