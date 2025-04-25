import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int armor = Integer.parseInt(br.readLine());
            if (isPrime(armor)) {
                System.out.println("Yes");
                continue;
            }
            System.out.println("No");
        }
    }

    private static boolean isPrime(int armor) {
        if (armor == 2)
            return true; // 2는 소수
        if (armor % 2 == 0)
            return false; // 짝수는 2 제외하고 소수가 아님
        for (int i = 3; i <= (int) Math.sqrt(armor); i += 2) {
            if (armor % i == 0)
                return false;
        }
        return true;
    }
}