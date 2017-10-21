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
  /** .
    * 
  */
  public showpanel(graph padd){
    final spanel s = new spanel(padd);
    this.add(s);
    this.setSize(1000,800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}

class spanel extends JPanel{
  public spanel(graph p){
    final Button button0 = new Button("0退出！");
    final Button button1 = new Button("1展示有向图");
    final Button button2 = new Button("2查询桥接词");
    final Button button3 = new Button("3根据桥接词生成新文本");
    final Button button4 = new Button("4计算两个单词之间的最短路径");
    final Button button5 = new Button("5随机游走");
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
      public void actionPerformed(final ActionEvent e) {
        final dpic dadd = new dpic();
        dadd.showDirectedGraph(p);
        //String choose1=JOptionPane.showInputDialog("是否关闭？");
        //  if(choose1.equals("是")){
        //  d.close();
        //} 
      }
    });
    button2.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent eadd) {
        final String ssadd = JOptionPane.showInputDialog("请输入两个单词：\n");
        final String []stmp = ssadd.split(" ");
        final String bridge = p.queryBridgeWords(stmp[0],stmp[1]);
        if (bridge == null) {
          //System.out.println("没有桥接词！");
          JOptionPane.showMessageDialog(null, "没有桥接词！");
        } else if (bridge.equals(" ")) {
          JOptionPane.showMessageDialog(null,"没有word1或者word2");
          } else {
            //System.out.println(bridge);
            JOptionPane.showMessageDialog(null, "桥接词为：\n" + bridge);
            }
      }
    });
    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        final String inputtext = JOptionPane.showInputDialog("输入文本：\n");
        final String rst = p.generateNewText(inputtext);
        JOptionPane.showMessageDialog(null,rst);
      }
    });
    button4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        final dpath dadd = new dpath();
        dadd.showDirectedGraph(p);
      }
    });
    button5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        final drandom dadd = new drandom();
        dadd.showDirectedGraph(p); 
      }
    });
  }
}