import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Problem3
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> goorm = new HashSet<>();
        Set<String> friend = new HashSet<>();

        // 구름이가 갖고 있는 아이템을 set에 담음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) goorm.add(st.nextToken());

        // 친구가 갖고 있는 아이템을 set에 담음
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) friend.add(st.nextToken());

        // m번의 교환 요청 처리
        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            String itemA = st.nextToken();
            String itemB = st.nextToken();

            // a를 구름이가 갖고 있고, b를 친구가 갖고 있으면 서로 교환
            if (goorm.contains(itemA) && friend.contains(itemB))
            {
                goorm.remove(itemA);
                goorm.add(itemB);
                friend.remove(itemB);
                friend.add(itemA);
            }
        }

        // 아이템의 이름을 사전순으로 출력하기 위해 리스트에 담고 정렬
        List<String> answer = new ArrayList<>(goorm);
        Collections.sort(answer);

        for (String item : answer) System.out.print(item + " ");
    }
}
