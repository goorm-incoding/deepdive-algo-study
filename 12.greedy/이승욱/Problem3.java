package Goorm.sec14;

import java.io.*;
import java.util.*;

public class Problem3 {
    static class Cookie {
        int taste; // 맛을 저장
        int idx; // 인덱스를 저장

        Cookie(int taste, int idx)
        {
            this.taste = taste;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Cookie[] cookies = new Cookie[n];

        for (int i = 0; i < n; ++i)
        {
            int a = Integer.parseInt(st.nextToken());
            cookies[i] = new Cookie(a, i + 1);
        }

        //맛이 낮은 쿠키가 앞에 오도록 오름차순 정렬 (가장 먼저 먹게 됨)
        //맛이 같다면 인덱스가 작은 쿠키를 우선함
        Arrays.sort(cookies, (c1, c2) -> {
            if (c1.taste != c2.taste) return Integer.compare(c1.taste, c2.taste);
            return Integer.compare(c1.idx, c2.idx);
        });

        // 만약 taste <= j라면 맛이 0이 된다.
        // 맛이 0이 되었을 경우에는 어떤 순서로 섭취하든 곱은 무조건 0이 된다.
        // 따라서 사전순으로 출력해야 한다.
        boolean allPositive = true;
        for (int j = 0; j < n; ++j)
        {
            if (cookies[j].taste <= j)
            {
                allPositive = false;
                break;
            }
        }

        // 만약 정렬된 순서로 모든 쿠키를 맛있게 먹을 수 있다면 (allPositive == true) 정렬된대로 출력
        // 그렇지 않다면 -> 그냥 원래의 순서대로 (1, 2, ..., N) 출력한다.
        if (allPositive) for (int j = 0; j < n; ++j) sb.append(cookies[j].idx).append(' ');
        else for (int i = 1; i <= n; ++i) sb.append(i).append(' ');

        System.out.println(sb.toString().trim());
    }
}
