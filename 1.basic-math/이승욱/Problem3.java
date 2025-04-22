import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < n; ++i) 
				{
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            long min = Math.min(A, B);
            long max = Math.max(A, B);

						// min * 1.6 ≤ max ≤ min * 1.63
            // max * 100 ≥ min * 160 && max * 100 ≤ min * 163 -> 정수로 바꿈(부동소수점 오차 때문)
            if (max * 100 >= min * 160 && max * 100 <= min * 163) count++;
        }

        System.out.println(count);
    }
}
