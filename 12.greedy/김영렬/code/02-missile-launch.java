import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Missile[] missiles = new Missile[N * 2];
        long[] input;
        long S = 0;
        for (int i = 0; i < N * 2; i += 2) {
            input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long X = input[0], Y = input[1], T = input[2];

            // 거리 계산 후 S 갱신
            long d = (X * X + Y * Y) * 2;
            S += d;
            missiles[i] = new Missile(T, false);
            missiles[i + 1] = new Missile(T + d, true);
        }
        Arrays.sort(missiles);

        int ct = 0;
        int res = 0;
        for (Missile m : missiles) {
            if (!m.hit) {
                ct++;
            } else {
                ct--;
            }
            res = Math.max(ct, res);
        }

        System.out.println(S - res);
    }

    static class Missile implements Comparable<Missile> {
        long launchTime;
        boolean hit;

        public Missile(long launchTime, boolean hit) {
            this.launchTime = launchTime;
            this.hit = hit;
        }

        @Override
        public int compareTo(Missile o) {
            if (this.launchTime != o.launchTime) {
                return Long.compare(this.launchTime, o.launchTime);
            }
            return Boolean.compare(o.hit, this.hit);
        }
    }

}