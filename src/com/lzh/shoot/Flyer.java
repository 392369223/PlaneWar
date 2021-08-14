package com.lzh.shoot;

import java.awt.image.BufferedImage;

public abstract class Flyer {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    //��java�б���һ��ͼƬ��BufferedImage
    protected BufferedImage image;
    //�ƶ�
    public abstract void step();
    //Խ����
    public abstract boolean outBounds();
    //���ٷ���
    public abstract boolean destroy();
    //��ȡ���ĵ�����
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
