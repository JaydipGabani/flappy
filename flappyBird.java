import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
/**
<applet code="flappyBird" width=600 height=600>
</applet>
*/
public class flappyBird extends Applet implements Runnable,KeyListener
{
	Graphics b;
	Image m,o,k;
	Dimension d;
	int f=0;
	int a[]=new int[20];
	Thread t,u;
	int flag=0;
	//String msg=" ";
	int x1=200,y2=200;
	public void init()
	{
		d=getSize();
		m=getImage(getCodeBase(),"IMG_2510.jpg");
		k=getImage(getCodeBase(),"flappy1.jpg");
		Random r= new Random();
		for(int i=0;i<20;i++)
			a[i]=r.nextInt(200);
		addKeyListener(this);
		o=createImage(d.width,d.height);
		requestFocus();
		//setBackground(Color.gray);
		b=o.getGraphics();
	}
	public void start()
	{
		t=new Thread(this,"thread1");
		u=new Thread(this,"thread2");
		t.start();
		u.start();
	}
	public void run()
	{
		for(;f!=1;)
		{
			try
			{
				repaint();
				if(Thread.currentThread().getName().equals("thread1"))
				{
					Thread.sleep(100);
					x1-=10;
				}
				else
				{
					Thread.sleep(100);
					y2+=10; 
				}
			}catch(InterruptedException e){}
		}
	}
	int y1=20;
	public void restart()
	{
		start();
	}
	public void update(Graphics g)
	{
			b.drawImage(m,0,0,this);
			b.drawImage(k,y1-455,y2-305,this);
			b.fillOval(y1,y2,20,20);
			for(int i=0;i<20;i++)
			{
				//Random r= new Random();
				b.drawRect(x1,0,30,100+a[i]);
				b.drawRect(x1,200+a[i],30,600);
				for(int j=0;j<30;j++)
				{
					if((y2<100+a[i]||y2+20>200+a[i])&&(y1+10>x1&&y1+10<x1+30)) 
					{
						b.drawString("GAME OVER 2",300,300);
						f=1;
						break;
					}
					if(x1==40&&(y2<100+a[i]||y2+20>200+a[i]))
					{/*try{
					this.t.suspend();
					this.u.suspend();
					}
					catch(Exception e){}*/
					b.drawString("GAME OVER 1",300,300);
					f=1;
					break;
					}
					
				}
			
				x1+=150;
			}
			x1-=3000;
			if(x1+400==10)
				x1=200;
			g.drawImage(o,0,0,this);
			repaint();
	}
	public void paint(Graphics g)
	{
		update(g);
	}
	public void keyPressed(KeyEvent ke)
	{
		y2-=40;
		repaint();
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}
}