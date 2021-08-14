package com.lzh.shoot;

import java.util.Random;

public class Airplane extends Flyer{
    //因为敌机会垂直移动，所以有y方向的速度
	private int yspeed=2;
	private int awardScore=5;
	public Airplane(){
		image=ShootGame.airplane;
		width=image.getWidth();
		height=image.getHeight();
		Random r1=new Random(); 
		x=r1.nextInt(ShootGame.WIDTH-width);
		y=-1*height;
	}
	public int getAwardScore(){
		return awardScore;
	}
	@Override
	public void step() {
		y=y+yspeed;
	}
	@Override
	public boolean outBounds() {
		return false;
	}
	@Override
	public boolean destroy() {
		if(y>=ShootGame.HEIGHT){
			return true;
		}else{
			return false;
		}
	}
	
	
}
