package com.lzh.shoot;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lzh.utils.CollideUtil;

public class ShootGame extends JPanel {
	public static final int HEIGHT=700;
    public static final int WIDTH=400;
    public static final int START=0;
    public static final int RUNNING=1;
    public static final int PAUSE=2;
    public static final int GAMEOVER=3;
    public int state=0;
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bee;
    public static BufferedImage airplane;
    public static BufferedImage bullet;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    
    static{
    	try {
			background=ImageIO.read(ShootGame.class.getResource("/image/background.png"));
			start=ImageIO.read(ShootGame.class.getResource("/image/start.png"));
			hero0=ImageIO.read(ShootGame.class.getResource("/image/Hero.png"));
			bee=ImageIO.read(ShootGame.class.getResource("/image/Bee.png"));
			airplane=ImageIO.read(ShootGame.class.getResource("/image/Airplane.png"));
			bullet=ImageIO.read(ShootGame.class.getResource("/image/bullet.png"));
			pause=ImageIO.read(ShootGame.class.getResource("/image/pause.png"));
			gameover=ImageIO.read(ShootGame.class.getResource("/image/gameover.png"));
			//hero1=ImageIO.read(ShootGame.class.getResource("/image/hero1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    //һ��Ӣ�ۻ�
    Hero hero=new Hero();
    //�ܶ�л�
    Airplane[] airplanes={};
    //�ܶ��۷�
    Bee[] bees={};
    //����ܶ��ӵ�
    Bullet[] bullets={};
    public static void main(String[] args) {
		JFrame jf=new JFrame("Fly");
		jf.setSize(WIDTH+20,HEIGHT+50);
		//���������Ϸ�
		jf.setAlwaysOnTop(true);
		//���þ���
		jf.setLocationRelativeTo(null);
		ShootGame panel=new ShootGame();
		jf.add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setResizable(false);
		panel.action();
	}
    public void action(){
    	//������������
    	MouseAdapter l=new MouseAdapter(){
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING){
				//����ƶ�
				int x=e.getX();
				int y=e.getY();
				hero.move(x, y);
				}
			}	
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(state==START){
					state=RUNNING;
				}else if(state==GAMEOVER){
					state=START;
					//���³�ʼ�����ݽṹ
					hero=new Hero();
					bees=new Bee[]{};
					airplanes=new Airplane[]{};
					bullets=new Bullet[]{};
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE){
					state=RUNNING;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING){
					state=PAUSE;
				}
			}
    	};
    	this.addMouseMotionListener(l);
    	this.addMouseListener(l);
    	//hero.setDoubleFire();
    	//����һ����ʱ��
    	Timer t=new Timer();
    	t.schedule(new TimerTask(){
    		private int mytimer=0;
			@Override
			public void run() {
				if(state==RUNNING){
				if(mytimer%50==0){
			    //��������
				nextEnemy();
				}
				if(mytimer%40==0){
					//�����ӵ�
					nextBullets();
				}
				mytimer++;
				//�ƶ�
				step();
				fit1();
				fit2();
				destroy();
			    repaint();
				}else if(state==START){
					repaint();
				}else if(state==PAUSE){
					repaint();
				}else{
					repaint();
				}
			}		
    	}, 10,10);
    }
    @Override
	public void paint(Graphics g) {
    	//���ø���ķ��������ģ���������ã�ԭ����ͼ�񲻻ᱻ�����һƬ���
    	super.paint(g);
    	/*paintBackground(g);
    	paintHero(g);
    	paintBullets(g);
    	paintAirplanes(g);
    	paintBees(g);
    	paintLifeAndScore(g);*/
    	paintBackground(g);
    	if(state==START){
    		paintStart(g);
    	}else if(state==RUNNING){
    		paintRun(g);
    	}else if(state==PAUSE){
    		paintPause1(g);
    	}else{
    		paintEnd(g);
    	}
	}
    //������������
    public void paintStart(Graphics g){
    	//���ڽ����й��������뵽��Ϸ��ʼ����
    	g.drawImage(start,0,0,null);
    }
    //���ƽ��н���
    public void paintRun(Graphics g){
    	paintHero(g);
    	paintBullets(g);
    	paintAirplanes(g);
    	paintBees(g);
    	paintLifeAndScore(g);
    }
    //������ͣ����
    public void paintPause1(Graphics g){
    	paintHero(g);
    	paintBullets(g);
    	paintAirplanes(g);
    	paintBees(g);
    	paintLifeAndScore(g);
    }
    public void paintEnd(Graphics g){
    	g.drawImage(gameover,0,0,null);
    }
    //���Ʒ���������ֵ
    public void paintLifeAndScore(Graphics g){
    	String str="score: "+hero.getScore();
    	String str2="life: "+hero.getLife();
    	Font font=new Font(Font.SANS_SERIF,Font.BOLD,14);
    	g.setFont(font);
    	int x=10;
    	int y=15;
    	g.drawString(str, x, y);
    	g.drawString(str2,x,y+20);
    }
    //���Ʊ���
    public void paintBackground(Graphics g){
    	g.drawImage(background,0,0,null);
    }
    //������ͣ
    public void paintPause(Graphics g){
    	g.drawImage(pause,0,0,null);
    }
    //����gameoverͼƬ
    public void paintGameOver(Graphics g){
    	g.drawImage(gameover,0,0,null);
    }
    //����Ӣ�ۻ�
    public void paintHero(Graphics g){
    	g.drawImage(hero.image, hero.x, hero.y, null);
    }
    public void paintBullets(Graphics g){
    	for(Bullet bullet:bullets){
    		g.drawImage(bullet.image,bullet.x,bullet.y,null);
    	}
    }
    public void paintBees(Graphics g){
    	for(Bee bee:bees){
    		g.drawImage(bee.image,bee.x,bee.y,null);
    	}
    }
    public void paintAirplanes(Graphics g){
    	for(Airplane plane:airplanes){
    		g.drawImage(plane.image,plane.x,plane.y,null);
    	}
    }
    //�����ƶ�����
    public void step(){
    	for(Bullet bullet:bullets){
           bullet.step();
    	}
    	for(Bee bee:bees){
    		if(bee.outBounds()){
    	        bee.setXspeed();
    		    bee.step();
    		}else{
    			bee.step();	
    		}
    	}
    	for(Airplane plane:airplanes){
    		plane.step();
    	}
    }
    public void paintTest(Graphics g){
    	//g.drawImage(airplane,0,0,null);
    	//g.drawImage(bee,100,100,null);
    	//g.drawImage(bullet,0,0,null);
    	//g.drawImage(pause,0,0,null);
    	g.drawImage(gameover,0,0,null);
    }
	public void nextBullets(){
    	Bullet[] b=hero.shoot();
    	if(b.length==1){
    		//���ӵ���������
    		//��������һ������
    		bullets=Arrays.copyOf(bullets, bullets.length+1);
    		bullets[bullets.length-1]=b[0];
    	}else{
    		//���ӵ���������
    		//����������������
    		bullets=Arrays.copyOf(bullets, bullets.length+2);
    		bullets[bullets.length-2]=b[0];
    		bullets[bullets.length-1]=b[1];
    	}
    }
    public void nextEnemy(){
        Random r=new Random();
        int result=r.nextInt(5);
        if(result==0){
        	//����һ���۷������ӵ��۷��������
        	Bee b=new Bee();
        	bees=Arrays.copyOf(bees,bees.length+1);
        	bees[bees.length-1]=b;
        }else{
        	//����һ���л�������ӵ��л��������
        	Airplane plane=new Airplane();
        	airplanes=Arrays.copyOf(airplanes,airplanes.length+1);
        	airplanes[airplanes.length-1]=plane;
        }
    }
    //Խ�����ٺ���
    public void destroy(){
    	for(int i=0;i<bullets.length;i++){
    		if(bullets[i].destroy()){
    			for(int j=i;j<bullets.length-1;j++){
    				bullets[j]=bullets[j+1];
    			}
    			bullets[bullets.length-1]=null;
    			bullets=Arrays.copyOf(bullets, bullets.length-1);
    			i--;
    		   // System.out.println("�ӵ���������");
    		}
    	}
    	for(int i=0;i<airplanes.length;i++){
    		if(airplanes[i].destroy()){
    			for(int j=i;j<airplanes.length-1;j++){
    				airplanes[j]=airplanes[j+1];
    			}
    			airplanes[airplanes.length-1]=null;
    			airplanes=Arrays.copyOf(airplanes, airplanes.length-1);
    			i--;
    		}
    	}
    	for(int i=0;i<bees.length;i++){
    		if(bees[i].destroy()){
    			for(int j=i;j<bees.length-1;j++){
    				bees[j]=bees[j+1];
    			}
    			bees[bees.length-1]=null;
    			bees=Arrays.copyOf(bees, bees.length-1);
    			i--;
    		}
    	}
    }
    //�ӵ�ײ���Ĵ�����
    public void fit1(){
    	for(int i=0;i<bullets.length;i++){
    		for(int j=0;j<bees.length;j++){
    			if(CollideUtil.collideWithBee(bullets[i], bees[j])){
    				hero.getAward(bees[j]);
    				//���ٵ��ӵ�
    				for(int m=i;m<bullets.length-1;m++){
    					bullets[m]=bullets[m+1];
    				}
    				//����
    				bullets[bullets.length-1]=null;
    				bullets=Arrays.copyOf(bullets, bullets.length-1);
    				//���ٵ��۷�
    				for(int m=j;m<bees.length-1;m++){
    					bees[m]=bees[m+1];
    				}
    				bees[bees.length-1]=null;
    				bees=Arrays.copyOf(bees, bees.length-1);
    				i--;
    				break;
    			}
    			//System.out.println("bees:"+bees.length);
    			//System.out.println("bullets:"+bullets.length);
    		} 
    	}
    	for(int i=0;i<bullets.length;i++){
    		for(int j=0;j<airplanes.length;j++){
    			if(CollideUtil.collideWithAir(bullets[i], airplanes[j])){
    				hero.getAward(airplanes[j]);
    				for(int m=i;m<bullets.length-1;m++){
    					bullets[m]=bullets[m+1];
    				}
    				//����
    				bullets[bullets.length-1]=null;
    				bullets=Arrays.copyOf(bullets, bullets.length-1);
    				//���ٵ��۷�
    				for(int m=j;m<airplanes.length-1;m++){
    					airplanes[m]=airplanes[m+1];
    				}
    				airplanes[airplanes.length-1]=null;
    				airplanes=Arrays.copyOf(airplanes,airplanes.length-1);
    				i--;
    				break;
    			}
    		} 
    	}
    }
    //Ӣ�ۻ�ײ���Ĵ�����
    public void fit2(){
    	for(int i=0;i<bees.length;i++){
    		if(hero.fitWithBee(bees[i])){
    			//Ӣ�ۻ���óͷ�
    			hero.getPunishment(bees[i]);
    			if(hero.getLife()==0){
    				state=GAMEOVER;
    				return ;
    			}
    		   //�����۷�
    			for(int m=i;m<bees.length-1;m++){
    				bees[m]=bees[m+1];
    			}
    			bees[bees.length-1]=null;
    			i--;
    			bees=Arrays.copyOf(bees,bees.length-1);
    		}
    	}
        for(int i=0;i<airplanes.length;i++){
    		if(hero.fitWithAir(airplanes[i])){
    			//Ӣ�ۻ�
    			hero.getPunishment(airplanes[i]);
    			if(hero.getLife()==0){
    				state=GAMEOVER;
    				return ;
    			}
    			
    			//���ٵл�
    			for(int m=i;m<airplanes.length-1;m++){
    				airplanes[m]=airplanes[m+1];
    			}
    			airplanes[airplanes.length-1]=null;
    			i--;
    			airplanes=Arrays.copyOf(airplanes,airplanes.length-1);
    		}
    	}
    }
}
