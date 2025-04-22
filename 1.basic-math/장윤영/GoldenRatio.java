package com.javatraining.basicmath;
import java.util.*;

public class GoldenRatio {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수
        int count = 0;

        for(int i = 0; i<T; i++){
            long A = sc.nextLong();
            long B = sc.nextLong();

            long small = Math.min(A, B);
            long big = Math.max(A, B);

            double startmultiply = small*1.6;
            double endmultiply = small*1.63;

            if(startmultiply <= big && endmultiply >= big){
                count += 1;
            }

        }

        System.out.println(count);
    }
}
