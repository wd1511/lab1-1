//here is the second change for github by huangxu
<<<<<<< HEAD
//here is the second change for branch b1
=======
//here is the second change in branch c4
>>>>>>> c4
//here is to fixed the conflict2
package lab1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.*;
import java.awt.image.BufferedImage;


public class dpic extends JFrame{
	MyPanel mp=null;
	 
	
	public dpic() {
		 ;
	}
	public void showDirectedGraph(graph p){
		mp = new MyPanel(p);
		//this.setAlwaysOnTop(true);
		//mp.showDirectedGraph(p);
		this.add(mp);
		this.setSize(1000,800);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		//this.setAlwaysOnTop(false);
	}
	public void close(){
		this.setVisible(false);
	}
}

class MyPanel extends JPanel{
	private graph p1;
	public MyPanel(graph p) {
		p1=p;
		 
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		int m=1000;
		int n=800;
		int k=p1.get_num();
		
		int []m1 = new int[100];
		int []n1 = new int[100];
		int []m2 = new int[100];
		int []n2 = new int[100];
		g.setColor(Color.BLACK);
		
		//确定图形位置，画有向图
		for(int i=0;i<k;i++)
		{
			//System.out.println(i);
			m1[i]= (int)(m/2+(9-k/10)*k*Math.cos(2*Math.PI*i/k));
			n1[i]= (int)(n*0.45+(9-k/10)*k*Math.sin(2*Math.PI*i/k));
			m2[i]= (int)(m/2+((9-k/10)*k-15+k/10)*Math.cos(2*Math.PI*i/k)+15-k/10);
			n2[i]= (int)(n*0.45+((9-k/10)*k-15+k/10)*Math.sin(2*Math.PI*i/k)+15-k/10);
			g.drawOval(m1[i],n1[i],30-k/5,30-k/5);
			g.drawString(""+p1.str[i+1],m1[i]+6,n1[i]);
		}
		
		g.setColor(Color.BLUE);
		for(int i1=0;i1<k;i1++)
		{
			for(int j1=0;j1<k;j1++)
			{
				if(p1.a[i1+1][j1+1]!=0)
				{
					g.drawLine(m2[i1], n2[i1], m2[j1], n2[j1]);
					g.drawString(""+p1.a[i1+1][j1+1],(m2[i1]+m2[j1])/2,(n2[i1]+n2[j1])/2);
					int t1=(k/3+5)*m2[j1]+m2[i1]+n2[j1]-n2[i1];
					int t2=(k/3+5)*n2[j1]+n2[i1]-m2[j1]+m2[i1];
					int t3=(k/3+5)*m2[j1]+m2[i1]-n2[j1]+n2[i1];
					int t4=(k/3+5)*n2[j1]+n2[i1]+m2[j1]-m2[i1];
					g.drawLine(t1/(k/3+6),t2/(k/3+6),m2[j1],n2[j1]);
					g.drawLine(t3/(k/3+6),t4/(k/3+6),m2[j1],n2[j1]);
				}
			}
		}
		BufferedImage targetImg = new BufferedImage(2000,1000,BufferedImage.TYPE_INT_RGB);
		 Graphics2D g2=targetImg.createGraphics();
		//g2.setBackground(Color.yellow);
		//g2=(Graphics2D)g;
		 g2.setColor(Color.WHITE);
		 g2.fillRect(0, 0, 2000, 1000);
		 g2.setColor(Color.BLACK);
			for(int i=0;i<k;i++)
			{
				//System.out.println(i);
				m1[i]= (int)(m/2+(9-k/10)*k*Math.cos(2*Math.PI*i/k));
				n1[i]= (int)(n*0.45+(9-k/10)*k*Math.sin(2*Math.PI*i/k));
				m2[i]= (int)(m/2+((9-k/10)*k-15+k/10)*Math.cos(2*Math.PI*i/k)+15-k/10);
				n2[i]= (int)(n*0.45+((9-k/10)*k-15+k/10)*Math.sin(2*Math.PI*i/k)+15-k/10);
				g2.drawOval(m1[i],n1[i],30-k/5,30-k/5);
				g2.drawString(""+p1.str[i+1],m1[i]+6,n1[i]);
			}
			
			g2.setColor(Color.BLUE);
			for(int i1=0;i1<k;i1++)
			{
				for(int j1=0;j1<k;j1++)
				{
					if(p1.a[i1+1][j1+1]!=0)
					{
						g2.drawLine(m2[i1], n2[i1], m2[j1], n2[j1]);
						g2.drawString(""+p1.a[i1+1][j1+1],(m2[i1]+m2[j1])/2,(n2[i1]+n2[j1])/2);
						int t1=(k/3+5)*m2[j1]+m2[i1]+n2[j1]-n2[i1];
						int t2=(k/3+5)*n2[j1]+n2[i1]-m2[j1]+m2[i1];
						int t3=(k/3+5)*m2[j1]+m2[i1]-n2[j1]+n2[i1];
						int t4=(k/3+5)*n2[j1]+n2[i1]+m2[j1]-m2[i1];
						g2.drawLine(t1/(k/3+6),t2/(k/3+6),m2[j1],n2[j1]);
						g2.drawLine(t3/(k/3+6),t4/(k/3+6),m2[j1],n2[j1]);
					}
				}
			}
		try{
		ImageIO.write(targetImg, "jpeg", new File("f:\\java\\file\\picture"+".jpeg"));
		}
		catch(IOException ee)
		{
			System.err.println("发生异常："+ee);
			ee.printStackTrace();
		}
	}
}
