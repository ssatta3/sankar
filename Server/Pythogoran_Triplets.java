package Server;

import java.util.Arrays;

public class Pythogoran_Triplets {
    
	 public static int pythogoranTriplets(int[] A){
         for(int j=0;j<A.length;j++){
        	 A[j] = A[j]*A[j];
         }
		 Arrays.sort(A); 		 
     for(int i=A.length-1;i>1;i--){
    	 int start=0; int mid=i-1;
    	 while(start<mid){
    		 if(A[i]==A[start]+A[mid]){return 1;}
    		 else if(A[i]<A[start]+A[mid]){mid--;}
    		 else{start++;}
    	 }
     }
     return 0;
	 }
	
	
	public static void main(String[] args) {
		int[] A = {3,4,20,6,8};
		System.out.println(pythogoranTriplets(A));

	}

}
