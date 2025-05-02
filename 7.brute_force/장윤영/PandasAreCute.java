package com.javatraining.bruteforce;

import java.io.*;

public class PandasAreCute {

    static int K;
    static int[] R, C;

    static int solves(int r, int c){
        // 불만족도
        int dis = 0;
        for(int i = 0; i<K; i++){
            dis += (r-R[i])*(r-R[i]) + (c-C[i])*(c-C[i]);
        }
        return dis;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 숲의 행 개수
        int N = Integer.parseInt(input[0]);
        // 숲의 열 개수
        int M = Integer.parseInt(input[1]);
        // 판다 수
        K = Integer.parseInt(input[2]);

        // 판다의 위치 행 배열
        R = new int[K];
        // 판다의 위치 열 배열
        C = new int[K];
        for(int i = 0; i<K; i++){
            String[] input1 = br.readLine().split(" ");
            R[i] = Integer.parseInt(input1[0]);
            C[i] = Integer.parseInt(input1[1]);
        }

        // 최소값구할 결과
        int min = Integer.MAX_VALUE;

        // 가능한 위치 확인
        for(int r = 1; r<=N; r++){
            for(int c = 1; c<=M; c++){

                // 판다가 있는지 확인
                boolean isValid = true;
                for(int i=0; i<K; i++){
                    if(r==R[i] && c==C[i]){
                        isValid = false;
                        break;
                    }
                }
                if(!isValid) continue;

                // 판다가 없으면 solve 함수 실행
                min = Math.min(min, solves(r,c));

            }
        }
        System.out.println(min);
    }

}
