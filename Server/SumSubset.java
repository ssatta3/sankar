package Server;

public class SumSubset {

	
	static int x=0;
	public static int sumSubsetBacktrack(int[] a,int targetSum,int i)
	{
	    if(targetSum==0) {x=1;System.out.println("sum exists");return x;};
	    if(i==a.length-1) {x=0;return x;}
	    i++;
		sumSubsetBacktrack(a,targetSum,i);
		sumSubsetBacktrack(a,targetSum-a[i],i);
	   
	   return x;
	}
	public static void main(String[] args) {
		int [] a = {2,3,4,5,6,7};
	sumSubsetBacktrack(a, 18, -1);

	}

}
