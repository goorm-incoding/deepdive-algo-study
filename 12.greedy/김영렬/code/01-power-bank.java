import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static class Charger implements Comparable<Charger> {
        int cost;
        int type;

        public Charger(int cost, int type) {
            this.cost = cost;
            this.type = type;
        }

        @Override
        public int compareTo(Charger o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = input[0], B = input[1], C = input[2];
        int N = Integer.parseInt(br.readLine());

        Charger[] chargerInfo = new Charger[N];
        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            chargerInfo[i] = new Charger(input[0], input[1]);
        }

        // 충전기의 가격을 기준으로 오름차순 정렬
        Arrays.sort(chargerInfo);

        int count = 0;
        long sum = 0;

        for (Charger c : chargerInfo) {
            // X타입 충전기인 경우
            if (c.type == 0) {
                // A타입 보조 배터리가 아직 남아있는 경우
                if (A > 0) {
                    count++;
                    sum += c.cost;
                    A--;
                } else if (C > 0) { // A타입이 남아있지 않고 C타입이 남아있는 경우
                    count++;
                    sum += c.cost;
                    C--;
                }
                continue;
            }

            // Y 타입 충전기인 경우
            if (c.type == 1){
                // B타입 보조 배터리가 아직 남아있는 경우
                if (B > 0) {
                    count++;
                    sum += c.cost;
                    B--;
                } else if (C > 0) { // B타입이 남아있지 않고 C타입이 남아있는 경우
                    count++;
                    sum += c.cost;
                    C--;
                }
            }
        }

        System.out.println(count + " " + sum);
    }
}