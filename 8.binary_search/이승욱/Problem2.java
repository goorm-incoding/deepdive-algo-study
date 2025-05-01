import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);
        long answer = 0;

        for (int i = 0; i < n - 2; ++i)
        {
            for (int j = i + 1; j < n - 1; ++j)
            {
                long sum = arr[i] + arr[j];

                // 삽입 위치 : 그 값을 배열에 넣었을 때 정렬을 유지할 수 있는 위치
                // 검색에 실패했다면 그 값이 들어갈 자리를 음수로 가공해서 반환 -> 리턴값 = -(삽입 위치) -1
                int index = Arrays.binarySearch(arr, sum); // sum 값의 인덱스를 저장

                if (index < 0) index = -(index + 1); // sum을 발견하지 못했다면, 삽입 위치를 저장
                else ++index; // 인덱스를 1 증가(합계 값 자체는 포함 X)

                if (index > j + 1) answer += (index - (j + 1));
                System.out.println(answer);
            }
        }
        System.out.println(answer);
    }
}
