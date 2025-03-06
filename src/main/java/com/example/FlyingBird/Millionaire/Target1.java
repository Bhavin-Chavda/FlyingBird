package com.example.FlyingBird.Millionaire;

public class Target1 {
    public static void main(String[] args) {

        int n = 32;

        long target = 1;


        for(int i=0;i<=n;i++)
        {
            target = target*2;
            System.out.println("Target After Iteration : "+i+" , is : "+target);
        }



    }
}
