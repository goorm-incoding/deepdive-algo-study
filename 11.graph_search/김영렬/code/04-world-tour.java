import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

class Main {

    private static final List<List<Integer>> adjacentList = new ArrayList<>();

    private static int[] languages;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
        visited = new boolean[N];

        languages = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 인접 리스트 초기화 및 데이터 삽입
        for (int i = 0; i < N; i++) {
            adjacentList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int p = Integer.parseInt(input[0]) - 1; // 인덱스가 0부터 시작하기 때문에 -1
            int q = Integer.parseInt(input[1]) - 1; // 인덱스가 0부터 시작하기 때문에 -1
            adjacentList.get(p).add(q);
            adjacentList.get(q).add(p);
        }

        int result = 0;
        for (int i = 1; i <= 10; i++) {
            if (languages[0] == i) continue; // 첫번째 나라 언어는 패스
            result = Math.max(result, bfs(i));
        }

        System.out.println(result);
    }

    private static int bfs(int lang) {
        Deque<Integer> queue = new ArrayDeque<>();
        Arrays.fill(visited, false); // 방문 배열 초기화 - bfs가 언어별로 반복해서 수행되기 때문에
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjacentList.get(node)) {
                // 그 나라에 방문하지 않았으며 그 나라의 언어가 모국어 또는 학습한 언어일 경우
                if (!visited[neighbor] && (languages[neighbor] == languages[0] || languages[neighbor] == lang)) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return (int) IntStream.range(0, visited.length).filter(i -> visited[i]).count();
    }
}