import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem4
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr); // 이진 탐색은 정렬이 필수

        for(int i = 0; i < q; ++i)
        {
            long x = Long.parseLong(br.readLine());
            int index = Arrays.binarySearch(arr, x);

            if (index >= 0)
            {
                sb.append(arr[index]).append(System.lineSeparator());
                continue;
            }

            // 이진 탐색을 실패했을 경우
            index = -(index + 1); // 삽입 위치 계산

            if (index == 0) sb.append(arr[0]).append(System.lineSeparator());
            else if (index == n) sb.append(arr[n - 1]).append(System.lineSeparator());
            else
            {
                long leftValue = arr[index - 1];
                long rightValue = arr[index];

                long leftDiff = x - leftValue; // 왼쪽 차이
                long rightDiff = rightValue - x; // 오른쪽 차이

                if (leftDiff < rightDiff) sb.append(leftValue).append(System.lineSeparator());
                else if (leftDiff > rightDiff) sb.append(rightValue).append(System.lineSeparator());
                else sb.append(Math.min(leftValue, rightValue)).append(System.lineSeparator()); // 차이가 똑같다면 더 작은 값 출력
            }

        }
        System.out.print(sb);
    }
}
