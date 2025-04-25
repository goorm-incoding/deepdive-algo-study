import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    private static int treesCount;
    private static int startPosition;
    private static int cuttingCriteria;
    private static int cuttingChance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        treesCount = Integer.parseInt(firstLine[0]);
        cuttingCriteria = Integer.parseInt(firstLine[1]);
        startPosition = Integer.parseInt(firstLine[2]);

        int[] treeHeightData = new int[treesCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < treesCount; i++) {
            treeHeightData[i] = Integer.parseInt(st.nextToken());
        }

        cuttingChance = Integer.parseInt(br.readLine());
        String[] directionData = new String[cuttingChance];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cuttingChance; i++) {
            directionData[i] = st.nextToken();
        }

        long result = logic(treeHeightData, directionData);

        System.out.println(result);
    }

    private static long logic(int[] treeHeightData, String[] directionData) {
        long woodAmount = 0L;

        int position = startPosition - 1;

        for (int i = 0; i < cuttingChance; i++) {
            // 벌목
            woodAmount += cuttingTree(treeHeightData, position);
            // 위치 이동
            position = movePosition(directionData[i], position);
            // 나무 성장
            treeGrow(treeHeightData);
        }

        return woodAmount;
    }

    private static long cuttingTree(int[] treeHeightData, int position) {
        long amount = 0L;

        // 해당 나무가 벌목이 가능하면 벌목
        if (treeHeightData[position] >= cuttingCriteria) {
            // 나무를 벌목 -> 목재량이 증가한다.
            amount = treeHeightData[position];
            // 벌목한 나무의 높이가 0이 된다.
            treeHeightData[position] = 0;
        }

        return amount;
    }

    private static int movePosition(String direction, int position) {
        if (direction.equals("L")) {
            // 현재 위치가 배열의 첫 번째 요소일 경우 배열의 마지막 요소로 이동
            if (position == 0) {
                return treesCount - 1;
            }
            return position - 1;
        }

        if (direction.equals("R")) {
            // 현재 위치가 배열의 마지막 요소일 경우 배열의 첫번째 요소로 이동
            if (position == treesCount - 1) {
                return 0;
            }
            return position + 1;
        }

        return position;
    }

    private static void treeGrow(int[] treeHeightData) {
        // 벌목과 이동이 모두 끝나면 나무가 1만큼 성장한다.
        for (int j = 0; j < treeHeightData.length; j++) {
            treeHeightData[j] += 1;
        }
    }
}