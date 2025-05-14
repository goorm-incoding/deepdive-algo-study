import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem3
{
    static ArrayList<Integer>[] adjList; // 인접 리스트
    static boolean[] visited; // 방문 배열

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 노드 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 수
        int k = Integer.parseInt(st.nextToken()); // 시작 노드

        adjList = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i < n + 1; ++i) adjList[i] = new ArrayList<>();

        for(int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            // 양방향 연결
            adjList[nodeA].add(nodeB);
            adjList[nodeB].add(nodeA);
        }

        int[] answer = bfs(k);
        System.out.print(answer[0] + " " + answer[1]); // 방문한 노드 수, 마지막으로 방문한 노드 수 출력

    }

    static int[] bfs(int startNode)
    {
        Queue<Integer> que = new LinkedList<>();
        que.add(startNode);
        visited[startNode] = true;

        int count = 1; // 방문한 노드 수
        int lastNode = startNode; // 마지막으로 방문한 노드

        while(!que.isEmpty())
        {
            int nowNode = que.poll();
            int minNode = Integer.MAX_VALUE;

            // 인접 노드중 가장 작으면서 방문하지 않은 노드를 찾음
            boolean found = false;
            for(int linkedNode : adjList[nowNode])
            {
                if((!visited[linkedNode]) && (linkedNode < minNode)) // 방문하지 않았고, 가장 작은 노드라면
                {
                    minNode = linkedNode;
                    found = true;
                }
            }

            // 인접 노드중 가장 작으면서 방문하지 않은 노드를 찾았다면
            if(found)
            {
                visited[minNode] = true;
                que.add(minNode);
                lastNode = minNode; // 마지막으로 방문한 노드 업데이트
                ++count; // 방문한 노드 수 업데이트
            }

        }
        return new int[]{count, lastNode};
    }
}
