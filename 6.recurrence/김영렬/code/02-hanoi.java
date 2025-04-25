import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Main {
		private static final int DISK_AMOUNT = 20;
    private static final List<int[]> MOVE_DATA = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        hanoi(DISK_AMOUNT, 1, 3, 2);

        System.out.println(logic(K));
    }

    private static void hanoi(int n, int from, int to, int via) {
        int[] move = {from, to};

        if (n == 1) {
            MOVE_DATA.add(move);
            return;
        }

        hanoi(n - 1, from, via, to);
        MOVE_DATA.add(move);
        hanoi(n - 1, via, to, from);
    }

    private static String logic(int K) {
        List<Stack<Integer>> pillars = new ArrayList<>(){{
            add(new Stack<>());
            add(new Stack<>());
            add(new Stack<>());
        }};

        for (int i = 20; i > 0; i--) pillars.get(0).push(i);

        for (int i = 0; i < K; i++) {
            int start = MOVE_DATA.get(i)[0];
            int end = MOVE_DATA.get(i)[1];

            int disk = pillars.get(start - 1).pop();
            pillars.get(end - 1).push(disk);
        }

        StringBuilder sb = new StringBuilder();
        pillars.forEach(pillar -> {
            Integer sum = pillar.stream().reduce(0, Integer::sum);
            sb.append(sum).append(" ");
        });

        return sb.toString().trim();
    }
}