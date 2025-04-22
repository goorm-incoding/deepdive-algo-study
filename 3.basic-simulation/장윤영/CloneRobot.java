package com.javatraining.basicsimulation;

import java.io.*;

public class CloneRobot {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 현재 로봇 위치
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        // 웅덩이 개수
        int N = Integer.parseInt(br.readLine());

        // 웅덩이 위치
        int[] px = new int[N]; // x 값들만 모은 배열
        int[] py = new int[N]; // y 값들만 모은 배열
        for(int i = 0; i<N; i++){
            String[] input1 = br.readLine().split(" ");
            px[i] = Integer.parseInt(input1[0]);
            py[i] = Integer.parseInt(input1[1]);
        }

        // 조종 횟수
        int Q = Integer.parseInt(br.readLine());
        // 명령
        String[] C = br.readLine().split(" ");

        for(int i = 0; i<Q; i++){
            String action = C[i];
            int nextX = x;
            int nextY = y;

            // 명령어 별 다음 위치
            // 좌,우,상,하
            switch (action) {
                case "L":
                    nextX -= 1;
                    break;
                case "R":
                    nextX += 1;
                    break;
                case "U":
                    nextY += 1;
                    break;
                case "D":
                    nextY -= 1;
                    break;
            }

            // 웅덩이랑 이동위치랑 비교
            boolean pos = true;
            for (int j = 0; j<N; j++){
                if(px[j] == nextX && py[j] == nextY){
                    pos = false;
                    break;
                }
            }

            // 웅덩이가 true일 경우만 이동
            if(pos){
                x = nextX;
                y = nextY;
            }

        }

        System.out.println(x + " " + y);
    }
}
