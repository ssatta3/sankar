package Server;

import java.util.Arrays;
import java.util.HashSet;

public class ThreeSumToZero {

	public static void sumToZero(int[] a){
	 Arrays.sort(a);
	 HashSet hs = new HashSet();
	 for(int i=0;i<a.length;i++)
	 {hs.add(a[i]);}
	 int j = a.length-1;
	 int sum;
	 int i=0;
	 while(i<j){
		sum = a[i] + a[j];
		
		if(sum>=0){
		if((hs.contains(-sum))){
			System.out.println(a[i]+" "+ a[j]+ " " +-sum);
		}
			j--;
			
		
		}
		if(sum<0){
			if((hs.contains(-sum))){
				System.out.println(a[i]+" "+ a[j]+ " " + -sum);
			}
				i++;
			
		}
		
	 }
	}
	public static void main(String[] args) {
		int[] a = {0,-5,4,1};
		sumToZero(a);
	}

}
