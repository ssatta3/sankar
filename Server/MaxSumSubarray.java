package Server;

public class MaxSumSubarray {

	public static int maxSumSub(int[] a)
	{
		int ans=0;
		int max=0;
		for(int i=0;i<a.length;i++)
		{
			max += a[i];
			if(max<0) max=0;
			ans = Math.max(ans, max);
		}
		return ans;
	}
	
	public static void main(String[] args) {
		
       int[] a = {6,3,-6,8,9,-9,10,-18,23};
       int max = maxSumSub(a);
       System.out.println(max);
	}

}
