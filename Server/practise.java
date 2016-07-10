package Server;

import java.util.ArrayList;
import java.util.Stack;

public class practise {
	     static int words;
         public static int[][] calculateCost(String s,int l)
         {
        	String[] s1 = s.split(" ");
        	 words = s1.length;
        	int[][] costmatrix = new int[words][words];
        	for(int i=0;i<words;i++)
        	{
        		costmatrix[i][i]=l-s1[i].length();
        	   for(int j=i+1;j<words;j++)
        	   {
        		   costmatrix[i][j]= costmatrix[i][j-1]-s1[j].length()-1;
        	   }
        	}
        	for(int i=0;i<words;i++)
        	{
        		for(int j=0;j<words;j++)
        		{
        			if(costmatrix[i][j]<0)
        			{
        				costmatrix[i][j]=9999;
        			}else{
        				costmatrix[i][j]=(int) Math.pow(costmatrix[i][j],2);
        			}
        		}
        	}
        	
			return costmatrix;
        	 
         }
         public static void printMatrix(int[][] x,int length)
         {
        	 for(int i=0;i<length;i++)
        	 {
        		 for(int j=0;j<length;j++)
        		 {
        			 System.out.print(x[i][j]+" ");
        		 }
        		 System.out.println();
        	 }
         }
         public static ArrayList wordWrap(int[][] cost)
         {
        	 int[] minCost = new int[words];
        	 for(int i=0;i<words;i++)
        	 {
        		 minCost[i]=9999999;
        	 }
        	 int[] path = new int[words];
        	 int[] temp = new int[words];
        	 int prevcost=99999999;
        	 for(int i=words-1;i>=0;i--)
        	 {
        		 minCost[i]=cost[i][words-1];
        		 path[i] = words;
        		 for(int j=words-1;j>i;j--)
        		 {
        			 if(cost[i][j-1] == 9999){
                         continue;
                     }
        			if(minCost[i]>minCost[j]+cost[i][j-i]){
        				minCost[i]=minCost[j]+cost[i][j-1];
        				path[i] = j;
        		 }
        		 }
        	 }
        	 ArrayList al = new ArrayList();
        	 al.add(0, minCost);
        	 al.add(1,path);
        	 return al;
         }
	
	public static void main(String[] args) {
	   String s = "sankar is a bad guy";
	   int[][] x = calculateCost(s,10);
	   printMatrix(x,5);	
	   ArrayList al= wordWrap(x);
	   int[] cost =(int[])al.get(0);
	   int[] path = (int[])al.get(1);
	   for(int i =0 ; i< cost.length;i++)
	   {
		   System.out.print(cost[i]+" ");
	   }
	   System.out.println();
	   for(int i =0 ; i< path.length;i++)
	   {
		   System.out.print(path[i]+" ");
	   }
	   
	}
}
