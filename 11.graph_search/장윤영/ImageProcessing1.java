package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class ImageProcessing1 {

    static int N, M;
    static String[] S = new String[150];
    static boolean[][] visited = new boolean[150][150];
    static int[] dirX = {0,0,-1,1};
    static int[] dirY = {1,-1,0,0};

    // dfs
    static int dfs(int x, int y){
        // 현재 위치의 픽셀 개수
        int current = 1;

        // 시작 위치 스택에 넣기
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x,y});

        // 시작 위치 방문 처리
        visited[x][y] = true;

        while(!stack.isEmpty()){

            int[] now = stack.pop();
            int cx = now[0];
            int cy = now[1];

            for(int d=0; d<4; d++){
                int nextX = cx+dirX[d];
                int nextY = cy+dirY[d];

                // 격자 범위 벗어나는지 확인
                // 방문 여부 확인
                if(nextX >= 0 && nextY >= 0 && nextX < M && nextY < N){
                    // 방문하지 않고 마스킹된 픽셀이면 탐색
                    if(!visited[nextX][nextY] && S[nextX].charAt(nextY) == '#'){
                        // 인접한 픽셀 방문 처
                        visited[nextX][nextY] = true;
                        // 다음 위치 탐색 준비
                        stack.push(new int[]{nextX,nextY});
                        // 물체 크기 증가
                        current++;
                    }
                }
            }
        }
        return current;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 사진의 가로 크기
        N = Integer.parseInt(input[0]);
        // 사진의 세로 크기
        M = Integer.parseInt(input[1]);

        // 마스킹 픽셀 문자열
        for(int i = 0; i<M; i++){
            S[i] = br.readLine();
        }

        // 물체의 개수
        int pixcel = 0;
        // 물체의 크기
        int pixcelSize = 0;

        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                // 방문하지 않고 처리가 된 픽셀인 경우 탐색
                if(!visited[i][j] && S[i].charAt(j) == '#'){
                    int size = dfs(i,j);
                    pixcel++;
                    pixcelSize = Math.max(pixcelSize, size);
                }
            }
        }

        System.out.println(pixcel);
        System.out.println(pixcelSize);
    }
}
