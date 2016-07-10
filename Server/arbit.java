package Server;

import java.util.HashSet;

public class arbit {
	 
    public static int recursiveBinarySearch(int[] sortedArray, int start, int end, int key) {
         
        if (start <= end) {
            int mid = start + (end - start) / 2; 
            if (key < sortedArray[mid]) {
                recursiveBinarySearch(sortedArray, start, mid, key);
                 
            } else if (key > sortedArray[mid]) {
                recursiveBinarySearch(sortedArray, mid+1, end , key);
                 
            } else {
                return mid;  
            }
        }
        return (start + 1); 
    }
 
    public static void main(String[] args) {
    	      String test = "10 2 3"; 
    	      String[] s1 = test.split(" ");
        	for(int i=1;i<s1.length;i++){
            	System.out.println(s1[i]);
        	}
           
            
          
        }
     
       
        /*System.out.println(x==y);
    	int[] arr1 = {2,45,234,567,876,900,976,999};
        int index = recursiveBinarySearch(arr1,0,arr1.length,45);
        System.out.println("Found 45 at "+index+" index");
        index = recursiveBinarySearch(arr1,0,arr1.length,999);
        System.out.println("Found 999 at "+index+" index");
        index = recursiveBinarySearch(arr1,0,arr1.length,876);
        System.out.println("Found 876 at "+index+" index");*/
    }
