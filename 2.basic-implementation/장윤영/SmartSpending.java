package com.javatraining.basicimplementation;

import java.io.*;

public class SmartSpending {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String maxItem = "";
        String minItem = "";
        int maxPrice = 0;
        int minPrice = 100000;

        for (int i=0; i<N; i++){
            String[] item = br.readLine().split(" ");
            String name = item[0];
            int price = Integer.parseInt(item[1]);

            if(price > maxPrice){
                maxPrice = price;
                maxItem = name;
            }
            if(price < minPrice){
                minPrice = price;
                minItem = name;
            }

        }

        System.out.println(maxItem + " " + maxPrice);
        System.out.println(minItem + " " + minPrice);
    }
}
