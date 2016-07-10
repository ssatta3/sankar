package Server;

public class IncreasingTriplet {
	 
	 public static boolean IncTriplet(int[] a){
		 int min1,min2,min3,tempMin1,tempMin2,tempMin3;
		 min1= Integer.MAX_VALUE;
		 min2 = Integer.MAX_VALUE; min3 = Integer.MAX_VALUE;
		 int n = a.length;
		 min1 = a[0];
		 min2 = a[1];
		 if(min2<=min1){
			min1 = min2;
			min2 = Integer.MAX_VALUE;
		 }
		 tempMin1 = min1;
		 tempMin2 = min2;
		 int j;
		 for(int i=2;i<n;i++){
	      if(a[i]<min2 && a[i]> tempMin1)
	      {
	    	  min1 = tempMin1;
	    	  min2 = a[i];
	      }
	      if(a[i]>min2) return true;
	      if(a[i]<min1){
	    	  tempMin1 = a[i];
	      }	 
		 }
		 return false;
		 
	 }

	public static void main(String[] args) {
		
       int[] a = {5,4,3,2,2};
       boolean b = IncTriplet(a);
       System.out.println(b);
	}

}
