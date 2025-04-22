package com.javatraining.basicimplementation;

import java.io.*;

public class WhoIsTheWinner {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] Alice = new int[N];
        int[] Bob = new int[N];

        String[] line1 = br.readLine().split(" ");
        for (int i=0; i<N; i++){
            Alice[i] = Integer.parseInt(line1[i]);
        }

        String[] line2 = br.readLine().split(" ");
        for (int i=0; i<N; i++){
            Bob[i] = Integer.parseInt(line2[i]);
        }

        int AliceScore = 0;
        int BobScore = 0;

        for(int i=0; i<N; i++){

            int a = Alice[i];
            int b = Bob[i];

            if(a==b){
                AliceScore +=1;
                BobScore +=1;
            } else if (Math.abs(a-b) == 7){
                if(a>b){
                    AliceScore-=1;
                    BobScore+=3;
                }else{
                    AliceScore+=3;
                    BobScore-=1;
                }
            } else if (a>b){
                AliceScore += 2;
            } else{
                BobScore += 2;
            }

        }

        System.out.println(AliceScore + " " + BobScore);
    }
}
