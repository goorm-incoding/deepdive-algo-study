import java.io.*;
import java.util.StringTokenizer;

class Main {
    private static int aliceScore = 0;
    private static int bobScore = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int alicePower = Integer.parseInt(st1.nextToken());
            int bobPower = Integer.parseInt(st2.nextToken());

            if (alicePower > bobPower) {
                if (checkSpecialCase("Alice", alicePower, bobPower)) {
                    continue;
                }
                aliceScore += 2;
                continue;
            }

            if (bobPower > alicePower) {
                if (checkSpecialCase("Bob", bobPower, alicePower)) {
                    continue;
                }
                bobScore += 2;
                continue;
            }

            aliceScore += 1;
            bobScore += 1;
        }

        System.out.println(aliceScore + " " + bobScore);
    }

    // 공통 로직 별도 메서드로 분리
    private static boolean checkSpecialCase(String morePowerPlayer, int player1Power, int player2Power) {
        if (player1Power - player2Power == 7) {
            if (morePowerPlayer == "Alice") {
                bobScore += 3;
                aliceScore -= 1;
                return true;

            }
            aliceScore += 3;
            bobScore -= 1;

            return true;
        }

        return false;
    }
}