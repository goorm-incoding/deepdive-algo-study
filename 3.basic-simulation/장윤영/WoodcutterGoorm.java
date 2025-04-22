package com.javatraining.basicsimulation;

import java.io.*;
import java.util.*;

public class WoodcutterGoorm {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 나무 개수
        int N = Integer.parseInt(input[0]);
        // 벌목 높이 조건
        int M = Integer.parseInt(input[1]);
        // 시작 위치, 배열의 인덱스는 0부터 시작하니까 시작 위치를 하나 줄여서 맞춘다
        int x = Integer.parseInt(input[2])-1;

        // 초기 나무 높이
        int[] H = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 벌목 반복 횟수
        int Q = Integer.parseInt(br.readLine());

        // 벌목 움직임 명령어
        String[] D = br.readLine().split(" ");

        // 소지한 목재량
        long result = 0;

        for(int i = 0; i<Q; i++){

            // 벌목 조건에 맞으면 벌목
            if (H[x]+i >= M){
                // 시간이 지나면서 자라는 것을 i로 표현
                result += H[x] + i;
                // 벌목 하고 난 뒤 높이
                H[x] -= H[x] + i;
            }

            // 명령어 별 위치이동
            switch(D[i]){
                case "L":
                    x = (x-1+N)%N;
                    break;
                case "R":
                    x = (x+1)%N;
                    break;
                case "S":
                    break;
            }
        }

        System.out.println(result);
    }
}
