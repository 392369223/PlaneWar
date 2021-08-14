package com.lzh.shoot;

public class Bullet extends Flyer{
    private int yspeed;
    /*
     * 子弹类的构造方法
     * x:英雄机的中心坐标或者四分位坐标
     * y:英雄机的纵坐标
     */
    public Bullet(int x,int y){
    	image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
    	yspeed=1;
    	this.x=x-width/2;
    	this.y=y;
    	
    }
	@Override
	public void step() {
		y=y-yspeed;
	}

	@Override
	public boolean outBounds() {
		return false;
	}
	//越界销毁
	@Override
	public boolean destroy() {
	    if(y<=-1*height){
	    	return true;
	    }else{
		    return false;
	    }
	}
    
}
