package com.javatraining.binarysearch;

import java.io.*;
import java.util.*;

public class EscapeRoom {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 길이
        int N = Integer.parseInt(br.readLine());

        // 수열
        int[] A = new int[N];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(input[i]);
        }

        // 모니터에 출력될 수
        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        for(int i = 0; i<M; i++){
            B[i] = Integer.parseInt(br.readLine());
        }

        // StringBuilder는 Java에서 문자열을 빈번하게 이어붙이거나 수정할 때 사용되는 클래스
        // 매번 새로운 객체를 만들지 않고, 내부 버퍼에서 문자열을 효율적으로 수정
        StringBuilder sb = new StringBuilder();
        // A 배열 정렬
        // 이분 탐색은 정렬이 되어있어야 한다.
        Arrays.sort(A);
        for(int i=0; i<M; i++){
            // Arrays.binarySearch(배열,찾을 값)
            int index = Arrays.binarySearch(A,B[i]);
            // 배열에 값이 없으면 삽입 위치는 -(삽입위치 + 1) 로 음수가 나오게 된다.
            // Timeout
            // if(index >= 0){
            // 	System.out.println(1);
            // } else{
            // 	System.out.println(0);
            // }
            if(index >= 0){
                sb.append(1).append('\n');
            } else{
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
    }
}
