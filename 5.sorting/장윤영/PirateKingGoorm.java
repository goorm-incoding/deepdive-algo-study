package com.javatraining.sorted;

import java.io.*;
import java.util.*;

public class PirateKingGoorm {
    // Comparable은 자기 자신과 다른 같은 타입의 객체를 비교해서 정렬 기준을 정의하는 인터페이스
    static class Island implements Comparable<Island>{
        int index, x, y;

        public Island(int index, int x, int y){
            this.index = index;
            this.x = x;
            this.y = y;
        }

        // x끼리 비교, x가 같다면 y를 비교해서 오름차순 정렬
        @Override
        public int compareTo(Island other){
            if (this.x != other.x){
                return Integer.compare(this.x, other.x);
            } else{
                return Integer.compare(this.y, other.y);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Island> island = new ArrayList<>();
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            // 입력값 순서대로 index를 부여
            island.add(new Island(i, x, y));
        }

        Collections.sort(island);

        // 결과 저장 배열
        int[] result = new int[N];

        // 정렬한 섬 순서대로, 자신보다 뒤쪽에 위치한 섬들의 개수를 res에 저장
        for(int i = 0; i<N; i++){
            // 정렬된 순서의 i번째 섬을 가져와서 그 섬의 입력 순서(index)를 가져옴
            // 총 섬(N)의 개수 - 현재 순서(i) - 자기자신(1)
            result[island.get(i).index] = N - i - 1;
        }

        for(int i = 0; i<N; i++){
            System.out.println(result[i]);
        }

    }

}
