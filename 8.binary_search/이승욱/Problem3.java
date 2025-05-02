import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem3
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken()); // 게임 진행 횟수
        long m = Long.parseLong(st.nextToken()); // 승리 횟수

        long rate = m * 100 / n; // 현재 승률 계산

        long leftPtr = 1;
        long rightPtr = (long) Math.pow(10, 12);
        long answer = -1;

        while (leftPtr <= rightPtr)
        {
            long mid = (leftPtr + rightPtr) / 2; // 중앙 인덱스
            long newRate = (m + mid) * 100 / (n + mid); // 새로운 승률 계산

            if (newRate > rate) // 새로운 승률이 원래 승률보다 높다면
            {
                answer = mid; // 정답 업데이트
                rightPtr = mid - 1; // 오른쪽 범위를 중앙 인덱스 왼쪽으로 이동시켜 탐색 범위 축소
            }
            else leftPtr = mid + 1; // 왼쪽 범위를 중앙 인덱스 오른쪽으로 이동시켜 탐색 범위 축소
        }

        System.out.println(answer == -1 ? "X" : answer);
    }
}
