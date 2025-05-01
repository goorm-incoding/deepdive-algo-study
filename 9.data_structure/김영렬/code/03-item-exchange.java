import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];
        Set<String> goormItems = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toSet());
        Set<String> friendItems = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toSet());

        while (M-- > 0) {
            String[] exchange = br.readLine().split(" ");
            if (goormItems.contains(exchange[0]) && friendItems.contains(exchange[1])) {
                goormItems.remove(exchange[0]);
                friendItems.remove(exchange[1]);
                goormItems.add(exchange[1]);
                friendItems.add(exchange[0]);
            }
        }

        StringBuilder sb = new StringBuilder();
        goormItems.stream().sorted().forEach(item -> sb.append(item).append(" "));
        System.out.println(sb.toString().trim());
    }
}