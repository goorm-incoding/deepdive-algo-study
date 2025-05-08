package com.javatraining.binarysearch;

import java.io.*;
import java.util.*;

public class FindingTheNearestPoint {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 점의 개수
        int N = Integer.parseInt(input[0]);
        // 질의의 개수
        int Q = Integer.parseInt(input[1]);

        // 각 점의 좌표
        long[] X = new long[N];
        input = br.readLine().split(" ");
        for(int i = 0; i<N; i++){
            X[i] = Long.parseLong(input[i]);
        }

        // 이분 탐색을 위한 정렬
        Arrays.sort(X);
        // Timeout 방지
        StringBuilder sb = new StringBuilder();

        // 각 질의
        for(int i = 0; i<Q; i++){
            long p = Long.parseLong(br.readLine());

            // 이분 탐색 조건
            int start = 0;
            int end = N-1;

            while(start<end){
                int mid = (start+end)/2;
                // 찾고자 하는 값보다 작으면 오른쪽 탐색
                if(X[mid] <p){
                    start = mid+1;
                }else{
                    end = mid;
                }
            }

            // 가장 가까운 점 찾기
            // 첫번째 점이 가장 가깝다
            if(start == 0){
                //System.out.println(X[start]);
                sb.append(X[start]).append("\n");
            }else{
                // 거리값 비교
                long p1 = Math.abs(p-X[start-1]);
                long p2 = Math.abs(p-X[start]);
                // 거리가 같은 경우 더 작은 값을 선택
                if(p1<=p2){
                    //System.out.println(X[start-1]);
                    sb.append(X[start-1]).append("\n");
                } else{
                    //System.out.println(X[start]);
                    sb.append(X[start]).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
