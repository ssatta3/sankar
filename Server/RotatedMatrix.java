package Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RotatedMatrix {
	public static int binarySearch(List<Integer> a, int start, int end){
		int ans = 0; 
	    int mid = start + (end-start) /2;
	    if(mid==0){return mid;}
	    if (a.get(mid)<a.get(mid-1)&&a.get(mid)<a.get(mid+1)){
            return mid;
        }
	    if(a.get(mid)>a.get(a.size()-1)){
	         ans = binarySearch(a,mid+1,end);
	    } 
	        
	        else if(a.get(mid)<a.get(a.size()-1)){
	        ans = binarySearch(a,start,mid-1);
	    }
	    return ans;
	}
	public static int search(List<Integer> a, int start, int end, int target){
		int mid = start + (end-start)/2;
		   if(start>end){return -1;}
		int ans =0;
		if(a.get(mid)==target){return mid;}
		else if(a.get(mid)>target){
			ans = search(a,start,end-1,target);
		}else {
			ans = search(a,start+1,end,target);
		}
		return ans;
	}
	
	
	
	public static void main(String[] args) {
		Integer[] A =  { 1, 7, 67, 133, 178 };
		ArrayList<Integer> l = new ArrayList<Integer>();
		Collections.addAll(l, A);
		int target =68;
		int ans = 0;
		//l.add(4);l.add(5);l.add(6);l.add(7);l.add(0);l.add(1);l.add(2);
		
		int x = binarySearch(l,0,l.size()-1);
		if(target>l.get(l.size()-1)){
			ans = search(l,0,x,target);
		}else if(target<l.get(l.size()-1)){
			ans = search(l,x,l.size()-1,target);
		} else {
			System.out.println(-1);
		}
	
  System.out.println(ans);
	}

}
