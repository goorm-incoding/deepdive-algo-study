import java.io.*;
import java.util.StringTokenizer;

public class Problem2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 동전의 개수 입력

        // 동전들의 가치 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] coins = new int[n];
        for (int i = 0; i < n; ++i) coins[i] = Integer.parseInt(st.nextToken());

        long maxSum = 0;
        long currentSum = 0;

        for (int i = 0; i < n; ++i)
        {
            currentSum += coins[i]; // 현재 동전을 포함한 부분합 계산
            maxSum = Math.max(maxSum, currentSum); // 지금까지의 최대값 갱신
            if (currentSum < 0) currentSum = 0; // 부분합이 음수가 되면 새로 시작
        }
        System.out.println(maxSum);
    }
}
