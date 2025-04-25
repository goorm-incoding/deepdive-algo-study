import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] H = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long totalCnt = 0;
        int damage = 0;

        /*
         * Key Point. 데미지는 1, 2, 3, 4로 순환한다.
         *
         * 우선 한 사이클을 돌려본다. (만약 사이클 중 적의 체력이 0이하로 내려간다면 중단)
         *
         * 한 사이클을 돌려도 체력이 남아있다면 아래 과정을 수행한다.
         *
         * 현재 데미지가 1이고 (i가 0) 적의 체력이 health라고 가정한다면
         * Math.round(health / 10) * 4번의 발사로 Math.round(health / 10) * 10 만큼의 데미지를 입힌다.
         * 이렇게 될 경우 적의 체력은 health % 10이 된다.
         *
         * 만약 적의 체력이 위 과정을 수행해도 남아있다면 다시 사이클을 돌려본다.
         */

        for (int health : H) {
            // 데미지가 1이 되기 전까지 & 적의 체력이 남아있는 동안 사격
            while (damage > 0 && health > 0) {
                health -= damage + 1;
                damage = (damage + 1) % 4;
                totalCnt++;
            }

            // 현재 체력이 0이하일 경우(적이 쓰러졌다면) 다음 적으로 목표 변경
            if (health <= 0) {
                continue;
            }

            // 위에서 도출한 수식을 활용하여 체력을 10미만으로 만든다.
            totalCnt += (health / 10) * 4;
            health %= 10;

            while (health > 0) {
                health -= damage + 1;
                damage = (damage + 1) % 4;
                totalCnt++;
            }
        }

        System.out.println(totalCnt);
    }
}