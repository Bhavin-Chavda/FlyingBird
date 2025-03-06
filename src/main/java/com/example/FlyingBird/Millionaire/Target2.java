package com.example.FlyingBird.Millionaire;

public class Target2 {
    public static void main(String[] args) {

        int trades = 0;
        double n=100;

        long target = (long) Math.pow(2,33);
        double container = 0.0;

        System.out.println(target);

        int flag = 0,cnt=0;
        while(n<target)
        {
            flag++;
            cnt++;
            if(flag==3)
            {
                System.out.println("StopLoss Trade , Trade no : "+cnt);
                flag=0;

                n = n*0.7;
                trades++;
            }
            else{
                System.out.println("TP Trade , Trade no : "+cnt);

                n=n*1.40;

                container += n*0.05;
                n -= n*0.05;

                trades++;
            }
            System.out.println("Current Amount : "+(long)n);
            System.out.println("Current Container Value : "+(long)container);
            System.out.println("===========================");

        }
        System.out.println("Final Amount : "+(long)n);
        System.out.println("No of Trades needed : "+trades);
        System.out.println("Amount of Container : "+ (long)container);

        // Next Task:
        // Find Strategy that has accuracy over 67% almost around (70-72)%
        // Implement it in automatic Trading with Webhook to take trade automatically

        // Just Do it
    }
}
