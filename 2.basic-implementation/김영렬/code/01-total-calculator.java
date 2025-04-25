import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int result = 0;

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            char symbol = st.nextToken().charAt(0);
            int num2 = Integer.parseInt(st.nextToken());

            switch (symbol) {
                case '+':
                    result += (num1 + num2);
                    break;
                case '-':
                    result += (num1 - num2);
                    break;
                case '*':
                    result += (num1 * num2);
                    break;
                case '/':
                    result += (num1 / num2);
                    break;
            }
        }

        System.out.println(result);
    }
}