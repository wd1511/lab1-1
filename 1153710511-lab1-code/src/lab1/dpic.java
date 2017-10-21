//here is the second change for github by huangxu
//here is the second change with mate
//变量名 final
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
  MyPanel mp = null;
  
  public dpic() {
    ;
  }
  /** .
   * 
  */
  
  public void showDirectedGraph(graph picture) {
    mp = new MyPanel(picture);
    //this.setAlwaysOnTop(true);
    //mp.showDirectedGraph(p);
    this.add(mp);
    this.setSize(1000,800);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setVisible(true);
    //this.setAlwaysOnTop(false);
  }
  
  public void close() {
    this.setVisible(false);
  }
}

class MyPanel extends JPanel{
  private final graph p1add;
  
  public MyPanel(graph p) {
    p1add = p;
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    final int madd = 1000; 
    final int nadd = 800;
    final int kadd = p1add.get_num();
    int []m1 = new int[100];
    int []n1 = new int[100];
    int []m2 = new int[100];
    int []n2 = new int[100];
    g.setColor(Color.BLACK);
    //确定图形位置，画有向图
    for (int i = 0;i < kadd;i++) {
      //System.out.println(i);
      m1[i] = (int)(madd / 2 + (9 - kadd / 10) * kadd * Math.cos(2 * Math.PI * i / kadd));
      n1[i] = (int)(nadd * 0.45 + (9 - kadd / 10) * kadd * Math.sin(2 * Math.PI * i / kadd));
      m2[i] = (int)(madd / 2 + ((9 - kadd / 10) * kadd - 15 + kadd / 10) * Math.cos(2 * Math.PI * i / kadd) 
               + 15 - kadd / 10);
      n2[i] = (int)(nadd * 0.45 + ((9 - kadd / 10) * kadd - 15 + kadd / 10) * Math.sin(2 * Math.PI * i / kadd) 
               + 15 - kadd / 10);
      g.drawOval(m1[i],n1[i],30 - kadd / 5,30 - kadd / 5);
      g.drawString("" + p1add.str[i + 1],m1[i] + 6,n1[i]);
    }
    g.setColor(Color.BLUE);
    for (int i1 = 0;i1 < kadd;i1++) {
      for (int j1 = 0;j1 < kadd;j1++) {
        if (p1add.a[i1 + 1][j1 + 1] != 0) {
          g.drawLine(m2[i1], n2[i1], m2[j1], n2[j1]);
          g.drawString("" + p1add.a[i1 + 1][j1 + 1],(m2[i1] + m2[j1]) / 2,(n2[i1] + n2[j1]) / 2);
          final int t1 = (kadd / 3 + 5) * m2[j1] + m2[i1] + n2[j1] - n2[i1];
          final int t2 = (kadd / 3 + 5) * n2[j1] + n2[i1] - m2[j1] + m2[i1];
          final int t3 = (kadd / 3 + 5) * m2[j1] + m2[i1] - n2[j1] + n2[i1];
          final int t4 = (kadd/ 3 + 5) * n2[j1] + n2[i1] + m2[j1] - m2[i1];
          g.drawLine(t1 / (kadd / 3 + 6),t2 / (kadd / 3 + 6),m2[j1],n2[j1]);
          g.drawLine(t3 / (kadd / 3 + 6),t4 / (kadd / 3 + 6),m2[j1],n2[j1]);
        }
      }
    }
    BufferedImage targetImg = new BufferedImage(2000,1000,BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = targetImg.createGraphics();
    //g2.setBackground(Color.yellow);
    //g2=(Graphics2D)g;
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, 2000, 1000);
    g2.setColor(Color.BLACK);
    for (int i = 0;i < kadd;i++) {
      //System.out.println(i);
      m1[i] = (int)(madd / 2 + (9 - kadd / 10) * kadd * Math.cos(2 * Math.PI * i / kadd));
      n1[i] = (int)(nadd * 0.45 + (9 - kadd / 10) * kadd * Math.sin(2 * Math.PI * i / kadd));
      m2[i] = (int)(madd / 2 + ((9 - kadd / 10) * kadd - 15 + kadd / 10) * Math.cos(2 * Math.PI * i / kadd) 
               + 15 - kadd / 10);
      n2[i] = (int)(nadd * 0.45 + ((9 - kadd / 10) * kadd - 15 + kadd / 10) * Math.sin(2 * Math.PI * i / kadd)
               + 15 - kadd / 10);
      g2.drawOval(m1[i],n1[i],30 - kadd / 5,30 - kadd / 5);
      g2.drawString("" + p1add.str[i + 1],m1[i] + 6,n1[i]);
    }
    g2.setColor(Color.BLUE);
    for (int i1 = 0;i1 < kadd;i1++) {
      for (int j1 = 0;j1 < kadd;j1++) {
        if (p1add.a[i1 + 1][j1 + 1] != 0) {
          g2.drawLine(m2[i1], n2[i1], m2[j1], n2[j1]);
          g2.drawString("" + p1add.a[i1 + 1][j1 + 1],(m2[i1] + m2[j1]) / 2,(n2[i1] + n2[j1]) / 2);
          final int t1add = (kadd / 3 + 5) * m2[j1] + m2[i1] + n2[j1] - n2[i1];
          final int t2add = (kadd / 3 + 5) * n2[j1] + n2[i1] - m2[j1] + m2[i1];
          final int t3add = (kadd / 3 + 5) * m2[j1] + m2[i1] - n2[j1] + n2[i1];
          final int t4add = (kadd / 3 + 5) * n2[j1] + n2[i1] + m2[j1] - m2[i1];
          g2.drawLine(t1add / (kadd / 3 + 6),t2add / (kadd / 3 + 6),m2[j1],n2[j1]);
          g2.drawLine(t3add / (kadd / 3 + 6),t4add / (kadd / 3 + 6),m2[j1],n2[j1]);
        }
      }
    }
    try {
      ImageIO.write(targetImg, "jpeg", new File("f:\\java\\file\\picture" + ".jpeg"));
    } catch (IOException ee) {
      System.err.println("发生异常：" + ee);
      ee.printStackTrace();
    }
  }
}
