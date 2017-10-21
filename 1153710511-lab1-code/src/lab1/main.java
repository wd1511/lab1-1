package lab1;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class main {
  /** .
    * 
  */
  public static void main(String[] arg) throws InterruptedException {
    //读入文件
    String filename = JOptionPane.showInputDialog("input the file location:");
    String s = "";
    int i = 0;
    try {
      FileInputStream f = new FileInputStream(filename);
      int b = f.read();
      int flag = 0;
      for (i = 0;b != -1;i++) {
        char tmp = (char)b;
        if ((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')) {
          s = s + tmp;
          flag = 0;
        } else if ((tmp == ' ' || tmp == '\r' || tmp == '\n' 
            || tmp == ',' || tmp == '.' || tmp == '?' 
            || tmp == '!' || tmp == '\'' || tmp == '\"' || tmp == ';' || tmp == ':') 
            && (flag == 0)) {
          s = s + " ";
          flag = 1;
        } else {
          s = s;
        }
        b = f.read();
      }
      f.close();
    } catch (IOException e) { 
      System.err.println("发生异常：" + e);
      e.printStackTrace();
    }
    s = s.toLowerCase();
    System.out.println(s);
    String []a = s.split(" ");
    //for(int j=0;j<a.length;j++)System.out.println(a[j]);
    //向图中添加节点
    graph g = new graph();
    for (int j = 0;j < a.length;j++) {
      g.add_node(a[j]);
    }
    for (int j = 0;j < a.length - 1;j++) {
      g.add_edge(a[j],a[j + 1]);
    }
    //把邻接矩阵写入文件
    System.out.println(g.get_num());
    String matrix = "";
    for (int k = 1;k <= g.get_num();k++) {
      for (int j = 1;j <= g.get_num();j++) {
        matrix = matrix + g.a[k][j] + " ";
      }
      matrix = matrix + "\r\n";
    }
    byte []b = matrix.getBytes();
    try {
      FileOutputStream out = new FileOutputStream("f:\\java\\file\\matrix.txt");
      out.write(b);
      out.flush();
      out.close();
    } catch (IOException ee) {
      System.err.println("发生异常：" + ee);
      ee.printStackTrace();
    }
    //g.add_node("jhadsfjkh");
    //g.print_node();
    //g.print_edge();
    //显示主界面
    showpanel p = new showpanel(g);
  }

}
