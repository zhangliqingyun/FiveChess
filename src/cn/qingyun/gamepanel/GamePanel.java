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
		 this.setTitle("立增版五子棋");
		 this.setSize(420,420);
		 this.setResizable(false);
		 int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		 int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		 this.setLocation((width-300)/2,(height-300)/2);
		 this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		 this.addMouseListener(this);
		 this.setVisible(true);
	 }
	 //保存鼠标点击的当前坐标
	 int x=0,y=0;
	 //保存所有下过的棋子，1表示黑子，2表示白子，0表示没有棋子
	 int[][] allChess=new int[16][16];
	 boolean isOver=false;
	 //定义一个标记用于记录是否是黑方
	 boolean isBlack=true;
	 String blackTime="无限制";
	 String whiteTime="无限制";
	 //提示信息
	 String message="黑方时间";
	 //记录当前保存进二维数组的棋子的x和y
	 int x1,y1;
    //重载显示
		public void paint(Graphics g) {
			 BufferedImage image=null;
			 try {
				  image= ImageIO.read(getClass().getClassLoader().getResource("./cn/qingyun/img/fivechess-backage.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(image,0,20,this);
			//双缓冲技术防止屏幕闪烁
			BufferedImage bi=new BufferedImage(420,420,BufferedImage.TYPE_INT_ARGB);
			Graphics g2=bi.createGraphics();
			g2.setFont(new Font("黑体",25,25));
			g2.setColor(Color.black);
			g2.drawString("提示信息:",6,60);
			g2.drawString(message, 128, 60);
			g2.drawRect(14, 380,130, 30);
			g2.drawRect(180,380,130,30);
			g2.setFont(new Font("黑体",13,13));
			g2.drawString("黑方时间："+blackTime, 18, 400);
			g2.drawString("白方时间："+whiteTime, 183, 400);
			g2.setFont(new Font("宋体",35,35));
			g2.setColor(Color.blue);
		    g2.drawString("悔棋", 330, 380);
		    g2.drawString("新局", 330, 100); 
		    g2.drawString("关于", 330, 210); 
		    g2.drawString("退出", 330, 325);    
		    g2.drawString("认输", 330, 265);        
		    g2.drawString("让子", 330, 155);       
		    g2.setColor(Color.black);
			for(int i=0;i<16;i++){//画出棋盘格子线
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
			
			//循环显示输出所有的棋子
			for(int i=0;i<16;i++){
				for(int j=0;j<16;j++){
						if(allChess[i][j]==1){//黑子
							int tempX=i*20+13;
							int tempY=j*20+72;
							g2.fillOval(tempX-7, tempY-7, 14, 14);
						}
						else if(allChess[i][j]==2){//白子
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


	//鼠标点击的监听
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("鼠标点击事件");
	}
  //鼠标进入的监听
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("鼠标进入");
	}
    //鼠标离开的监听
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("鼠标离开");
	}
  //鼠标按下的监听
	@Override
	public void mousePressed(MouseEvent e) {
		if(!isOver){
			 x=e.getX();
			 y=e.getY();
			 //开始一局新游戏
			 if(x>=332&&x<=395&&y>=72&&y<=107){
				 int newGame=JOptionPane.showConfirmDialog(this, "开始一局新游戏？");
				 if(newGame==0){
					 for(int i=0;i<16;i++){
							for(int j=0;j<16;j++){
								allChess[i][j]=0;
								isOver=false;
								isBlack=true;
								message="黑方时间";
								this.repaint();
							}
					 }
				 }
			 }
			 //关于
			 if(x>=334&&x<=400&&y>=184&&y<=215){
				 JOptionPane.showMessageDialog(this, "这是一个五子棋，五个连成一起就算赢，立增版五子棋，详询请咨询立增");
			 }
			 //退出
			 if(x>=336&&x<=398&&y>=304&&y<=330){
				 int exit=JOptionPane.showConfirmDialog(this,"确定退出吗？");
				 if(exit==0){
					 System.exit(0);
				 }
			 }
			//认输
			 if(x>=337&&x<=398&&y>=240&&y<=269){
				 int loseWin=JOptionPane.showConfirmDialog(this,"确定认输吗？");
				 if(loseWin==0){
					 if(isBlack){
						 JOptionPane.showMessageDialog(this, "白方胜！黑方认输");
					 }
					 else{
						 JOptionPane.showMessageDialog(this, "黑方胜！白方认输");
					 }
				 }
			 }
			 //设置
			 if(x>=334&&x<=402&&y>=131&&y<=160){
				 if(isBlack){
					 int  rangZi=JOptionPane.showConfirmDialog(this, "白方确定让一个子?");
					 if(rangZi==0){
						 rangZi();
						 this.repaint();
					 }
				 }else{
					 int  rangZi=JOptionPane.showConfirmDialog(this, "黑方确定让一个子?");
					 if(rangZi==0){
						 rangZi();
						 this.repaint();
					 }
				 }
				 
			 }
			 //悔棋
			 if(x>=335&&x<=400&&y>=350&&y<=384)
			 {
				 int huiqi=JOptionPane.showConfirmDialog(this, "要悔棋吗？");
				 if(huiqi==0){
					 this.destoryQi();
					 this.repaint();
				 }
			 }
			 if(x>=13&&x<=313&&y>=72&&y<=372){//棋盘范围内
				 x=(x-13)/20;//取接近的点
				 y=(y-72)/20;
				 if(allChess[x][y]==0){
					 if(isBlack){//是否是黑方
						 x1=x;
						 y1=y;
						 allChess[x][y]=1;
						 isBlack=false;
						 message="白方时间";
					 }else{
						 x1=x;
						 y1=y;
						 allChess[x][y]=2;//存进去
						 isBlack=true;
						 message="黑方时间";
					 }
				 }else{
					 JOptionPane.showMessageDialog(this, "当前位置已有棋子，请重新下棋子");
				 }	
				 this.repaint();//重新绘制图形	
				 this.lieBlackWin();
				 this.hangWhiteWin();
				 this.xiayouBlackWin();
				 this.shangyouBlackWin();
			 }
			System.out.println("按下的x:"+x+"----Y:"+y);
			System.out.println("鼠标按下");
		}else{
			int a=JOptionPane.showConfirmDialog(this, "游戏已结束,是否重新开始？");
			if(a==0){
				for(int i=0;i<16;i++){
					for(int j=0;j<16;j++){
						allChess[i][j]=0;
						isOver=false;
						message="黑方时间";
						this.repaint();
					}
				}
				
			}
		}
		
	}
	//让子
	public void rangZi(){
		allChess[x1][y1]=0;
	}
	//悔棋操作
	public void destoryQi(){
		//当时黑方时间时悔棋，悔的棋是白子，所以下一步还是黑方下子，把提示信息也更改
		if(isBlack){
			isBlack=false;
			message="白方时间";
		}else{
			message="黑方时间";
			isBlack=true;
		}
		allChess[x1][y1]=0;
	}
	
	//判断是否列向有5个棋子
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
				JOptionPane.showMessageDialog(this, "黑方胜");
			}else{
				JOptionPane.showMessageDialog(this, "白方胜");
			}
			
			isOver=true;
		}
	}
	//判断是否列向有5个棋子
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
				JOptionPane.showMessageDialog(this, "黑方胜");
			}else{
				JOptionPane.showMessageDialog(this, "白方胜");
			}
			
			isOver=true;
		}
	}
	//判断是否左下和右上有5个棋子
	private void xiayouBlackWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		//左下
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
				JOptionPane.showMessageDialog(this, "黑方胜");
			}else{
				JOptionPane.showMessageDialog(this, "白方胜");
			}
				
			isOver=true;
		}
	}
	
	//判断是否左上和右下有5个棋子
	private void shangyouBlackWin(){
		int count=1;
		int color=allChess[x][y];
		int i=1;
		//左上
		while(color==allChess[x-i][y-i]){
			i++;
			count=count+1;
		}
		i=1;
		//右下
		while(color==allChess[x+i][y+i]){
			i++;
			count=count+1;
		}
		if(count>=5){
			if(!isBlack){
				JOptionPane.showMessageDialog(this, "黑方胜");
			}else{
				JOptionPane.showMessageDialog(this, "白方胜");
			}
			
			isOver=true;
		}
	}
	
  //鼠标放开的监听
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("鼠标放开");
	}
      
}
