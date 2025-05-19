import java.io.*;
import java.util.*;

public class Problem1
{
    static class Charger
    {
        int price;
        int type;

        Charger(int price, int type)
        {
            this.price = price;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); // x만
        int b = Integer.parseInt(st.nextToken()); // y만
        int c = Integer.parseInt(st.nextToken()); // x와 y 모두
        int n = Integer.parseInt(br.readLine());

        List<Charger> chargers = new ArrayList<>();
        for (int i = 0; i < n; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            chargers.add(new Charger(price, type));
        }

        // 가격 오름차순으로 충전기 정렬
        Collections.sort(chargers, (c1, c2) -> c1.price - c2.price);

        int count = 0;
        long totalPrice = 0;

        // 가장 저렴한 충전기부터 할당
        for (Charger charger : chargers)
        {
            if (charger.type == 0) // x타입
            {
                if (a > 0) // x 타입 충전기에 할당
                {
                    ++count;
                    totalPrice += charger.price;
                    --a;
                }
                else if (c > 0) // x와 y 모두되는 충전기에 할당
                {
                    ++count;
                    totalPrice += charger.price;
                    --c;
                }
            }
            else // y타입
            {
                if (b > 0) // x 타입 충전기에 할당
                {
                    ++count;
                    totalPrice += charger.price;
                    --b;
                }
                else if (c > 0) // x와 y 모두되는 충전기에 할당
                {
                    ++count;
                    totalPrice += charger.price;
                    --c;
                }
            }
        }

        System.out.print(count + " " + totalPrice);
    }
}
