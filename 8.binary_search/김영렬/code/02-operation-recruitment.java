import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] S = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        long result = 0L;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long sum = S[i] + S[j];
                int idx = Arrays.binarySearch(S, j + 1, N, sum);

                // Arrays.binarySearch 메서드는 key로 주어진 값이 배열에 존재하지 않을 경우
                // (해당 값이 들어가야 하는 위치의 인덱스 * -1) - 1 을 반환한다.
                // 따라서 해당값에 -1을 곱해 양수로 변환해준 뒤 1을 빼면 가장 큰 수의 인덱스를 구할 수 있다.
                if (idx < 0) idx = -idx - 1;
                else idx++;

                if (idx > j + 1) result += (idx - (j + 1));
            }
        }

        System.out.println(result);
    }
}