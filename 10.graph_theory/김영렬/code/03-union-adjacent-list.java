import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 그래프 선언 및 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        // 간선 데이터 추가
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            graph.get(s).add(e);
        }

        System.out.println(bfs(graph));
    }

    private static int bfs(List<List<Integer>> graph) {

        int count = 0;
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                ++count; queue.add(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int target = queue.poll();
                    for (int node : graph.get(target)) {
                        if (!visited[node] && graph.get(node).contains(target)) {
                            queue.add(node);
                            visited[node] = true;
                        }
                    }
                }
            }
        }

        return count;
    }
}