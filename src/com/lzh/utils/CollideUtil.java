package com.lzh.utils;

import com.lzh.shoot.Airplane;
import com.lzh.shoot.Bee;
import com.lzh.shoot.Bullet;

public class CollideUtil {
	//ÅÐ¶Ï×Óµ¯ºÍµÐ»úÅö×²
    public static boolean collideWithAir(Bullet bullet,Airplane airplane){
    	int[] m1=bullet.getCenter();
		int[] m2=airplane.getCenter();
		boolean r1=Math.abs(m1[0]-m2[0])<(bullet.getWidth()+airplane.getWidth())/2;
		boolean r2=Math.abs(m1[1]-m2[1])<(bullet.getHeight()+airplane.getHeight())/2;
		return r1&r2;
    }
    //ÅÐ¶Ï×Óµ¯ºÍÃÛ·äÏà×²
    public static boolean collideWithBee(Bullet bullet,Bee bee){
    	int[] m1=bullet.getCenter();
		int[] m2=bee.getCenter();
		boolean r1=Math.abs(m1[0]-m2[0])<(bullet.getWidth()+bee.getWidth())/2;
		boolean r2=Math.abs(m1[1]-m2[1])<(bullet.getHeight()+bee.getHeight())/2;
		return r1&r2;
    }
}
