package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class Fire {

    static final int MAX = 1000;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    // 행, 열의 크기
    static int R, C;
    // 각 칸까지 불이 도달하는 시간
    static int[][] dist = new int[MAX][MAX];
    // 방문 배열
    static boolean[][] visited = new boolean[MAX][MAX];
    // 지도 정보
    static String[] S = new String[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        // 각 행의 문자열 입력받기
        for (int i = 0; i < R; i++) {
            S[i] = br.readLine();
        }

        Queue<int[]> q = new LinkedList<>();

        // 불의 위치 모두 큐에 담기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 불의 시작점
                if (S[i].charAt(j) == '@') {
                    q.add(new int[]{i, j});
                    // 시작점의 거리 0
                    dist[i][j] = 0;
                    // 방문 표시
                    visited[i][j] = true;
                }
            }
        }

        // BFS 시작
        while (!q.isEmpty()) {
            // 현재 위치 좌표
            int[] curr = q.poll();
            int i = curr[0];
            int j = curr[1];

            // 상하좌우 비교
            for (int d = 0; d < 4; d++) {
                int nx = i + dx[d];
                int ny = j + dy[d];

                // 범위 내 , 벽(#)이 아니고 , 아직 방문 하지 않은 경우
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && S[nx].charAt(ny) != '#' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // 거리 갱신
                    dist[nx][ny] = dist[i][j] + 1;
                    // 다음 위치를 큐에 추가
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 불이 구름이가 위치한 곳까지 도달했을 때 탈출할 수 있는지 판단
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 구름이가 위치하는 곳
                if (S[i].charAt(j) == '&') {
                    // 불이 도달하지 않음
                    if (!visited[i][j]){
                        System.out.println(-1);
                        // 불이 도달한다면 불이 오기 직전까지 머무를 수 있다
                    } else {
                        System.out.println(dist[i][j] - 1);
                    }
                    // 구름이는 한 명이므로 결과 출력 후 종료
                    return;
                }
            }
        }
    }
}
