import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem4
{
    public static boolean[] sieve(int n)
    {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // 처음엔 전부 소수(true)로 가정
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; ++i)
        {
            if (isPrime[i])
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false; // i의 배수 제거
        }
        return isPrime;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] armors = new int[n];
        int max = 0;

        // 갑옷 고유값 저장 및 최대값 계산
        for(int i = 0; i < n; ++i)
        {
            armors[i] = Integer.parseInt(br.readLine());
            if (armors[i] > max) max = armors[i];
        }

        boolean[] isPrime = sieve(max);

        for (int i = 0; i < n; i++)
        {
            int count = 0;
            while (!isPrime[armors[i] - count]) ++count; // 가장 처음 만나는 소수 -> 가장 적은 갑옷 변형 시스템 사용 횟수
            sb.append(count).append(System.lineSeparator());
        }
        System.out.println(sb);
    }
}
