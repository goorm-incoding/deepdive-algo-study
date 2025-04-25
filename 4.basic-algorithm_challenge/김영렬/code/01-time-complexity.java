import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 1 <= N <= 10^9(=1000000000)
        int answer = 0;

        while (true) {
            if (N <= 0)
                break;

            answer += N / 5;
            N /= 5;
        }

        System.out.println(answer);
    }
}