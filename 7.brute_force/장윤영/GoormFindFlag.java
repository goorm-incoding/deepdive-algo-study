package com.javatraining.bruteforce;

import java.io.*;

public class GoormFindFlag {

    // 방향 이동 좌표
    static int[] dx = {0,0,-1,1,-1,1,-1,1};
    static int[] dy = {1,-1,0,0,1,1,-1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        // 게임판의 크기
        int N = Integer.parseInt(input[0]);
        // 찾고싶은 깃발의 값
        int K = Integer.parseInt(input[1]);

        // 구름위치 입력 받을 이중배열좌표
        int[][] matrix = new int[N][N];

        // 좌표 입력
        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }

        // K인 칸의 개수
        int countK = 0;

        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                // 구름이 없는 위치인 경우 반복 검사시작
                if (matrix[x][y] ==0){
                    // 주변에 구름이 있는지 검사
                    int goormCount = 0;
                    for(int i = 0; i<8; i++){
                        int px = dx[i]+x;
                        int py = dy[i]+y;

                        // 좌표 값을 벗어나는지 범위 확인
                        if(px>=0 && px<N && py>=0 && py<N){
                            if(matrix[px][py] == 1){
                                goormCount += 1;
                            }
                        }
                    }

                    if(goormCount == K){
                        countK += 1;
                    }
                }
            }
        }

        System.out.println(countK);
    }
}
