import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Problem2
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; ++i)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String product = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());

            map.put(product, map.getOrDefault(product, 0) + amount);
        }

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 이름을 기준으로 사전순으로 정렬
                .forEach(entry -> sb.append(entry.getKey() + " " + entry.getValue()).append(System.lineSeparator()));

        System.out.println(sb);
    }
}
