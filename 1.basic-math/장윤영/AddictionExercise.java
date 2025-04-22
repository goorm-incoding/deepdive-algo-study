package com.javatraining.basicmath;
import java.util.*;

public class AddictionExercise {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int W = sc.nextInt();
        int R = sc.nextInt();

        double RM = W*(1+R/30.0);

        System.out.println((int)RM);
    }
}
