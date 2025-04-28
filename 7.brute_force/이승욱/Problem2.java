import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Problem2
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ArrayList<String> sortStrings = new ArrayList<>(); // 중복되지 않고, 정렬된 부분 문자열을 저장
        ArrayList<String[]> combinations = new ArrayList<>(); // 부분 문자열이 될 수 있는 조합을 저장
        int score = 0;

        for (int i = 1; i < length - 1; ++i)
        {
            for (int j = i + 1; j < length; ++j)
            {
                String one = input.substring(0, i); // 0 ~ i - 1
                String two = input.substring(i, j); // i ~ j -1
                String three = input.substring(j); // j ~ 끝

                String[] combination = {one, two, three}; // 현재 나눈 세 부분 문자열 조합
                combinations.add(combination); // 해당 조합을 저장

                for(String str : combination) // 조합의 각 요소를 순회
                    if(!sortStrings.contains(str)) // 중복되지 않았다면
                        sortStrings.add(str); // 해당 문자열 추가
            }
        }

        Collections.sort(sortStrings); // 사전 순으로 정렬

        for(String[] strings : combinations) // 조합 순회
        {
            int sum = 0;
            for(String str : strings) sum += sortStrings.indexOf(str) + 1; // 사전순 인덱스를 점수로 합산
            score = Math.max(score, sum);
        }

        System.out.println(score);
    }
}
