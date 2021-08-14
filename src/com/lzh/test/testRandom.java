package com.lzh.test;

import java.util.Random;

public class testRandom {
    public static void main(String[] args) {
		Random r=new Random();
		for(int i=0;i<10;i++){
		System.out.println(r.nextInt(2));
		}
	}
}
