import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        int[] verticalLength = getInput();
        int result = logic(verticalLength);

        System.out.println(result);
    }

    private static int[] getInput() {
        int[] verticalLength = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int fieldSize = Integer.parseInt(br.readLine());
            verticalLength = new int[fieldSize];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < fieldSize; i++) {
                verticalLength[i] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return verticalLength;
    }

    private static int logic(int[] verticalLength) {
        int horizontalSum = verticalLength.length * 2;
        int verticalSum = 0;

        for (int i = 0; i < verticalLength.length; i++) {
            // 땅 길이가 1일 경우
            if (i == 0 && verticalLength.length == 1) {
                verticalSum += verticalLength[i] * 2;
                continue;
            }

            if (i == 0) {
                verticalSum += calcFirstField(verticalLength, i);
                continue;
            }

            if (i == verticalLength.length - 1) {
                verticalSum += calcLastField(verticalLength, i);
                continue;
            }

            verticalSum += calcOtherField(verticalLength, i);
        }

        return horizontalSum + verticalSum;
    }

    private static int calcFirstField(int[] verticalLength, int idx) {
        int sum = 0;

        sum += verticalLength[idx];
        int rightSideLength = verticalLength[idx] - verticalLength[idx + 1];
        if (rightSideLength > 0) {
            sum += rightSideLength;
        }

        return sum;
    }

    private static int calcLastField(int[] verticalLength, int idx) {
        int sum = 0;

        sum += verticalLength[idx];
        int leftSideLength = verticalLength[idx] - verticalLength[idx - 1];
        if (leftSideLength > 0) {
            sum += leftSideLength;
        }

        return sum;
    }

    private static int calcOtherField(int[] verticalLength, int idx) {
        int sum = 0;

        int leftSideLength = verticalLength[idx] - verticalLength[idx - 1];
        if (leftSideLength > 0) {
            sum += leftSideLength;
        }

        int rightSideLength = verticalLength[idx] - verticalLength[idx + 1];
        if (rightSideLength > 0) {
            sum += rightSideLength;
        }

        return sum;
    }
}