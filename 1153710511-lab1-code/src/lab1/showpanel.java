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
		Button button0=new Button("0�˳���");
		Button button1=new Button("1չʾ����ͼ");
		Button button2=new Button("2��ѯ�ŽӴ�");
		Button button3=new Button("3�����ŽӴ��������ı�");
		Button button4=new Button("4������������֮������·��");
		Button button5=new Button("5�������");
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
				 //String choose1=JOptionPane.showInputDialog("�Ƿ�رգ�");
	    			//if(choose1.equals("��")){
	    				//d.close();
	    			//}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ss=JOptionPane.showInputDialog("�������������ʣ�\n");
    			String []stmp=ss.split(" ");
    			String bridge=p.queryBridgeWords(stmp[0],stmp[1]);
    			if(bridge==null){
    				//System.out.println("û���ŽӴʣ�");
    				JOptionPane.showMessageDialog(null, "û���ŽӴʣ�");
    			}
    			else if(bridge==" "){
    				JOptionPane.showMessageDialog(null,"û��word1����word2");
    			}
    			else{
    				//System.out.println(bridge);
    				JOptionPane.showMessageDialog(null, "�ŽӴ�Ϊ��\n"+bridge);
    			}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_text=JOptionPane.showInputDialog("�����ı���\n");
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