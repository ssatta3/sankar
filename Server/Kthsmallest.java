package Server;

import java.awt.PageAttributes;
import java.util.Random;

public class Kthsmallest {
	static int[] a;
	public static int kthsmall(int k,int start,int end,int[] a){
	
		
		int i = partition(a,start,end);
		if(i==k) {return a[i];}
		else if(i>k){
			return kthsmall(k,start,i-1,a);
		}
		else if(i<k) {
			return kthsmall(k,i+1,end,a);
		}
		return Integer.MAX_VALUE;
	}
    public static int partition(int[] a,int start,int end){
    	int partition = start;
    	int pivot = a[start];
    	int temp;
    	for(int i=start;i<end;i++){
             if(a[i]<pivot){
            	 temp = a[i];
            	 a[i] = a[partition];
            	 a[partition]=temp;
            	 partition++;
             }
    	}
    	 temp = a[start];
         a[start] = a[partition];
         a[partition]=temp;
         return partition;
    }
	
	public static void main(String[] args) {
		int[] a = {6,10,13,5,8,3,2,11};
        int x = kthsmall(0,0,7,a);
        System.out.println(x);
	}

}
//	int partitionIndex = start+rand.nextInt(end-start+1);