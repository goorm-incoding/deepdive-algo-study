package com.javatraining.datastructure;

import java.io.*;
import java.util.*;

public class ItemExchange {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 가지고 있는 아이템의 수
        int N = Integer.parseInt(input[0]);
        // 교환을 시도한 횟수
        int M = Integer.parseInt(input[1]);

        // List로 작성 시 Timeout -> Set으로 변경
        // List는 O(n), Set은 O(log n)
        // 구름이가 가지고 있던 종류의 아이템 이름
        // List<String> goorm = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
        Set<String> goorm = new HashSet<>(Arrays.asList(br.readLine().split(" ")));
        // 친구가 처음에 가지고 있던 종류의 아이템 이름
        // List<String> friend = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
        Set<String> friend = new HashSet<>(Arrays.asList(br.readLine().split(" ")));

        // 문자열 A, B
        String[] A = new String[M];
        String[] B = new String[M];
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            A[i] = input[0];
            B[i] = input[1];
        }

        // 교환 과정
        for(int i = 0; i<M; i++){
            if(goorm.contains(A[i]) && friend.contains(B[i]) && !goorm.contains(B[i])){
                goorm.remove(A[i]);
                goorm.add(B[i]);
                friend.remove(B[i]);
                friend.add(A[i]);
            }
        }

        // 구름이가 가지고 있는 아이템의 이름 사전순으로 출력
        List<String> result = new ArrayList<>(goorm);
        Collections.sort(result);
        for(String item : result){
            System.out.print(item + " ");
        }
    }
}
