//多了equals
package lab1;

public class graph {
  private int num;
  //图中顶点个数
  public int [][]a = new int[100][100];
  //邻接矩阵
  public String []str = new String[100];
  //顶点的内容
  //public int [][]graphflag=new int[100][100];//路径
  public int [][]d = new int[100][100];
  //distance
  private int [][]p = new int[100][100];
  //path
  //构造函数
  
  public graph() {
    num = 0;
  }

  /** .
   * 添加节点
  */
  
  public void add_node(String sadd) {
    int flag = 0;
    flag = search(sadd);
    if (flag == 0) {
      num++;
      str[num] = sadd;
    }
  }
  /** .
   * 添加边
   */ 
  
  public void add_edge(String s1add,String s2add) {
    final int s1index = search(s1add);
    final int s2index = search(s2add);
    a[s1index][s2index]++;
  }
  /** .
   * 获得顶点个数
   */ 

  public int get_num() {
    return num;
  }
  /** .
   * 打印顶点
   */ 

  public void print_node() {
    for (int i = 1;i <= num;i++) {
      System.out.println(str[i]);
    }
  }
  /** .
   * 打印边
   */ 
  
  public void print_edge() {
    for (int i = 1;i <= num;i++) {
      for (int j = 1;j <= num;j++) {
        System.out.print(a[i][j] + " ");
        System.out.println();
      }
    }
  }
  /** .
   * 查询顶点
   */ 
  
  public int search(String sadd) {
    int iadd;
    for (iadd = 1;iadd <= num;iadd++) {
      if (sadd.equals(str[iadd])) {
        return iadd;
      }
    }
    return 0;
  }
  /** .
   * 寻找w1和w2之间的桥接词
   */ 
  
  public String queryBridgeWords(String w1add,String w2add) {
    final int w1index = search(w1add);
    final int w2index = search(w2add);
    if (w1index == 0 || w2index == 0) {
      return " ";
    }
    String bridge = "";
    if (w1index != 0 && w2index != 0) {
      for (int j = 1;j <= num;j++) {
        if (a[w1index][j] != 0 && a[j][w2index] != 0) {
          bridge = bridge + " " + str[j];
        }
      }
    }
    if (bridge.equals("")) {
      return null;
    } else {
      return bridge;
    }
  }
  /** .
   * floyd算法，求最短路径
   */ 

  public void floyd() {
    for (int i = 1;i <= num;i++) {
      for (int j = 1;j <= num;j++) {
        p[i][j] = j;
        if (a[i][j] == 0) {
          d[i][j] = 100000;
        }  else {
          d[i][j] = a[i][j];
        }
      }
    }
    for (int k = 1;k <= num;k++) {
      for (int i = 1;i <= num;i++) {
        for (int j = 1;j <= num;j++) {
          if (d[i][k] + d[k][j] < d[i][j]) {
            d[i][j] = d[i][k] + d[k][j];
            p[i][j] = p[i][k];
          }
        }
      }
    }
  }
  /** .
   * 输出i和j间的最短路径
   */
  
  public void distance(int iadd,int jadd) {
    System.out.println(d[iadd][jadd]);
  }
  /** .
   * 打印路径
   */

  public void path(int iadd,int jadd) {
    while (iadd != jadd) {
      System.out.print(iadd + "->");
      iadd = p[iadd][jadd];
    }
    System.out.println(jadd);
  }
  /** .
   * 生成桥接文本
   */

  public String generateNewText(String inputText) {
    final String []bshuzu = inputText.split(" ");
    String bbadd = "";
    for (int ii = 0;ii < bshuzu.length - 1;ii++) {
      final String badd = this.queryBridgeWords(bshuzu[ii], bshuzu[ii + 1]);
      //String []ba0=b.split(" ");
      String padd;
      if (badd == null || badd == " ") {
        padd = "";
      } else {
        String []ba0 = badd.split(" ");
        if (ba0.length == 1) {
          padd = ba0[0];
        } else {
          padd = ba0[1];
        }
      }
      //System.out.println("fsadfasf "+ba0[1]);
      if (padd != null) {
        if (bbadd == "") {
          bbadd = bbadd + bshuzu[ii] + " " + p;
        } else {
          bbadd = bbadd + " " + bshuzu[ii] + " " + p;
        }
      } else {
        if (bbadd == "") {
          bbadd = bbadd + bshuzu[ii];
        } else {
          bbadd = bbadd + " " + bshuzu[ii];
        }
      }
    }
    if (bbadd == "") {
      bbadd = bbadd + bshuzu[bshuzu.length - 1];
    } else {
      bbadd = bbadd + " " + bshuzu[bshuzu.length - 1];
    }
    return bbadd;
  }
  /** .
   * 计算最短路径
   */

  public int[] calcShortestPath(final String word1, final String word2) {
    String sadd = "";
    int []aaa = new int[100];
    this.floyd();
    int index1 = search(word1);
    final int index2 = search(word2);
    if (d[index1][index2] >= 100000) {
      return aaa;
    }
    while (index1 != index2) {
      //System.out.print(i+"->");
      sadd = sadd + " " + str[index1] + "->";
      aaa[0]++;
      aaa[aaa[0]] = index1;
      index1 = p[index1][index2];
    }
    sadd = sadd + str[index2];
    aaa[0]++;
    aaa[aaa[0]] = index2;
    System.out.println(sadd);
    return aaa;
  }
  /** .
   *  随机游走
   */
  
  public int[] randomwalk() {
    int []aaa = new int[100];
    String walk = "";
    int xadd = 1 + (int)(Math.random() * num);
    aaa[1] = xadd;
    int shumu = 1;
    walk = str[xadd];
    int endflag = 0;
    int findflag = 0;
    int j = 0;
    while (endflag == 0) {
      findflag = 0; 
      //System.out.print(x);
      int xiangling = 0;
      int []aaadd = new int[100];
      for (int i = 1;i <= num;i++) {
        if (a[xadd][i] > 0) {
          xiangling++;
          aaadd[xiangling] = i;
        }
      }
      if (xiangling == 0) {
        break;
      }
      j = 1 + (int)(Math.random() * xiangling);
      walk = walk + " " + str[aaadd[j]];
      aaa[++shumu] = aaadd[j];
      //System.out.println(j);
      String []chongfu;
      chongfu = walk.split(" ");
      for (int i = 0;i < chongfu.length - 3;i++) {
        if (chongfu[i].equals(chongfu[chongfu.length - 2]) 
            && chongfu[i + 1].equals(chongfu[chongfu.length - 1])) {
          endflag = 1;
        }
      } 
      xadd = aaadd[j];
    }
    System.out.println(walk);
    aaa[0] = shumu;
    return aaa;
  }
}
