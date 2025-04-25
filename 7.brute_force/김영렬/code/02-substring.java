import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Main {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        List<String[]> cases = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                String subStr1 = S.substring(0, i);
                String subStr2 = S.substring(i, j);
                String subStr3 = S.substring(j);
                cases.add(new String[]{subStr1, subStr2, subStr3});
            }
        }

        List<String> dictList = cases.stream()
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < dictList.size(); i++) {
            scores.put(dictList.get(i), i + 1);
        }

        int maxScore = cases.stream()
                .mapToInt(subStrCase -> Arrays.stream(subStrCase).mapToInt(scores::get).sum())
                .max()
                .orElseThrow();

        System.out.println(maxScore);
    }
}