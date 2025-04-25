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
        for (int i = 2; i <= armor / 2; i++) {
            if (armor % i == 0)
                return false;
        }
        return true;
    }
}