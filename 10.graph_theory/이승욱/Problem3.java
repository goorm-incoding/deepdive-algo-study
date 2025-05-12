import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem3 {
    static ArrayList<Integer>[] adjList; // 단방향 인접 리스트
    static ArrayList<Integer>[] union; // 왕복 가능한 섬 리스트
    static boolean[] visited; // 방문 배열
    static int n, m;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 섬의 수
        m = Integer.parseInt(st.nextToken()); // 다리의 수

        adjList = new ArrayList[n + 1];
        union = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; ++i)
        {
            adjList[i] = new ArrayList<>();
            union[i] = new ArrayList<>();
        }

        // 입력된 단방향 간선 저장
        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int islandA = Integer.parseInt(st.nextToken());
            int islandB = Integer.parseInt(st.nextToken());
            adjList[islandA].add(islandB);
        }

        // 양방향인지 확인하고 연합에 추가
        for (int island = 1; island < n + 1; ++island)
        {
            for (int linkedIsland : adjList[island])
            {
                if (adjList[linkedIsland].contains(island))
                {
                    union[island].add(linkedIsland);
                    union[linkedIsland].add(island);
                }
            }
        }

        // BFS로 연합 개수 세기
        int count = 0;
        for (int i = 1; i < n + 1; ++i)
        {
            if (!visited[i])
            {
                ++count;
                bfs(i);
            }
        }

        System.out.print(count);
    }

    static void bfs(int island)
    {
        Queue<Integer> que = new LinkedList<>();
        que.add(island);
        visited[island] = true;

        while (!que.isEmpty())
        {
            int currentIsland = que.poll();
            for (int linkedIsland : union[currentIsland])
            {
                if (!visited[linkedIsland])
                {
                    visited[linkedIsland] = true;
                    que.add(linkedIsland);
                }
            }
        }
    }
}
