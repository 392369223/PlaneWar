package com.lzh.shoot;

public class Bullet extends Flyer{
    private int yspeed;
    /*
     * �ӵ���Ĺ��췽��
     * x:Ӣ�ۻ���������������ķ�λ����
     * y:Ӣ�ۻ���������
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
	//Խ������
	@Override
	public boolean destroy() {
	    if(y<=-1*height){
	    	return true;
	    }else{
		    return false;
	    }
	}
    
}
