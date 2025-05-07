package com.javatraining.bruteforce;

import java.io.*;
import java.util.*;

public class SplitString {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열의 길이
        int N = Integer.parseInt(br.readLine());
        // 문자열
        String S = br.readLine();

        // 슬라이싱한 점수들 입력 받을 배열
        List<String[]> wordList = new ArrayList<>();
        // 중복을 제거
        Set<String> noDupl = new HashSet<>();

        // 2개의 기준점을 잡아서 슬라이싱
        // 문자열은 1개이상이므로 기준점을 1부터 시작
        for(int i = 1; i<N; i++){
            for(int j = i+1; j<N; j++){
                String first = S.substring(0,i);
                String second = S.substring(i,j);
                String third = S.substring(j);
                // 객체 형식으로 나누어진 모든 문자열의 방법을 저장
                wordList.add(new String[]{first, second, third});
                // 나누어진 문자열의 중복을 방지하여 리스트에 저
                noDupl.add(first);
                noDupl.add(second);
                noDupl.add(third);
            }
        }

        // 사전순으로 임시 정렬하기
        List<String> temp = new ArrayList<>(noDupl);
        Collections.sort(temp);

        // 사전순으로 정렬한 것을 점수랑 문자열을 key-value 형식으로 매핑
        Map<String, Integer> scores = new HashMap<>();
        for(int i = 0; i<temp.size(); i++){
            // 점수는 1부터 시작이므로 value값을 맞춰준다.
            scores.put(temp.get(i),i+1);
        }

        int maxScore = -1;
        // 중복 제거 전 저장 해놓았던 모든 문자열의 경우의 수의 점수를 비교한다.
        for(String[] words : wordList){
            int tempScore = 0;
            // 한줄의 배열을 비교
            for (String word : words){
                tempScore += scores.get(word);
            }
            maxScore = Math.max(maxScore, tempScore);
        }

        System.out.println(maxScore);
    }

}
