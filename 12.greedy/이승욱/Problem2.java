import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem2
{
    static class Missile
    {
        long time; // 미사일이 발사되거나 도착하는 시점
        boolean isStart; // true: 시작, false: 도착

        Missile(long time, boolean isStart)
        {
            this.time = time;
            this.isStart = isStart;
        }
    }

    // 유클리드 거리의 제곱의 두 배를 계산
    static long calculate(long x, long y)
    {
        return (x * x + y * y) * 2;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Missile> pq = new PriorityQueue<>((o1, o2) ->
        {
            if (o1.time != o2.time) return Long.compare(o1.time, o2.time);
            else return Boolean.compare(o1.isStart, o2.isStart); // 도착 이벤트를 발사 이벤트보다 먼저 처리
        });

        long totalFlyingTime = 0; // 부스터 없이 모든 미사일이 날아가는 시간 합계

        // 입력받으면서 바로 이벤트 생성
        for (int i = 0; i < n; ++i)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken()); // 외계인 x좌표
            long y = Long.parseLong(st.nextToken()); // 외계인 y좌표

            long startTime = Long.parseLong(st.nextToken());
            long endTime = startTime + calculate(x, y); // 발사 시간 + 비행 시간
            totalFlyingTime += endTime - startTime;

            pq.add(new Missile(startTime, true)); // 시작 이벤트
            pq.add(new Missile(endTime, false)); // 도착 이벤트
        }

        int flyingMissile = 0; // 날아가는 미사일 개수
        int maxMissileOverlap = 0; // 겹치는 미사일의 최대 개수

        // 겹치는 미사일의 최대 개수를 구하는 로직
        while (!pq.isEmpty())
        {
            Missile missile = pq.poll();

            if (missile.isStart) ++flyingMissile; // 미사일 발사 -> 날아가는 개수 +1
            else --flyingMissile; // 미사일 도착 -> 날아가는 개수 -1
            maxMissileOverlap = Math.max(maxMissileOverlap, flyingMissile);
        }

        System.out.println(totalFlyingTime - maxMissileOverlap); // 총 비행 시간 - 겹치는 미사일 개수
    }
}
