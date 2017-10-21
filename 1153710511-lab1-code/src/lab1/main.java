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
    final  String filename = JOptionPane.showInputDialog("input the file location:");
    String sadd = "";
    int i = 0;
    try {
      final FileInputStream fadd = new FileInputStream(filename);
      int badd = fadd.read();
      int flag = 0;
      for (i = 0;badd != -1;i++) {
        final char tmp = (char)badd;
        if ((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')) {
          sadd = sadd + tmp;
          flag = 0;
        } else if ((tmp == ' ' || tmp == '\r' || tmp == '\n' 
            || tmp == ',' || tmp == '.' || tmp == '?' 
            || tmp == '!' || tmp == '\'' || tmp == '\"' || tmp == ';' || tmp == ':') 
            && (flag == 0)) {
          sadd = sadd + " ";
          flag = 1;
        } else {
          sadd = sadd;
        }
        badd = fadd.read();
      }
      fadd.close();
    } catch (IOException e) { 
      System.err.println("发生异常：" + e);
      e.printStackTrace();
    }
    sadd = sadd.toLowerCase();
    System.out.println(sadd);
    final String []a = sadd.split(" ");
    //for(int j=0;j<a.length;j++)System.out.println(a[j]);
    //向图中添加节点
    final graph gadd = new graph();
    for (int j = 0;j < a.length;j++) {
      gadd.add_node(a[j]);
    }
    for (int j = 0;j < a.length - 1;j++) {
      gadd.add_edge(a[j],a[j + 1]);
    }
    //把邻接矩阵写入文件
    System.out.println(gadd.get_num());
    String matrix = "";
    for (int k = 1;k <= gadd.get_num();k++) {
      for (int j = 1;j <= gadd.get_num();j++) {
        matrix = matrix + gadd.a[k][j] + " ";
      }
      matrix = matrix + "\r\n";
    }
    final byte []badd = matrix.getBytes();
    try {
      final FileOutputStream out = new FileOutputStream("f:\\java\\file\\matrix.txt");
      out.write(badd);
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
    showpanel p = new showpanel(gadd);
  }

}
