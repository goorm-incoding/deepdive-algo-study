package com.javatraining.basicalgorithmchallenge;

import java.io.*;
import java.util.*;

public class GameJam {

    // 명령어 별로 이동만들기
    static HashMap<String, int[]> dir = new HashMap<>();

    // 실제 좌표와 배열로 들어가는 값은 다르기 때문에 방향 값을 고려해야한다.
    static {
        dir.put("U", new int[]{-1, 0});
        dir.put("D", new int[]{1, 0});
        dir.put("R", new int[]{0, 1});
        dir.put("L", new int[]{0, -1});
    }

    // 이동중 보드밖으로 나가면 반대쪽 첫 번째 칸으로 이동
    static int setPos(int a, int N){
        if(a == -1) return N-1; // 마지막 인덱스로
        if(a == N) return 0; // 첫번째 인덱스로
        // 조건에 해당되지 않으면 그대로 다시 리턴
        return a;
    }
    // 게임 움직임
    static int move(int x, int y, boolean[][] visited, String[][] board, int N, int score){

        // 시작 위치 방문 처리
        visited[x][y] = true;
        // 이동을 계속 하는 경우 go
        boolean go = true;

        while(go){
            // 현재 x,y 칸에 선언된 명령어 가져오기
            String command = board[x][y];
            // 문자열에서 마지막 문자를 제외한 부분을 잘라내는 구문
            // 문자열의 첫 번째 문자부터 마지막 문자 바로 전까지를 가져오는 메서드
            int distance = Integer.parseInt(command.substring(0,command.length()-1));
            // 문자열의 문자 바로 전부터 마지막까지
            String direction = command.substring(command.length()-1);

            // distance 만큼 거리 이동
            for(int i = 0; i<distance; i++){
                // 정리해둔 명령어별로 위치 이동
                x += dir.get(direction)[0];
                y += dir.get(direction)[1];

                // 보드 크기 벗어나면 조정
                x = setPos(x, N);
                y = setPos(y, N);

                if(!visited[x][y]){
                    visited[x][y] = true;
                    score += 1;
                }else{
                    // 이미 방문한 곳이면 이동 종료
                    go = false;
                    break;
                }

            }

        }

        return score;

    }
    // 이미 방문한 칸이면 종료
    // 게임점수(승리조건) = 말이 방문한 서로 다른 칸의 개수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 격자 보드의 크기
        int N = Integer.parseInt(br.readLine());
        // 구름이의 좌표
        String[] goormInput = br.readLine().split(" ");
        // 좌표 입력은 1부터 시작, 배열 인덱스는 0부터 시작
        int goormX = Integer.parseInt(goormInput[0])-1;
        int goormY = Integer.parseInt(goormInput[1])-1;
        // 구름이 방문 위치 확인 선언
        boolean[][] goormVisited = new boolean[N][N];

        // 플레이어의 좌표
        String[] playerInput = br.readLine().split(" ");
        int playerX = Integer.parseInt(playerInput[0])-1;
        int playerY = Integer.parseInt(playerInput[1])-1;
        // 플레이어의 방문 위치 확인 선언
        boolean[][] playerVisited = new boolean[N][N];

        // 2차원 배열 생성
        String[][] board = new String[N][N];
        // board 입력값 초기화
        for(int i = 0; i<N; i++){

            board[i] = br.readLine().split(" ");

        }

        int goormScore = move(goormX, goormY, goormVisited, board, N, 1);
        int playerScore = move(playerX, playerY, playerVisited, board, N, 1);

        // 구름과 플레이어의 점수 중 더 높은 것을 출력한다.
        if(goormScore > playerScore){
            System.out.println("goorm "+ goormScore);
        } else{
            System.out.println("player "+ playerScore);
        }

    }
}
