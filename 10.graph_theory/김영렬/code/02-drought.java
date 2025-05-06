import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            // 지점의 개수 N, 수로의 개수 M
            int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
            for (int j = 0; j < M; j++) {
                input = br.readLine().split(" ");
            }
            System.out.println(N - 1);
        }
    }
}