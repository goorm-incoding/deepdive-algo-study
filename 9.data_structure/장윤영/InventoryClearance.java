package com.javatraining.datastructure;

import java.io.*;
import java.util.*;

public class InventoryClearance {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정보의 개수
        int N = Integer.parseInt(br.readLine());
        // <음식의 종류, 개수>
        // TreeMap는 자동으로 key를 사전순으로 정렬
        Map<String,Integer> food = new TreeMap<>();

        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            String S = input[0];
            int A = Integer.parseInt(input[1]);

            // .getOrDefault 키가 없을 때 기본값을 지정해주는 메서드
            // Map에 값이 존재하면 기존 값을 꺼내서 누적하고, 없으면 0부터 시작
            food.put(S,food.getOrDefault(S,0)+A);
        }

        StringBuilder sb = new StringBuilder();
        for(String key : food.keySet()){
            sb.append(key).append(" ").append(food.get(key)).append("\n");
        }
        System.out.println(sb);
    }
}
