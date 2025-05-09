import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2
{
    /*
        연결된 그래프에서 불필요한 간선을 제거하면서도 모든 정점을 연결시키는 최소 간선 개수만 남기는 것이 목표
        즉, 최소한의 간선으로 그래프를 연결 상태로 유지하려면 간선 수는 정점 수 - 1개만 있으면 충분
    */
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; ++i)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점 수
            int m = Integer.parseInt(st.nextToken()); // 간선 수

            for (int j = 0; j < m; ++j) br.readLine(); // 수로 정보는 읽기만 하고 버림

            sb.append(n - 1).append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}
