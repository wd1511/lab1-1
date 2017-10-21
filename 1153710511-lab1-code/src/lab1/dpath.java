//here is the first change for github by huangxu 
//here is the first change with mate
//final 变量名

package lab1;

import java.awt.*;
import javax.swing.*;

public class dpath extends JFrame{
  MyPanel2 mp = null;
  /*public static void main(String[] args){
  dpic d = new dpic();
  }*/
  
  public dpath() {
    ;
  }
  /** .
  */
  
  public void showDirectedGraph(graph picture) {
    mp = new MyPanel2(picture);
    //this.setAlwaysOnTop(true);
    //mp.showDirectedGraph(p);
    this.add(mp);
    this.setSize(1000,800);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setVisible(true);
    //this.setAlwaysOnTop(false);
  }
  /** .
  */
  
  public void close() {
    this.setVisible(false);
  }
}
class MyPanel2 extends JPanel{
  private final graph p1;
  private final int []aaaa; 
  int len = 0;
  
  public MyPanel2(graph picture) {
    p1 = picture;
    final String stmp = JOptionPane.showInputDialog("输入两个单词：\n");
    final String []sstmp = stmp.split(" ");
    int flagw1 = p1.search(sstmp[0]);
    int flagw2 = p1.search(sstmp[1]);
    aaaa = p1.calcShortestPath(sstmp[0],sstmp[1]);
    String ssss = "";
    for (int i = 1;i < aaaa[0];i++) {
      ssss = ssss + aaaa[i] + "->";
    }
    ssss = ssss + aaaa[aaaa[0]];
    if (flagw1 == 0 || flagw2 == 0) {
      JOptionPane.showMessageDialog(null,sstmp[0] + "或" + sstmp[1] + "不存在");
    } else if (aaaa[0] == 0) {
      JOptionPane.showMessageDialog(null,sstmp[0] + "和" + sstmp[1] + "之间不可达");
    } else {
      JOptionPane.showMessageDialog(null,"path is:\n" + ssss + "\nlength is " + 
                                     p1.d[flagw1][flagw2]);
    }
    //this.showDirectedGraph();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    int madd = 1000;
    int nadd = 800;
    int kadd = p1.get_num();
    int []m1 = new int[100];
    int []n1 = new int[100];
    int []m2 = new int[100]; 
    int []n2 = new int[100];
    g.setColor(Color.BLACK);
    //确定位置，绘制图
    for (int i = 0;i < kadd;i++) {
      g.setColor(Color.BLACK);
      //System.out.println(i);
      m1[i] = (int)(madd / 2 + (9 - kadd / 10) * kadd * Math.cos(2 * Math.PI * i / kadd));
      n1[i] = (int)(nadd * 0.45 + (9 - kadd / 10) * kadd * Math.sin(2 * Math.PI * i / kadd));
      m2[i] = (int)(madd / 2 + ((9 - kadd / 10) * kadd - 15 + kadd / 10)
               * Math.cos(2 * Math.PI * i / kadd) + 15 - kadd / 10);
      n2[i] = (int)(nadd * 0.45 + ((9 - kadd / 10) * kadd - 15 + kadd / 10) 
               * Math.sin(2 * Math.PI * i / kadd) + 15 - kadd / 10);
      int flag2 = 1;
      while (flag2 <= aaaa[0]) {
        if (i + 1 == aaaa[flag2]) {
          g.setColor(Color.GREEN);
        }
        flag2++;
      }
      g.drawOval(m1[i],n1[i],30 - kadd / 5,30 - kadd / 5);
      g.setColor(Color.BLACK);
      g.drawString("" + p1.str[i + 1],m1[i] + 6,n1[i]);
    }
    g.setColor(Color.BLUE);
    for (int i1 = 0;i1 < kadd;i1++) {
      for (int j1 = 0;j1 < kadd;j1++) {
        if (p1.a[i1 + 1][j1 + 1] != 0) {
          int flag1 = 1;
          g.setColor(Color.BLUE);
          while (flag1 < aaaa[0]) {
            if (i1 + 1 == aaaa[flag1] && j1 + 1 == aaaa[flag1 + 1]) {
              g.setColor(Color.RED);
            }
            flag1++;
          }
          g.drawLine(m2[i1], n2[i1], m2[j1], n2[j1]);
          g.drawString("" + p1.a[i1 + 1][j1 + 1],(m2[i1] + m2[j1]) / 2,(n2[i1] + n2[j1]) / 2);
          final int t1add = (kadd / 3 + 5) * m2[j1] + m2[i1] + n2[j1] - n2[i1];
          final int t2add = (kadd / 3 + 5) * n2[j1] + n2[i1] - m2[j1] + m2[i1];
          final int t3add = (kadd / 3 + 5) * m2[j1] + m2[i1] - n2[j1] + n2[i1];
          final int t4add = (kadd / 3 + 5) * n2[j1] + n2[i1] + m2[j1] - m2[i1];
          g.drawLine(t1add / (kadd / 3 + 6),t2add / (kadd / 3 + 6),m2[j1],n2[j1]);
          g.drawLine(t3add / (kadd / 3 + 6),t4add / (kadd / 3 + 6),m2[j1],n2[j1]);
        }
      }
    }
  }
}
