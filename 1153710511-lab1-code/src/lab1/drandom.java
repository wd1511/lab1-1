package lab1;

/*走过的顶点设为绿色，
 * 未走过的顶点设为黑色。
 * 走过的边设为红色
 * 未走过的边设为蓝色
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;
public class drandom extends JFrame{
	MyPanel1 mp=null;
	
	public drandom() {

		//this.showDirectedGraph(p);
	}
	public void showDirectedGraph(graph p){
		mp = new MyPanel1(p);
		//mp.showDirectedGraph(p);
		this.add(mp);
		this.setSize(1000,800);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
	}
	public void close(){
		this.setVisible(false);
	}
	public String buttonflag(){
		return mp.buttonflag;
	}
}

class MyPanel1 extends JPanel{
	private graph p1;
	private Button button2;
	private Button button3;
	private int[]ab;
	private int numm;
	String path="";
	public String buttonflag;
	public MyPanel1(graph p){
		p1=p;
		numm=1;
		buttonflag = "5";
		button2 = new Button("next");
		this.add(button2);
		
		button3 = new Button("end");
		this.add(button3);
		
		ab=p1.randomwalk();
			
			
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					//repaint();
					//g.setColor(Color.RED);
				if(numm<ab[0]){
						//g.drawLine(m2[ab[numm]], n2[ab[numm]], m2[ab[numm+1]], n2[ab[numm+1]]);
					repaint();
					if(path=="")
						path=path+ab[1]+"."+p1.str[ab[1]]+"->"+ab[2]+"."+p1.str[ab[2]];
					else
						path=path+"->"+ab[numm+1]+"."+p1.str[ab[numm+1]];
					String s=path;
				    byte[] b=s.getBytes();
				    try
				    {
				       FileOutputStream out=new FileOutputStream("f:\\java\\file\\routine.txt");
				       out.write(b);
				       out.flush();
				       out.close();
				    }
				    catch(IOException ee)
				    {
				      System.err.println("发生异常："+ee);
				      ee.printStackTrace();
				    }
					numm++;
				}
				else
				{
					//String path="";
					/*for(int ii=1;ii<ab[0];ii++)
					{
						path=path+ab[ii]+"."+p1.str[ab[ii]]+"->";
					}
					path=path+ab[ab[0]]+"."+p1.str[ab[ab[0]]];*/
					
					
					
					JOptionPane.showMessageDialog(null,"end!\npath is:\n"+path);
				}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String choose1=JOptionPane.showInputDialog("是否关闭？");
    			//if(choose1.equals("是")){
    				
    				//close();
				//ff=1;
				//JOptionPane.showMessageDialog(null,"end!\npath is:\n"+path);
					 setVisible(false);
					 //join();
				//buttonflag="1";
				//dispose();
				//System.exit(0);
    				//buttonflag = JOptionPane.showInputDialog("请选择功能：\n0.退出\n1.展示有向图\n2.查询桥接词\n3.根据桥接词生成新文本\n4.计算两个单词之间的最短路径\n5.随机游走\n");
    			//}
			}
		});
	}
		
		//repaint();
		//this.showDirectedGraph();
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		
		
		//JOptionPane.showMessageDialog(null, "test");
		
		int m=1000;
		int n=800;
		//int num=1;
		int k=p1.get_num();
		
		//int []ab=new int[100];
		
		
		int []m1 = new int[100];
		int []n1 = new int[100];
		int []m2 = new int[100];
		int []n2 = new int[100];
		
		//根据预先的计算的数组，开始随机游走
		for(int i=0;i<k;i++)
		{
			g.setColor(Color.BLACK);
			//System.out.println(i);
			m1[i]= (int)(m/2+(9-k/10)*k*Math.cos(2*Math.PI*i/k));
			n1[i]= (int)(n*0.45+(9-k/10)*k*Math.sin(2*Math.PI*i/k));
			m2[i]= (int)(m/2+((9-k/10)*k-15+k/10)*Math.cos(2*Math.PI*i/k)+15-k/10);
			n2[i]= (int)(n*0.45+((9-k/10)*k-15+k/10)*Math.sin(2*Math.PI*i/k)+15-k/10);
			int flag2=1;
			while(flag2<=numm)
			{
				if(i+1==ab[flag2])
				{
					g.setColor(Color.GREEN);
				}
				flag2++;
			}
			g.drawOval(m1[i],n1[i],30-k/5,30-k/5);
			g.setColor(Color.BLACK);
			g.drawString(""+p1.str[i+1],m1[i]+6,n1[i]);
		}
		
		for(int i1=0;i1<k;i1++)
		{
			for(int j1=0;j1<k;j1++)
			{
				if(p1.a[i1+1][j1+1]!=0)
				{
					int flag1=1;
					g.setColor(Color.BLUE);
					while(flag1<numm)
					{
						if(i1+1==ab[flag1] && j1+1==ab[flag1+1])
						{
							g.setColor(Color.RED);
						}
						flag1++;
					}
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
	}
}

