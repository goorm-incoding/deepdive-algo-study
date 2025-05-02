import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], M = input[1];

        int[] vertex = new int[N];  // 정점의 차수 정보 배열
        Edge[] edges = new Edge[M]; // 간선 정보 배열
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int A = input[0] - 1, B = input[1] - 1;
            edges[i] = new Edge(A, B);
            vertex[A]++;
            vertex[B]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];
            if (vertex[edge.A] == 1 || vertex[edge.B] == 1) {
                continue;
            }
            sb.append(i + 1).append(" ");
        }

        String result = sb.toString().trim();
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static class Edge {
        int A;
        int B;

        public Edge(int a, int b) {
            A = a;
            B = b;
        }
    }
}