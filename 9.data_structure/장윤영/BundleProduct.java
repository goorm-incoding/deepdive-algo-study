package com.javatraining.datastructure;

import java.io.*;
import java.util.*;

public class BundleProduct {
    static int N, M;
    // 부모노드 배열
    static int[] parent;

    // find: 루트 노드 찾기
    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // union: 두 노드 합치기
    static void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY){
            parent[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        // 물건의 수
        N = Integer.parseInt(input[0]);
        // 작업의 수
        M = Integer.parseInt(input[1]);

        parent = new int[N + 1];  // 1번부터 N번까지 사용
        for(int i = 1; i < N+1; i++){
            // 처음에 각 원소가 자기 자신을 루트로 가지고 시작한다.
            parent[i] = i;
        }

        for(int i = 0; i < M; i++){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            union(a, b);
        }

        // 루트 노드 기준으로 Set에 넣어 중복 제거 후 묶음 개수 확인
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= N; i++){
            set.add(find(i)); // 각 물건의 루트 노드를 기준으로 집합 분류
        }

        System.out.println(set.size());
    }
}
