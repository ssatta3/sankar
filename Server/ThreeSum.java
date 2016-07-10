package Server;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeSum {
	public static int threeSumClosest(ArrayList<Integer> a, int b) {
	    Collections.sort(a);
	    
	   /* for(int i=0;i<a.size();i++){
	        a.set(i,a.get(i)-b);
	    }*/
	    
	    int newTarget=0;
	    int ans=0;
	    int min  = Integer.MAX_VALUE;
	     int temp  = Integer.MAX_VALUE;
	    for(int i=0;i<a.size();i++ ){
	    	int k=a.size()-1;
	        newTarget = b-a.get(i); 
	        for(int j=i+1;j<a.size();j++){
	            for(;k>j;k--){
	                temp = Math.abs(newTarget-(a.get(j)+a.get(k)));
	                min = Math.min(temp,min);
	                if(min==temp){ans = a.get(i)+a.get(j)+a.get(k);}
	                
	            }
	        }
	    }
	 return ans;   	    
	  
	}
	public static void main(String[] args) {
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(5);a.add(-2);a.add(-1);a.add(-10);a.add(10);
		System.out.println(threeSumClosest(a, 5));
	}

}
