package cn.qingyun.gamepanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GamePanel extends JFrame implements MouseListener {

	 public GamePanel(){ 
		 this.setTitle("������������");
		 this.setSize(420,420);
		 this.setResizable(false);
		 int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		 int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		 this.setLocation((width-300)/2,(height-300)/2);
		 this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		 this.addMouseListener(this);
		 this.setVisible(true);
	 }
	 //����������ĵ�ǰ����
	 int x=0,y=0;
	 //���������¹������ӣ�1��ʾ���ӣ�2��ʾ���ӣ�0��ʾû������
	 int[][] allChess=new int[16][16];
	 boolean isOver=false;
	 //����һ��������ڼ�¼�Ƿ��Ǻڷ�
	 boolean isBlack=true;
	 String blackTime="������";
	 String whiteTime="������";
	 //��ʾ��Ϣ
	 String message="�ڷ�ʱ��";
	 //��¼��ǰ�������ά��������ӵ�x��y
	 int x1,y1;
    //������ʾ
		public void paint(Graphics g) {
			 BufferedImage image=null;
			 try {
				  image= ImageIO.read(getClass().getClassLoader().getResource("./cn/qingyun/img/fivechess-backage.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(image,0,20,this);
			//˫���弼����ֹ��Ļ��˸
			BufferedImage bi=new BufferedImage(420,420,BufferedImage.TYPE_INT_ARGB);
			Graphics g2=bi.createGraphics();
			g2.setFont(new Font("����",25,25));
			g2.setColor(Color.black);
			g2.drawString("��ʾ��Ϣ:",6,60);
			g2.drawString(message, 128, 60);
			g2.drawRect(14, 380,130, 30);
			g2.drawRect(180,380,130,30);
			g2.setFont(new Font("����",13,13));
			g2.drawString("�ڷ�ʱ�䣺"+blackTime, 18, 400);
			g2.drawString("�׷�ʱ�䣺"+whiteTime, 183, 400);
			g2.setFont(new Font("����",35,35));
			g2.setColor(Color.blue);
		    g2.drawString("����", 330, 380);
		    g2.drawString("�¾�", 330, 100); 
		    g2.drawString("����", 330, 210); 
		    g2.drawString("�˳�", 330, 325);    
		    g2.drawString("����", 330, 265);        
		    g2.drawString("����", 330, 155);       
		    g2.setColor(Color.black);
			for(int i=0;i<16;i++){//�������̸�����
					g2.drawLine(13,72+20*i,313,72+20*i);
					g2.drawLine(13+20*i,72, 13+20*i, 372);	
			}
		    g2.fillOval(170, 230, 6, 6);
		    g2.fillOval(170, 110, 6, 6);
		    g2.fillOval(170, 350, 6, 6);
		    g2.fillOval(50, 230, 6, 6);
		    g2.fillOval(290, 230, 6, 6);
		    g2.fillOval(290, 350, 6, 6);
		    g2.fillOval(290, 110, 6, 6);
		    g2.fillOval(50, 350, 6, 6);
		    g2.fillOval(50, 110, 6, 6);
			//g.fillOval(x,y,10,10);
			
			//ѭ����ʾ������е�����
			for(int i=0;i<16;i++){
				for(int j=0;j<16;j++){
						if(allChess[i][j]==1){//����
							int tempX=i*20+13;
							int tempY=j*20+72;
							g2.fillOval(tempX-7, tempY-7, 14, 14);
						}
						else if(allChess[i][j]==2){//����
							int tempX2=i*20+13;
							int tempY2=j*20+74;
							g2.setColor(Color.white);
							g2.fillOval(tempX2-7, tempY2-7, 14, 14);
							g2.setColor(Color.black);
							g2.drawOval(tempX2-7, tempY2-7,14, 14);
						}
					
					
				}
			}
			g.drawImage(bi,0,0,this);
		}


	//������ļ���
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("������¼�");
	}
  //������ļ���
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("������");
	}
    //����뿪�ļ���
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("����뿪");
	}
  //��갴�µļ���
	@Override
	public void mousePressed(MouseEvent e) {
		if(!isOver){
			 x=e.getX();
			 y=e.getY();
			 //��ʼһ������Ϸ
			 if(x>=332&&x<=395&&y>=72&&y<=107){
				 int newGame=JOptionPane.showConfirmDialog(this, "��ʼһ������Ϸ��");
				 if(newGame==0){
					 for(int i=0;i<16;i++){
							for(int j=0;j<16;j++){
								allChess[i][j]=0;
								isOver=false;
								isBlack=true;
								message="�ڷ�ʱ��";
								this.repaint();
							}
					 }
				 }
			 }
			 //����
			 if(x>=334&&x<=400&&y>=184&&y<=215){
				 JOptionPane.showMessageDialog(this, "����һ�������壬�������һ�����Ӯ�������������壬��ѯ����ѯ����");
			 }
			 //�˳�
			 if(x>=336&&x<=398&&y>=304&&y<=330){
				 int exit=JOptionPane.showConfirmDialog(this,"ȷ���˳���");
				 if(exit==0){
					 System.exit(0);
				 }
			 }
			//����
			 if(x>=337&&x<=398&&y>=240&&y<=269){
				 int loseWin=JOptionPane.showConfirmDialog(this,"ȷ��������");
				 if(loseWin==0){
					 if(isBlack){
						 JOptionPane.showMessageDialog(this, "�׷�ʤ���ڷ�����");
					 }
					 else{
						 JOptionPane.showMessageDialog(this, "�ڷ�ʤ���׷�����");
					 }
				 }
			 }
			 //����
			 if(x>=334&&x<=402&&y>=131&&y<=160){
				 if(isBlack){
					 int  rangZi=JOptionPane.showConfirmDialog(this, "�׷�ȷ����һ����?");
					 if(rangZi==0){
						 rangZi();
						 this.repaint();
					 }
				 }else{
					 int  rangZi=JOptionPane.showConfirmDialog(this, "�ڷ�ȷ����һ����?");
					 if(rangZi==0){
						 rangZi();
						 this.repaint();
					 }
				 }
				 
			 }
			 //����
			 if(x>=335&&x<=400&&y>=350&&y<=384)
			 {
				 int huiqi=JOptionPane.showConfirmDialog(this, "Ҫ������");
				 if(huiqi==0){
					 this.destoryQi();
					 this.repaint();
				 }
			 }
			 if(x>=13&&x<=313&&y>=72&&y<=372){//���̷�Χ��
				 x=(x-13)/20;//ȡ�ӽ��ĵ�
				 y=(y-72)/20;
				 if(allChess[x][y]==0){
					 if(isBlack){//�Ƿ��Ǻڷ�
						 x1=x;
						 y1=y;
						 allChess[x][y]=1;
						 isBlack=false;
						 message="�׷�ʱ��";
					 }else{
						 x1=x;
						 y1=y;
						 allChess[x][y]=2;//���ȥ
						 isBlack=true;
						 message="�ڷ�ʱ��";
					 }
				 }else{
					 JOptionPane.showMessageDialog(this, "��ǰλ���������ӣ�������������");
				 }	
				 this.repaint();//���»���ͼ��	
				 this.lieBlackWin();
				 this.hangWhiteWin();
				 this.xiayouBlackWin();
				 this.shangyouBlackWin();
			 }
			System.out.println("���µ�x:"+x+"----Y:"+y);
			System.out.println("��갴��");
		}else{
			int a=JOptionPane.showConfirmDialog(this, "��Ϸ�ѽ���,�Ƿ����¿�ʼ��");
			if(a==0){
				for(int i=0;i<16;i++){
					for(int j=0;j<16;j++){
						allChess[i][j]=0;
						isOver=false;
						message="�ڷ�ʱ��";
						this.repaint();
					}
				}
				
			}
		}
		
	}
	//����
	public void rangZi(){
		allChess[x1][y1]=0;
	}
	//�������
	public void destoryQi(){
		//��ʱ�ڷ�ʱ��ʱ���壬�ڵ����ǰ��ӣ�������һ�����Ǻڷ����ӣ�����ʾ��ϢҲ����
		if(isBlack){
			isBlack=false;
			message="�׷�ʱ��";
		}else{
			message="�ڷ�ʱ��";
			isBlack=true;
		}
		allChess[x1][y1]=0;
	}
	
	//�ж��Ƿ�������5������
	private void hangWhiteWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		while(color==allChess[x-i][y]){
			i++;
			count=count+1;
		}
		i=1;
		while(color==allChess[x+i][y]){
			i++;
			count=count+1;
		}
		if(count>=5){
			if(!isBlack){
				JOptionPane.showMessageDialog(this, "�ڷ�ʤ");
			}else{
				JOptionPane.showMessageDialog(this, "�׷�ʤ");
			}
			
			isOver=true;
		}
	}
	//�ж��Ƿ�������5������
	private void lieBlackWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		while(color==allChess[x][y+i]){
			i++;
			count=count+1;
		}
		
		i=1;
		while(color==allChess[x][y-i]){
			i++;
			count=count+1;
		}
		if(count>=5){
			if(!isBlack){
				JOptionPane.showMessageDialog(this, "�ڷ�ʤ");
			}else{
				JOptionPane.showMessageDialog(this, "�׷�ʤ");
			}
			
			isOver=true;
		}
	}
	//�ж��Ƿ����º�������5������
	private void xiayouBlackWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		//����
		while(color==allChess[x-i][y+i]){
			i++;
			count=count+1;
		}
		i=1;
		while(color==allChess[x+i][y-i]){
			i++;
			count=count+1;
		}
		if(count>=5){
			if(!isBlack){
				JOptionPane.showMessageDialog(this, "�ڷ�ʤ");
			}else{
				JOptionPane.showMessageDialog(this, "�׷�ʤ");
			}
				
			isOver=true;
		}
	}
	
	//�ж��Ƿ����Ϻ�������5������
	private void shangyouBlackWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		//����
		while(color==allChess[x-i][y-i]){
			i++;
			count=count+1;
		}
		i=1;
		//����
		while(color==allChess[x+i][y+i]){
			i++;
			count=count+1;
		}
		if(count>=5){
			if(!isBlack){
				JOptionPane.showMessageDialog(this, "�ڷ�ʤ");
			}else{
				JOptionPane.showMessageDialog(this, "�׷�ʤ");
			}
			
			isOver=true;
		}
	}
	
  //���ſ��ļ���
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("���ſ�");
	}
      
}
