import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem1
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] heights = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) heights[i] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();

        // 각 신선에 대해 봉우리 탐색 및 출력
        for (int i = 0; i < n; ++i)
        {
            sb.append(stack.size()).append(" "); // 현재 스택에 남아 있는 신선들의 수를 출력

            while ((!stack.isEmpty()) && (heights[stack.peek()] <= heights[i])) stack.pop(); // 현재 신선의 봉우리 높이보다 낮거나 같은 모든 신선들을 pop

            stack.push(i); // 현재 신선을 스택에 추가
        }

        // 결과 출력
        System.out.println(sb);
    }
}
