import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4
{
    static int[] parent;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) parent[i] = i; // 처음에는 자기 자신이 부모노드

        int count = n; // 처음엔 N개의 집합이 존재
        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (union(a, b)) --count; // 유니온 연산이 성공한다면 집합의 수 감소
        }

        System.out.println(count);
    }

    static boolean union(int a, int b)
    {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) return false; // 부모가 같다면 같은 집합이라는 뜻

        // 부모가 다르다면, 두 부모 노드 중 값이 더 작은 노드가 부모 노드가 됨
        if (parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;

        return true;
    }

    static int find(int node)
    {
        if (parent[node] != node) parent[node] = find(parent[node]); // 경로 압축
        return parent[node]; // 부모 노드 반환
    }
}
