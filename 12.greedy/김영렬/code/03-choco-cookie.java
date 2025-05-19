import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main {

    static class Cookie {
        int idx;
        int deli;

        public Cookie(int idx, int deli) {
            this.idx = idx;
            this.deli = deli;
        }

        public int getDeli() {
            return deli;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Cookie[] cookies = new Cookie[N];
        for (int i = 0; i < N; i++) {
            cookies[i] = new Cookie(i + 1, values[i]);
        }

        Arrays.sort(cookies, Comparator.comparingInt(Cookie::getDeli));

        StringBuilder sb = new StringBuilder();

        // 만일 어떤 쿠키의 맛이 0이 되었을 경우에는 어떤 순서로 섭취하든 곱은 무조건 0이 된다.
        // 따라서 이 경우에는 출력 요구 사항에 맞춰 사전순으로 쿠키의 번호를 출력해야 한다.
        for (int i = 0; i < N; i++) { // 맛 감소 레벨
            if (cookies[i].deli - i <= 0) {
                for (int j = 1; j <= N; j++) {
                    sb.append(j).append(" ");
                }
                System.out.println(sb.toString().trim());
                return;
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(cookies[i].idx).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}