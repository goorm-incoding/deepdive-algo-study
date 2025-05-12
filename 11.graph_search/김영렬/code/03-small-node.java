import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]), K = Integer.parseInt(input[2]);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]), e = Integer.parseInt(input[1]);
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        bfs(graph, N, K);
    }

    private static void bfs(List<List<Integer>> graph, int N, int K) {
        boolean[] visited = new boolean[N + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(K);
        int answer = 0;
        int leastVisited = K;
        while (!q.isEmpty()) {
            leastVisited = q.poll();
            visited[leastVisited] = true;
            answer++;
            List<Integer> neighbors = graph.get(leastVisited);
            if (neighbors != null && !neighbors.isEmpty()) {
                Collections.sort(neighbors);
                for (int node : neighbors) {
                    if (!visited[node]) {
                        q.add(node);
                        break;
                    }
                }
            }
        }
        System.out.println(answer + " " + leastVisited);
    }
}