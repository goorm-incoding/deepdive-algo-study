package com.javatraining.sorted;

import java.io.*;
import java.util.*;

public class BinarySorting {

    // 각 정수와 그 정수의 2진수 1의 개수를 저장하는 클래스
    static class Bsort implements Comparable<Bsort>{
        int value;
        int count;

        public Bsort(int value, int count){
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Bsort b){
            if(this.count != b.count){
                // 값이 양수면 swap, b.count이 큰값이 되면 내림차순
                // 1의 개수 내림차순
                return b.count - this.count;
            }else{
                // 값 내림차순
                return b.value - this.value;
            }
        }
    }

    // 2진수 변환 후 1의 개수 count
    static int oneCount(int n){
        int count = 0;
        while(n>0){
            if(n%2 == 1){
                count += 1;
            }
            n = n/2;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 정수의 수 N
        int N = Integer.parseInt(input[0]);
        // 찾으려는 정수의 위치 K
        int K = Integer.parseInt(input[1]);

        int[] arry = new int[N];
        // 정수의 종류
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i<N; i++){
            arry[i] = Integer.parseInt(input2[i]);
        }

        // 각 정수와 2진수 1의 개수를 Bsort 객체로 저장
        List<Bsort> bs = new ArrayList<>();
        for(int i = 0; i<N; i++){
            bs.add(new Bsort(arry[i],oneCount(arry[i])));
        }

        // Bsort 클래스의 compareTo 기준에 따라 정렬
        Collections.sort(bs);

        System.out.println(bs.get(K-1).value);
    }
}
