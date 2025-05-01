package com.javatraining.bruteforce;

import java.io.*;

public class GoormRPG2 {

    // 소수 판별
    static boolean is_Prime(int n){
        if(n<2) return false;
        for(int i = 2; i*i<=n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 갑옷의 개수
        int N = Integer.parseInt(br.readLine());

        // 갑옷의 고유값
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // 판타스틱한 갑옷은 소수값을 가지고 소수가 아닐 때마다 갑옷 변경 시스템 1회 사용
        for(int i = 0; i<N; i++){
            int count = 0;
            while(!is_Prime(A[i] - count)){
                count++;
            }
            System.out.println(count);
        }
    }

}
