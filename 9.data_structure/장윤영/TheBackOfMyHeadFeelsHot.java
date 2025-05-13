package com.javatraining.datastructure;

import java.io.*;
import java.util.*;

public class TheBackOfMyHeadFeelsHot {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Timeout 방지
        StringBuilder sb = new StringBuilder();
        // 봉우리의 수
        int N = Integer.parseInt(br.readLine());

        // 봉우리의 높이
        String[] input = br.readLine().split(" ");
        int[] h = new int[N];
        for(int i = 0; i<N; i++){
            h[i] = Integer.parseInt(input[i]);
        }

        // 스택 선언
        Stack<Integer> stack = new Stack<>();
        // 각 신선에 대해 봉우리 탐색 및 출력
        for(int i = 0; i<N; i++){
            // 스택에 남아있는 신선들의 수
            sb.append(stack.size()).append(" ");

            // 오른쪽에 있는 신선의 봉우리 높이가 현재 신선의 봉우리 높이보다 낮거나 같은 모든 신선들을 pop
            while(!stack.isEmpty() && h[stack.peek()] <= h[i]){
                stack.pop();
            }

            // 현재 신선 스택에 추가
            stack.push(i);
        }
        System.out.println(sb.toString().trim());
    }
}
