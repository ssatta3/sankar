package Server;

import java.util.ArrayList;
import java.util.List;

public class ArrayThreePointer {
	public static int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
	    int i=0;int j=0;int k =0;
	    int maxDiff =Integer.MAX_VALUE;
	    int tempMaxDiff =Integer.MAX_VALUE;
	    while(i<a.size()&&j<b.size()&&k<c.size()){
	        tempMaxDiff = Integer.max(Math.max(Math.abs(a.get(i)-b.get(j)),Math.abs(b.get(j)-c.get(k)))
	                               ,Math.abs(c.get(k)-a.get(i)));
	       if(tempMaxDiff==0){return 0;}
	        if(tempMaxDiff==Math.abs(a.get(i)-b.get(j))){ 
	            if(a.get(i)>b.get(j)){
	                j++;
	            }else{i++;}
	            maxDiff = Math.min(maxDiff,tempMaxDiff);continue;
	        } if(tempMaxDiff==Math.abs(b.get(j)-c.get(k))){ 
	            if(b.get(j)>c.get(k)){
	                k++;
	            }else{j++;}
	            maxDiff = Math.min(maxDiff,tempMaxDiff); continue;
	        } if(tempMaxDiff==Math.abs(a.get(i)-c.get(k))){ 
	            if(a.get(i)>c.get(k)){
	                k++;
	            }else{i++;}
	            maxDiff = Math.min(maxDiff,tempMaxDiff); continue;
	    }
	    
	    
	    
	}
	return maxDiff;
	}
	public static void main(String[] args) {
	ArrayList a = new ArrayList<>(); a.add(1);a.add(4);a.add(10);
	ArrayList b = new ArrayList<>(); b.add(2);b.add(15);b.add(20);
	ArrayList c = new ArrayList<>(); c.add(10);c.add(12);
	System.out.println(minimize(a, b, c));
	}

}
