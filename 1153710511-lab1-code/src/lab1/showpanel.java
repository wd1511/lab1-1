package lab1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Math.PI;
import static java.lang.Math.sin;
import static java.lang.StrictMath.cos;

public class showpanel extends JFrame{
	
	public showpanel(graph p){
		spanel s=new spanel(p);
		this.add(s);
		this.setSize(1000,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

class spanel extends JPanel{
	public spanel(graph p){
		Button button0=new Button("0退出！");
		Button button1=new Button("1展示有向图");
		Button button2=new Button("2查询桥接词");
		Button button3=new Button("3根据桥接词生成新文本");
		Button button4=new Button("4计算两个单词之间的最短路径");
		Button button5=new Button("5随机游走");
		this.add(button0);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		
		//dpic d=new dpic();
		//this.add(d);
		//d.setVisible(false);
		
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
		});
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dpic d=new dpic();
				 d.showDirectedGraph(p);
				 //String choose1=JOptionPane.showInputDialog("是否关闭？");
	    			//if(choose1.equals("是")){
	    				//d.close();
	    			//}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ss=JOptionPane.showInputDialog("请输入两个单词：\n");
    			String []stmp=ss.split(" ");
    			String bridge=p.queryBridgeWords(stmp[0],stmp[1]);
    			if(bridge==null){
    				//System.out.println("没有桥接词！");
    				JOptionPane.showMessageDialog(null, "没有桥接词！");
    			}
    			else if(bridge==" "){
    				JOptionPane.showMessageDialog(null,"没有word1或者word2");
    			}
    			else{
    				//System.out.println(bridge);
    				JOptionPane.showMessageDialog(null, "桥接词为：\n"+bridge);
    			}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_text=JOptionPane.showInputDialog("输入文本：\n");
    			String rst=p.generateNewText(input_text);
    			JOptionPane.showMessageDialog(null,rst);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dpath d=new dpath();
    			d.showDirectedGraph(p);
			}
		});
		
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drandom d = new drandom();
    			d.showDirectedGraph(p); 
			}
		});
	}
}