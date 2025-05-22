package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class SmallNode2 {

    // 노드수, 간선수 , 시작노드 번호, 간선 입력용 변수
    static int N, M, K, s, e;
    // 정점 번호를 key, 인접 노드 목록을 value로 가지는 인접 리스트 형태
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    // 방문 노드 배열
    static boolean[] visited;
    // 이동 경로 큐
    static Queue<Integer> q = new LinkedList<>();
    // 방문한 횟수
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        // 각 노드 번호로 그래프 초기화
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            s = Integer.parseInt(input[0]);
            e = Integer.parseInt(input[1]);
            // 양방향 간선 추가
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        // 가장 번호가 작은 곳으로 먼저 이동하기 때문에 인접 리스트를 오름차순으로 정렬
        for (int i : graph.keySet()) {
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N + 1];
        // 시작 노드 큐에 넣기
        q.add(K);

        int result = bfs(K);
        System.out.println(count + " " + result);
    }

    static int bfs(int k) {
        // 시작 노드 방문 횟수 포함
        count = 1;
        // 마지막 방문 노드 번호 초기화
        int nodeNum = k;
        visited[k] = true;

        // 더 이상 방문할 노드가 없을 때까지 반복
        while (!q.isEmpty()) {
            int current = q.poll();
            // 마지막 방문 노드 번호 갱신
            nodeNum = current;

            // 이동 여부 체크
            boolean moved = false;
            // 현재 노드에서 갈 수 있는 노드 중 가장 번호가 작은 것부터 탐색
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    // 방문 노드 수 증가
                    count++;
                    // 이동
                    moved = true;
                    // 가장 번호가 작은 노드 하나만 이동 하므로 break
                    break;
                }
            }

            // 더 이상 이동할 수 없으면 종
            if (!moved) break;
        }

        // 마지막 방문 노드 번호 반환
        return nodeNum;
    }
}
