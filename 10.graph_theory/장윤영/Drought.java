package com.javatraining.graphtheory;

import java.io.*;

public class Drought {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i<T; i++){

            String[] input = br.readLine().split(" ");
            // 지점의 개수 - 노드
            int N = Integer.parseInt(input[0]);
            // 수로의 개수 - 간선
            int M = Integer.parseInt(input[1]);

            int[] A = new int[M];
            int[] B = new int[M];

            for(int j = 0; j<M; j++){
                input = br.readLine().split(" ");
                A[j] = Integer.parseInt(input[0]);
                B[j] = Integer.parseInt(input[1]);
            }

            // 항상 모든 지점은 수로를 통해 연결되므로 노드에서 1개 뺀 값이 최소 간선의 개수이다.
            System.out.println(N-1);

        }
    }
}
