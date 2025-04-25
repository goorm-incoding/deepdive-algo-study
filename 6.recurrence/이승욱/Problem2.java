import java.io.*;
import java.util.*;

class Main {
	static long K, moveCount = 0;
	static List<Integer>[] sticks = new ArrayList[4]; // 1~3번 막대 (index 1부터 사용)

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Long.parseLong(br.readLine());

		for (int i = 1; i <= 3; ++i) sticks[i] = new ArrayList<>(); // 막대 초기화
		for (int i = 20; i >= 1; --i) sticks[1].add(i);// 1번 막대에 20개의 원반을 면적이 큰 순서대로 쌓음

		move(20, 1, 3); // 하노이 재귀 실행

		// 결과 출력
		for (int i = 1; i <= 3; ++i)
		{
			int total = 0;
			for (int disk : sticks[i]) total += disk;
			System.out.print(total + " ");
		}
	}

	// 하노이탑 이동 재귀 함수
	static void move(int diskCount, int from, int to)
	{
		if (diskCount == 0 || moveCount >= K) return; // 원판의 개수가 0이거나 움직인 횟수가 k 이상이면 종료
		int via = 6 - from - to; // 기둥 번호의 합이 6이므로 시작 기둥, 목표 기둥이 어느 기둥이더라도 보조 기둥은 6 - from - to로 구할 수 있음
		move(diskCount - 1, from, via); // 가장 큰 원반을 제외한 위쪽 diskCount - 1개의 원반을 보조 기둥으로 이동

		if (moveCount >= K) return; // k 이상이면 종료
		++moveCount; // 이동 횟수 증가

		if (moveCount <= K)
		{
			int disk = sticks[from].remove(sticks[from].size() - 1); // from 기둥에서 가장 위의 원반을 꺼내고
			sticks[to].add(disk); // to 기둥에 쌓음
		}

		move(diskCount - 1, via, to); // diskCount - 1개를 다시 보조기둥 -> 목적기둥으로 이동
	}
}