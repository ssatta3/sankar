package Server;

import java.util.ArrayList;

public class Histograms {
	public static int largestRectangleArea(ArrayList<Integer> a) {
	    int max=0;
	    int currMax=a.get(0);
	    int minIndex=0;
	    int minValue=a.get(0);
	    for(int i=1;i<a.size();i++){
	      minValue = Integer.min(minValue,a.get(i));
	      if(a.get(i)>minValue){
	          currMax = Integer.max(a.get(i),minValue*(i-minIndex+1));
	          if(currMax==a.get(i)){
	              minIndex=i; minValue=a.get(i);
	          }
	          
	      }else if(a.get(i)<minValue){
	          currMax = Integer.max(currMax,a.get(i)*(i-minIndex+1));
	          if(currMax==a.get(i)*(i-minIndex)){
	              minIndex=i; minValue=a.get(i);
	          }else{
	              minValue = a.get(i);
	              minIndex = i;
	          }
	      }
	      max = Integer.max(currMax,max);
	    }
	    
	   return max; 
	}
	public static void main(String[] args) {
		ArrayList a = new ArrayList<>();
		a.add(6);a.add(2);a.add(5);a.add(4);a.add(5);a.add(1);a.add(6);
		System.out.println(largestRectangleArea(a));
	}

}
