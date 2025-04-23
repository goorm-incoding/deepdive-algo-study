package com.javatraining.basicalgorithmchallenge;

import java.io.*;

public class GoormRPG1 {

    static boolean isPrime(int n){
        if(n<2) return false;

        // Timeout
        //for(int i = 2; i<n; i++){
        // 어떤 수 n이 약수 a*b=n 꼴로 쌍을 이룰 때, 작은 수 a는 무조건 √n 이하
        // 대칭을 이룬다. 따라서 작은 수들을 비교해서 작은 한 곳만 확인
        for (int i = 2; i * i <= n; i++) {
            if(n%i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        // A>W, A%W =0 두 조건 둘다 만족해야한다. (A = 값옷 고유값 / W = 무기의 고유값)
        // 즉, 소수를 구하는 문제
        // A==W 인 경우 소수라도 체력이 감소할 수 있다고 봤지만 위에 두 조건을 만족해야되기 때문에 문제가 되지 않는다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // 소수인지 판별
        for(int i = 0; i<N; i++){
            if(isPrime(A[i])){
                System.out.println("Yes");
            } else{
                System.out.println("No");
            }
        }

    }
}
