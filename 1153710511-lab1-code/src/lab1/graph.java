package lab1;
import java.lang.*;

public class graph {
	private int num;	//图中顶点个数
	public int [][]a=new int[100][100];	//邻接矩阵
	public String []str=new String[100];	//顶点的内容
	//public int [][]graphflag=new int[100][100];//路径
	public int [][]d=new int[100][100];	//distance
	private int [][]p=new int[100][100];	//path
	
	//构造函数
	public graph(){
		num=0;
	}
	
	//添加节点
	public void add_node(String s){
		int flag=0;
		flag=search(s);
		if(flag==0){
			num++;
			str[num]=s;
		}
	}
	
	//添加边
	public void add_edge(String s1,String s2){
		int s1_index=search(s1);
		int s2_index=search(s2);
		a[s1_index][s2_index]++;
	}
	
	//获得顶点个数
	public int get_num(){
		return num;
	}
	
	//打印顶点
	public void print_node(){
		for(int i=1;i<=num;i++){
			System.out.println(str[i]);
		}
	}
	
	//打印边
	public void print_edge(){
		for(int i=1;i<=num;i++){
			for(int j=1;j<=num;j++)System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}
	
	//查询顶点
	public int search(String s){
		int i;
		for(i=1;i<=num;i++){
			if(s.equals(str[i]))return i;
		}
		return 0;
	}
	
	//寻找w1和w2之间的桥接词
	public String queryBridgeWords(String w1,String w2){
		int w1_index=search(w1);
		int w2_index=search(w2);
		if(w1_index==0 || w2_index==0)return " ";
		String bridge="";
		if(w1_index!=0 && w2_index!=0){
			for(int j=1;j<=num;j++){
				if(a[w1_index][j]!=0 && a[j][w2_index]!=0)bridge=bridge+" "+str[j];
			}
		}
		if(bridge=="")return null;
		else return bridge;
	}
	
	//floyd算法，求最短路径
	public void floyd(){
		for(int i=1;i<=num;i++)
			for(int j=1;j<=num;j++){
			p[i][j]=j;
			if(a[i][j]==0)d[i][j]=100000;
			else d[i][j]=a[i][j];
		}
		
		for(int k=1;k<=num;k++){
			for(int i=1;i<=num;i++){
				for(int j=1;j<=num;j++){
					if(d[i][k]+d[k][j]<d[i][j]){
						d[i][j]=d[i][k]+d[k][j];
						p[i][j]=p[i][k];
	                   }
				}
			}
		}
	}
	
	//输出i和j间的最短路径
	public void distance(int i,int j){
		System.out.println(d[i][j]);
	}
	
	//打印路径
	public void path(int i,int j){
		while(i!=j){
			System.out.print(i+"->");
			i=p[i][j];
		}
		System.out.println(j);
	}
	
	//生成桥接文本
	public String generateNewText(String inputText){
		String []bshuzu=inputText.split(" ");
		
		String bb="";
    	for(int ii=0;ii<bshuzu.length-1;ii++){
    		String b=this.queryBridgeWords(bshuzu[ii], bshuzu[ii+1]);
    		//String []ba0=b.split(" ");
    		String p;
    		if(b==null||b==" ")
    		{
    			p="";
    		}
    		else
    		{
    			String []ba0=b.split(" ");
    			if(ba0.length==1)
        			p=ba0[0];
        		else
        			p=ba0[1];
    		}
    		//System.out.println("fsadfasf "+ba0[1]);
    		if(p!=null){
    			if(bb=="")	bb=bb+bshuzu[ii]+" "+p;
    			else	bb=bb+" "+bshuzu[ii]+" "+p;
    		}
    		else
    		{
    			if(bb=="") bb=bb+bshuzu[ii];
    			else bb=bb+" "+bshuzu[ii];
    		}
    	}
    	if(bb=="") bb=bb+bshuzu[bshuzu.length-1];
		else bb=bb+" "+bshuzu[bshuzu.length-1];
    	return bb;
	}
	
	//计算最短路径
	public int[] calcShortestPath(String word1, String word2){
		String s="";
		int aaa[]=new int[100];
		this.floyd();
		int index1=search(word1);
		int index2=search(word2);
		if(d[index1][index2]>=100000)return aaa;
		while(index1!=index2){
			//System.out.print(i+"->");
			s=s+" "+str[index1]+"->";
			aaa[0]++;
			aaa[aaa[0]]=index1;
			index1=p[index1][index2];
		}
		s=s+str[index2];
		aaa[0]++;
		aaa[aaa[0]]=index2;
		System.out.println(s);
		return aaa;
	}
	
	
	//随机游走
	public int[] randomwalk(){
		int []aaa=new int[100];
		String walk="";
		int x=1+(int)(Math.random()*num);
		aaa[1]=x;
		int shumu=1;
		walk=str[x];
		int end_flag=0;
		int find_flag=0;
		int j=0;
		while(end_flag==0){
			 find_flag=0; //System.out.print(x);
			 
			 int xiangling=0;
			 int []aa=new int[100];
			 
			 for(int i=1;i<=num;i++){
				 if(a[x][i]>0){
					 xiangling++;
					 aa[xiangling]=i;
				 }
			 }
			 
			 if(xiangling==0){
				 break;
			 }
			 
			 j=1+(int)(Math.random()*xiangling);
			 walk=walk+" "+str[aa[j]];
			 aaa[++shumu]=aa[j];
			 //System.out.println(j);
			 
			 String []chongfu;
			 chongfu=walk.split(" ");
			 for(int i=0;i<chongfu.length-3;i++){
				 if(chongfu[i].equals(chongfu[chongfu.length-2]) && chongfu[i+1].equals(chongfu[chongfu.length-1]))end_flag=1;
			 }
			 
			 x=aa[j];	 
		}
		System.out.println(walk);
		aaa[0]=shumu;
		return aaa;
	}
}
