package com.javatraining.recurrence;

import java.io.*;

public class BiggerThanYou {

    // 엄청 큰 값을 미리 구해 놓는다.
    static final long INF = Long.MAX_VALUE;
    static long result = INF;

    static int N;
    static long K;
    static int[] A;

    // A 값으로 이루어진 K 값보다 큰 최소값 찾기
    static void bigger(long now){
        if (now > K){
            result = Math.min(now, result);
            return;
        }
        for(int i=0; i<N; i++){
            // 다음 숫자를 구한다.
            long next = now*10 + A[i];
            // 다음 숫자가 0이면 다음 숫자로 넘어간다.
            if(next == 0) continue;
            bigger(next);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 구름이가 사용할 수 있는 숫자의 개수
        N = Integer.parseInt(br.readLine());

        // 사용할 수 있는 숫자 배열
        A = new int[N];

        String[] input = br.readLine().split(" ");
        for(int i= 0; i<N; i++){
            A[i] = Integer.parseInt(input[i]);
        }

        // 불량배 양의 정수 K
        K = Long.parseLong(br.readLine());

        // 재귀함수 0부터 시작
        bigger(0);

        // 출력
        System.out.println(result);
    }

}
