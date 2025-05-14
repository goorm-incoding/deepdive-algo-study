package com.javatraining.graphtheory;

import java.io.*;

public class ReleaseOfTheBomb {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 기폭 장치의 개수
        int N = Integer.parseInt(input[0]);
        // 전선의 개수
        int M = Integer.parseInt(input[1]);

        // 입력 받은 노드를 A와 B를 배열로 따로 나누고 전선이 연결되어 있는 노드에 전선의 개수를 샌다.
        // 노드와 인덱스를 맞춰서 +1을 해준다
        int[] A = new int[M+1];
        int[] B = new int[M+1];
        // 노드 별 전선의 개수
        int[] edge = new int[N+1];

        // 전선의 개수는 두 노드에 연결되므로 1개씩 빼서 입력 받는다.
        // 노드의 0번째 인덱스는 사용하지 않는다.
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            A[i+1] = Integer.parseInt(input[0]) -1;
            B[i+1] = Integer.parseInt(input[1]) -1;
            // A노드의 전선 수 증가
            edge[A[i+1]]++;
            // B노드의 전선 수 증가
            edge[B[i+1]]++;
        }

        // 폭탄 해제 반복
        boolean cut = false;

        for(int i = 1; i<M+1; i++){
            // 두 노드의 전선의 개수가 1개라면 끊을 수 없다.
            if(edge[A[i]] == 1 || edge[B[i]] == 1){
                continue;
            }
            cut = true;
            System.out.print(i+" ");
        }

        // 안전한 전선이라고 할 수 있는 전선이 없는 경우
        if(!cut){
            System.out.println("-1");
        }
    }

}
