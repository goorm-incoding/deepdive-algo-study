package com.javatraining.basicalgorithmchallenge;

import java.io.*;

public class TimeComplexity {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long count = 0;
        long n5 = 5;

        while(N/n5 >0){
            count += N/n5;
            n5 *=5;
        }

        System.out.println(count);
    }
}
