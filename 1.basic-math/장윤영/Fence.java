package com.javatraining.basicmath;

import java.util.*;

public class Fence {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = sc.nextInt();
        }

        int total = 0;
        total += 2*N;
        total += A[0];
        total += A[N-1];

        for (int i = 0; i<N-1; i++){
            total += Math.abs(A[i]-A[i+1]);
        }


        System.out.println(total);
    }
}
