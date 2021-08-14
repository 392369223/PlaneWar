package com.lzh.shoot;

import java.util.Random;

public class Bee extends Flyer {
	//奖励有两种类型。一种是双倍火力，一种是生命值
	public static final int DOUBLE_FIRE=0;
	public static final int LIFE=1;
	public static final Random r=new Random();
	private int xspeed=1;
	private int yspeed=1;
	private int awardType;
	public Bee(){
		image=ShootGame.bee;
		width=image.getWidth();
		height=image.getHeight();
		Random r1=new Random(); 
		x=r1.nextInt(ShootGame.WIDTH-width);
		y=-1*height;
		int result=r.nextInt(2);
		if(result==0){
			awardType=DOUBLE_FIRE;
		}else{
			awardType=LIFE;
		}
	}
	public int getAwardType(){
		return awardType;
	}
	@Override
	public void step() {
		x=x+xspeed;
		y=y+yspeed;	
	}
	public void setXspeed(){
		xspeed=-1*xspeed;
	}
	public boolean destroy(){
		boolean r1=y>ShootGame.HEIGHT;
		return r1;
	}
	@Override
	public boolean outBounds() {
		//判断是否水平越界，如果越界，水平速度，添加一个负号
		boolean r1=(x+xspeed)>0&(x+xspeed+width)<ShootGame.WIDTH;
		return !r1;
	}
	

}
