import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem1
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr); // 이진 탐색을 하기 위해선 정렬이 필수

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; ++i)
        {
            int value = Integer.parseInt(br.readLine());

            // 이진 탐색 메서드는 찾는 값의 인덱스를 반환함, 음수를 반환했다면 없다는 의미
            sb.append(Arrays.binarySearch(arr, value) < 0 ? 0 : 1).append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}
