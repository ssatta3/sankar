package Server;

import java.util.ArrayList;
import java.util.List;

public class MedianRecurse {
	public static int median(List<Integer> a, int start1,int end1,List<Integer> b,int start2,int end2){
	    int median1 = 0;
	    int median2 = 0;
	    
	    int diff1=Integer.MAX_VALUE; int diff2=Integer.MAX_VALUE;int diff = Integer.MAX_VALUE;
	    
	    if((end1-start1+1)%2==0){
	        median1 = start1+ (((end1-start1+1)/2) + ((end1-start1+1)/2) + 1)/2;
	    }else{
	        median1 = start1+ ((end1-start1+1)/2);
	    }
	     if((end2-start2+1)%2==0){
	        median2 = start2 + (((end2-start2+1)/2) + ((end2-start2+1)/2) + 1)/2;
	    }else{
	        median2 = start2 + ((end2-start2+1)/2);
	    }
	    if(a.get(median1)<b.get(median2)){
	       diff1 = Math.abs(median1-start1);
	       diff2 = Math.abs(median2-start2);
	       diff = Math.min(diff1,diff2);
	        start1=start1+diff;
	        end2= end2-diff;
	        median(a,start1,end1,b,start2,end2);
	    }
	    if(a.get(median1)>b.get(median2)){
	      diff1 = end1-median1;
	     diff2 =    end2-median2;
	   diff = Math.min(diff1,diff2);
	        end1 = end1-diff;
	        start2 = start2+diff;
	        median(a,start1,end1,b,start2,end2);
	    }
	    return 1;
	}
	public static void main(String[] args) {
	List<Integer> a =  new ArrayList<Integer>();
	List<Integer> b =  new ArrayList<Integer>();
	a.add(1);a.add(1);a.add(2);a.add(3);a.add(7);a.add(9);a.add(10);
	b.add(5);b.add(7);b.add(11);b.add(12);b.add(14);b.add(15);b.add(16);
	median(a,0,6,b,0,6);

	}

}
