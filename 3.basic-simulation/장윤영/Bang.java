package com.javatraining.basicsimulation;

import java.io.*;

public class Bang {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 적들의 수
        int N = Integer.parseInt(br.readLine());

        // 적들의 체력
        String[] input =  br.readLine().split(" ");
        int[] H = new int[N];
        for (int i = 0; i<N; i++){
            H[i] = Integer.parseInt(input[i]);
        }

        // 권총을 발사해야하는 횟수
        long count = 0;
        // 현재 발사 단계
        int ct = 0;

        /*
         * ((i - 1) % 4) + 1 을 반복하면 결국 1,2,3,4 를 반복한다
         * 따라서 현재 발사 단계로 ct를 지정해서 while 문으로 순환한다.
         */

        for(int i = 0; i<N; i++){

            // 적의 체력이 남아있는 동안 권총 발사를 원형큐로 만들어서 반복한다
            // 다음 적은 이미 1,2,3,4 중 중간에 발사 순서일 경우 그 시점부터 시작해야된다
            // 그것을 ct로 잡아서 조건 확인
            // 이번 발사 상태가 남아 있으면 먼저 처리!
            while(ct>0 && H[i] > 0){
                H[i] -= ct+1;
                ct = (ct+1)%4;
                count++;
            }

            // 적이 쓰러지면 넘어간다. i++
            if (H[i] <= 0){
                continue;
            }

            // H[i] >= 10 인 경우 한 번에 4발로 줄이기
            count += (H[i]/10) * 4;
            H[i] %= 10;

            // 남은 체력이 10 미만이면 다시 데미지 순서대로 발사
            while (H[i]>0){
                H[i] -= ct+1;
                ct = (ct+1)%4;
                count++;
            }

        }
        System.out.println(count);
    }
}

