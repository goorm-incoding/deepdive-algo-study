package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class TravelTheWorld {
    static int N, M;
    static int[] a;
    // 그래프의 인접리스트
    static List<Integer>[] G;
    static boolean[] visited;

    static int bfs(int start, Set<Integer> lang){
        visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        // 시작 지점에 큐 추가
        q.offer(start);
        // 시작 시점 방문 처리
        visited[start] = true;

        // 시작 나라 포함해서 횟수 시작
        int cnt = 1;

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()){
            // 현재 위치
            int current = q.poll();

            // 현재 위치에서 이동 가능한 인접 노드 탐색
            for(int next : G[current]){
                // 아직 방문하지 않았고 아는 언어 중 하나를 사용하는 나라일 때만 방문
                if (!visited[next] && lang.contains(a[next])){
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 나라의 개수
        N = Integer.parseInt(input[0]);
        // 항로의 개수
        M = Integer.parseInt(input[1]);

        // i 번 나라가 사용하는 언어의 번호
        a = new int[N+1];
        input = br.readLine().split(" ");
        for(int i = 1; i<N+1; i++){
            a[i] = Integer.parseInt(input[i-1]);
        }

        // 그래프 초기화
        G = new ArrayList[N+1];
        for(int i = 1; i<N+1; i++){
            G[i] = new ArrayList<>();
        }

        // 향로 정보 입력받아서 양방향 그래프 연결
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]);
            int q = Integer.parseInt(input[1]);
            // 양방향 연결
            G[p].add(q);
            G[q].add(p);
        }

        int result = 0;
        // 구름이가 이미 알고 있는 언어
        int goorm = a[1];

        // 구름이가 추가로 배울 수 있는 언어 1~10번까지 시도
        for(int i = 1; i<=10; i++){
            // 현재 아는 언어 집합 = 원래 알고 있는 언어 + 새로 배울 언어
            Set<Integer> knownLang = new HashSet<>();
            knownLang.add(goorm);
            knownLang.add(i);
            // 방문 가능한 나라 수 계산
            int count = bfs(1, knownLang);

            // 최대값
            result = Math.max(result, count);
        }

        System.out.println(result);
    }
}
