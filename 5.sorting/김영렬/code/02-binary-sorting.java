import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; // 정수의 수
        int K = input[1]; // 플레이어가 찾으려는 정수의 위치

        Integer[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed().toArray(Integer[]::new);

        // 1의 개수(내림차순) → 값 크기(내림차순) 정렬
        Arrays.sort(nums, (a, b) -> {
            int cntA = Integer.bitCount(a);
            int cntB = Integer.bitCount(b);
            return cntA != cntB ? Integer.compare(cntB, cntA) : Integer.compare(b, a);
        });

        System.out.println(nums[K - 1]);
    }
}