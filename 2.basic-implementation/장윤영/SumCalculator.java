package com.javatraining.basicimplementation;

import java.io.*;

public class SumCalculator {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int sum = 0;

        for (int i = 0; i<T; i++){
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            String operator = line[1];
            int num2 = Integer.parseInt(line[2]);

            int result = 0;

            switch(operator){
                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    result = num1 / num2;
                    break;
            }

            sum += result;

        }

        System.out.println(sum);
    }
}
