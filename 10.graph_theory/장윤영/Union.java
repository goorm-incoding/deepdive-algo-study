package com.javatraining.graphtheory;

import java.io.*;

public class Union {

    // 섬의 개수
    static int N;
    // 단방향 연결 정보를 저장하는 인접 행렬
    static boolean[][] matrix;
    // 방문 배열
    static boolean[] visited;
    // 양방향 확인
    static boolean[][] graph;

    // dfs
    static void dfs(int current){
        visited[current] = true;
        for(int next = 1; next<N+1; next++){
            // 다음 섬이 현재 섬과 양방향으로 연결되어 있고, 아직 방문하지 않았다면 탐색 계속
            if(graph[current][next] == true && !visited[next]){
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 섬의 개수
        N = Integer.parseInt(input[0]);
        // 다리의 개수
        int M = Integer.parseInt(input[1]);

        // 인접행렬 초기화
        matrix = new boolean[N+1][N+1];
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            // 단방향 다리 등록
            matrix[s][e] = true;
        }

        // 방문 배열
        visited = new boolean[N+1];
        // 양방향 연결인 경우에만 graph에 기록
        graph = new boolean[N+1][N+1];
        for(int i = 1; i<N+1; i++){
            for(int j = 1; j<N+1; j++){
                // i → j 와 j → i 둘 다 존재하면 양방향
                if(matrix[i][j] && matrix[j][i]){
                    graph[i][j] = true;
                }
            }
        }

        // 연합의 개수
        int count = 0;
        for(int i = 1; i<N+1; i++){
            if(!visited[i]){
                dfs(i);
                count++;
            }
        }
        System.out.println(count);
    }
}
