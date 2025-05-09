import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem1
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 기폭 장치 수
        int m = Integer.parseInt(st.nextToken()); // 전선 수

        int[][] wires = new int[m][2]; // 전선에 연결된 기폭장치를 저장하는 배열
        int[] connectionCount = new int[n + 1]; // n개의 기폭장치에 몇 개의 선이 연결되어있는지 저장하는 배열(인덱스 1부터 시작)

        for (int i = 0; i < m; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int deviceA = Integer.parseInt(st.nextToken());
            int deviceB = Integer.parseInt(st.nextToken());

            // a장치와 b장치가 하나의 전선으로 연결되었음을 의미
            wires[i][0] = deviceA;
            wires[i][1] = deviceB;

            // 각 장치에 연결된 전선 수 추가
            ++connectionCount[deviceA];
            ++connectionCount[deviceB];
        }

        List<Integer> safeWires = new ArrayList<>(); // 안전한 전선을 저장하는 리스트
        for (int i = 0; i < m; ++i)
        {
            // 선에 연결된 장치를 가져옴
            int deviceA = wires[i][0];
            int deviceB = wires[i][1];

            // 해당 선을 제거하면 각 장치가 다른 전선과 연결되었는지 검사
            if (connectionCount[deviceA] - 1 >= 1 && connectionCount[deviceB] - 1 >= 1) safeWires.add(i + 1); // 안전한 전선 추가(전선 번호는 1부터 시작)
        }

        if (safeWires.isEmpty()) sb.append("-1");
        else
        {
            Collections.sort(safeWires);
            for (int wireNum : safeWires) sb.append(wireNum).append(" ");
        }
        System.out.println(sb);
    }
}
