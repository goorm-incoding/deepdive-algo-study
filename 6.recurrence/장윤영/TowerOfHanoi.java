package com.javatraining.recurrence;

import java.io.*;
import java.util.*;

public class TowerOfHanoi {

    // 원반 이동 과정을 저장할 리스트
    static List<int[]> moves = new ArrayList<>();

    // 하노이의 탑 재귀함수
    static void hanoi(int n, int start, int end, int second){

        // 원반이 1인 경우 바로 끝점으로 옮긴다.
        if(n==1){
            // 실제로 이동하는 처음, 끝 막대만 작성
            moves.add(new int[]{start, end});
            return;
        }

        // n-1개의 원반을 중간지점으로 옮긴다.
        hanoi(n-1,start,second,end);

        // 마지막 원반을 끝점으로 옮긴다.
        moves.add(new int[]{start, end});

        // 중간지점에 있던 n-1개의 원반을 끝점으로 옮긴다.
        hanoi(n-1,second,end,start);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // K번째 이동
        int K = Integer.parseInt(br.readLine());

        // 하노이의 탑 실행
        hanoi(20,1,3,2);

        // 각 막대기에 있는 원반을 저장할 리스트 배열
        List<Integer>[] sticks = new ArrayList[4];
        // sticks의 인덱스를 start, second, end 와 맞춰준다.
        for(int i = 1; i<4; i++){
            sticks[i] = new ArrayList<>();
        }

        // 처음 모든 원반이 첫 번째 막대에 존재
        // 큰 것부터(20부터) 저장
        for(int i = 20; i>0; i--){
            sticks[1].add(i);
        }

        // K번째 이동
        for(int i = 0; i<K; i++){
            int start = moves.get(i)[0];
            int end = moves.get(i)[1];

            // 원반은 리스트의 끝에 들어가기 때문에, 리스트의 마지막 원소가 가장 위에 있는 원반
            // 맨 위에 있는 원반 위치의 원반을 리스트에서 제거하고, 그 값을 반환
            int disk = sticks[start].remove(sticks[start].size()-1);
            sticks[end].add(disk);
        }

        // 각 기둥의 원반 크기 합 출력
        for(int i = 1; i<4; i++){
            int total = 0;
            for(int disk:sticks[i]){
                total += disk;
            }
            // 마지막이면 줄바꿈, 아니면 공백
            if(i<3){
                System.out.print(total+" ");
            }else{
                System.out.println(total);
            }
        }

    }
}
