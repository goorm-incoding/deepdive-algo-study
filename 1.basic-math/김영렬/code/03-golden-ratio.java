import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());
        long[][] data = new long[T][2];

        // 입력받은 데이터로 2차원 배열 생성
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            data[i][0] = Long.parseLong(st.nextToken());
            data[i][1] = Long.parseLong(st.nextToken());
        }

        int count = 0;

        for (long[] item : data) {
            if (item[0] < item[1]) {
                if (item[0] * 1.6 <= item[1] && item[1] <= item[0] * 1.63) {
                    count++;
                }
                continue;
            }

            if (item[1] * 1.6 <= item[0] && item[0] <= item[1] * 1.63) {
                count++;
            }
        }

        System.out.println(count);
    }
}