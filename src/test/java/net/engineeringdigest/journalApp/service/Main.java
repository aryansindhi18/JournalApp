package net.engineeringdigest.journalApp.service;

import java.io.Console;
import java.util.*;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Main{
    public static void main(String[] args){
        //Debugwithcustominputs
        System.out.println(createShortNumeral("LLLXXXVVVV").equals("CC"));
        //Addallyourtestcaseshere
        System.out.println(createShortNumeral("MMMM").equals("MMMM"));
        System.out.println(createShortNumeral("DD").equals("M"));
        System.out.println(createShortNumeral("CCCC").equals("CD"));
        System.out.println(createShortNumeral("XXXX").equals("XL"));
        System.out.println(createShortNumeral("IIII").equals("IV"));
        System.out.println(createShortNumeral("VV").equals("X"));
        //System.out.println(createShortNumeral("LLLXXXVVVV").equals("CC"));

        //DONOTMODIFY.Executesin-builttestcases.
//        TestMain.executeJunitTests();
    }

    //Fixthebugsinthegivencodewithoutchangingtheimplementationlogic.
    //NOCODESHOULDBEREMOVED.
    public static String createShortNumeral(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        // HashMap<String, Integer> map = new HashMap();
        map.put('I',1);
        map.put('V',5);
        map.put('L',50);
        map.put('D',500);
        map.put('X',10);
        map.put('C',100);
        map.put('M',1000);

        int result=0;
        for (char ch : str.toCharArray()) {
            result += map.get(ch);
        }

        // for(int i = str.length();i >= 1; ++i) {
        //     result = map.get(str.charAt(i));
        // }


        //example - 200
        // k=1
        //  div = 200%100 =>0
        //  k = 1, div = 0
        //  did not go inside for loop
        /*String res="";
        int div=0;

        if(result>1000){
            div=result%1000;
            for(int k=1;k<div;++k)
                res=res+'M';
            result=result/1000;
        }


        if(result>100){
            div=result%100;
            for(int k=1;k<div;++k)
                res=res+'C';
            result=result/100;
        }

        if(result>10){
            div=result%10;
            for(int k=1;k<div;++k)
                res=res+'X';
            result=result/10;
        }

        if(result>1){
            div=result%5;
        for(int k=1;k<div;++k)
        res=res+'V';
        result=result/5;
        }

        if(result>500){
            div=result%500;
            for(int k=1;k<div;++k)
                res=res+'D';
            result=result/500;
        }

        if(result>50){
            div=result%50;
            for(int k=1;k<div;++k)
                res=res+'L';
            result=result/50;
        }

        if(result>5){
            div=result%5;
            for(int k=1;k<div;++k)
                res=res+'V';
            result=result/5;
        }
        return"L";*/
        // Convert integer back to minimal Roman numeral representation
        StringBuilder res = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (result >= values[i]) {
                res.append(numerals[i]);
                result -= values[i];
            }
        }

        return res.toString();
    }

}