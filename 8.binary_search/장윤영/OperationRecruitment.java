package com.javatraining.binarysearch;

import java.io.*;
import java.util.*;

public class OperationRecruitment {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 섭외 가능한 가수들의 수
        int N =Integer.parseInt(br.readLine());
        // 가수 팬클럽의 영향력
        String[] input = br.readLine().split(" ");
        long[] S = new long[N];
        for(int i = 0; i<N; i++){
            S[i] = Long.parseLong(input[i]);
        }

        // 이분탐색 시 선수 조건 정렬
        Arrays.sort(S);

        // 조건에 맞게 섭외할 수 있는 경우의 수
        long count = 0;
        // 3개의 인덱스를 뽑아서 비교해야하므로 뒤에 2개의 인덱스를 남겨 놓고 반복을 해야한다.
        for(int i = 0; i<N-2; i++){
            for(int j = i+1; j<N-1; j++){
                long sum = S[i]+S[j];
                int index = Arrays.binarySearch(S,j+1,N,sum);

                // 음수의 결과가 나오면 배열의 어느 위치에 추가될 수 있는지 확인한다.
                if(index<0){
                    index = -index -1;
                    // 값이 정확히 나온다면 영향력은 같으면 안되므로 인덱스를 하나 증가시켜서 다음 값을 기준으로 한다.
                }else{
                    index++;
                }

                // 인덱스가 j보다 큰지 확인하고 경수의 수를 구한다.
                // j+1와 index의 사이값의 개수를 세야한다.
                if(index>j+1){
                    count += (index-(j+1));
                }
            }
        }
        System.out.println(count);
    }
}
