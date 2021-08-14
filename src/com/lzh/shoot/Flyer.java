package com.lzh.shoot;

import java.awt.image.BufferedImage;

public abstract class Flyer {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    //在java中保存一张图片用BufferedImage
    protected BufferedImage image;
    //移动
    public abstract void step();
    //越界检查
    public abstract boolean outBounds();
    //销毁方法
    public abstract boolean destroy();
    //获取中心点坐标
    public int[] getCenter(){
    	int[] r=new int[2];
    	r[0]=(width+x+x)/2;
    	r[1]=(height+y+y)/2;
    	return r;
    }
    public int getWidth(){
    	return width;
    }
    public int getHeight(){
    	return height;
    }
}
