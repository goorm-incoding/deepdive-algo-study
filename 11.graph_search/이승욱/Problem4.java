import java.io.*;
import java.util.*;

public class Problem4
{
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int[] languages;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        languages = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; ++i)
        {
            adjList[i] = new ArrayList<>();
            languages[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int nationA = Integer.parseInt(st.nextToken());
            int nationB = Integer.parseInt(st.nextToken());

            adjList[nationA].add(nationB);
            adjList[nationB].add(nationA);
        }

        int language = languages[1];

        int maxCount = 0;

        for (int newLanguage = 1; newLanguage <= 10; ++newLanguage)
        {
            if (newLanguage == language) continue; // 이미 알고 있는 언어는 건너 뜀

            visited = new boolean[n + 1];
            int count = bfs(language, newLanguage); // 새 언어를 배웠을 때 방문할 수 있는 나라 수 계산
            maxCount = Math.max(maxCount, count);
        }

        System.out.print(maxCount);
    }

    static int bfs(int myLanguage, int newLanguage)
    {
        Queue<Integer> que = new LinkedList<>();
        que.add(1);  // 1번 나라에서 시작
        visited[1] = true;
        int count = 1;  // 방문한 나라 수

        while (!que.isEmpty())
        {
            int nowNation = que.poll();

            for (int nextNation : adjList[nowNation])
            {
                if (visited[nextNation]) continue; // 이미 방문한 나라는 건너 뜀

                // 내가 아는 언어나 새로 배운 언어를 사용하는 나라만 방문 가능
                if (languages[nextNation] == myLanguage || languages[nextNation] == newLanguage)
                {
                    visited[nextNation] = true;
                    que.add(nextNation);
                    ++count;
                }
            }
        }

        return count;
    }
}
