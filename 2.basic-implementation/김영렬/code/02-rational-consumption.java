import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String expensiveItemName = "";
        int expensiveItemPrice = 0;

        String cheapItemName = "";
        int cheapItemPrice = 0;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String itemName = st.nextToken();
            int itemPrice = Integer.parseInt(st.nextToken());

            // 첫번째 항목일 때
            if (i == 0) {
                expensiveItemName = itemName;
                expensiveItemPrice = itemPrice;

                cheapItemName = itemName;
                cheapItemPrice = itemPrice;

                continue;
            }

            // 지금까지 나온 물건 중 가장 비쌀 경우
            if (itemPrice > expensiveItemPrice) {
                expensiveItemName = itemName;
                expensiveItemPrice = itemPrice;

                continue;
            }

            // 지금까지 나온 물건 중 가장 저렴할 경우
            if (itemPrice < cheapItemPrice) {
                cheapItemName = itemName;
                cheapItemPrice = itemPrice;
            }
        }

        System.out.println(expensiveItemName + " " + expensiveItemPrice);
        System.out.println(cheapItemName + " " + cheapItemPrice);
    }
}