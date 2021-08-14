package com.lzh.shoot;

import java.util.Random;

public class Hero extends Flyer{
	public static final int LIVE=0;
	public static final int DEAD=1;
	private int doubleFire;
	private int  score;
	private int life;
	public Hero(){
		//获取图片
		image=ShootGame.hero0;
		width=image.getWidth();
		height=image.getHeight();
		x=ShootGame.WIDTH/2-width/2;
		y=ShootGame.HEIGHT-height;
		doubleFire=0;
		score=0;
		life=3;
	}
	public int getScore(){
		return score;
	}
	public int getLife(){
		return life;
	}
	public Bullet[] shoot(){
		if(doubleFire>0){
			Bullet[] b=new Bullet[2];
			b[0]=new Bullet(x+width/4,y);
			b[1]=new Bullet(x+width*3/4,y);
			doubleFire=doubleFire-2;
			return b;
		}else{
		    Bullet[] b=new Bullet[1];
		    b[0]=new Bullet(x+width/2,y);
		    return b;
		}
	}
	@Override
	public void step() {
		Random r=new Random();
		int result=r.nextInt(2);
		if(result==0){
			image=ShootGame.hero0;
		}else{
			image=ShootGame.hero0;
		}
	}
	
	/**
	 * @param x:表示鼠标的横坐标
	 * @param y:表示鼠标的纵坐标
	 */
	public void move(int x,int y){
		this.x=x-width/2;
		this.y=y-height/2;
	}
	@Override
	public boolean outBounds() {
	    boolean r1=x>0&(x+width)<ShootGame.WIDTH;
	    boolean r2=y>0&(y+height)<ShootGame.HEIGHT;
		return !(r1&r2);
	}
	@Override
	public boolean destroy() {
		return false;
	}
	public boolean fitWithAir(Airplane plane){
		int[] m1=plane.getCenter();
		int[] m2=getCenter();
		if(Math.abs(m1[0]-m2[0])<(width+plane.width)/2&&Math.abs(m1[1]-m2[1])<(height+plane.height)/2){
			return true;
		}else{
			return false;
		}
	}
    public boolean fitWithBee(Bee bee){
    	int[] m1=bee.getCenter();
		int[] m2=getCenter();
		if(Math.abs(m1[0]-m2[0])<(width+bee.width)/2&&Math.abs(m1[1]-m2[1])<(height+bee.height)/2){
			return true;
		}else{
			return false;
		}
    }
    public void getAward(Bee bee){
    	if(bee.getAwardType()==Bee.DOUBLE_FIRE){
    		doubleFire=doubleFire+10;
    	}else{
    		life=life+1;
    	}
    }
    public void getAward(Airplane plane){
    	int num=plane.getAwardScore();
    	score=score+num;
    }
    public int getPunishment(Bee bee){
    	life=life-1;
    	if(life==0){
    		return DEAD;
    	}else{
    		return LIVE;
    	}
    }
    public int getPunishment(Airplane plane){
    	life=life-1;
    	if(life==0){
    		return DEAD;
    	}else{
    		return LIVE;
    	}
    }
}
