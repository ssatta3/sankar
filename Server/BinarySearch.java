package Server;

public class BinarySearch {
         public static int binarySearch(int[] a,int min,int max,int target)
         {
        	 int mid = (min+max)/2;
        	 if(min>max)
        	 {
        		 mid=-1;
        		 return min;
        	 }
        	 if(a[mid]==target)
        	 {
        		 return mid;
        	 }
        	if(a[mid]>target)
        	{
        		return binarySearch(a,min,mid-1,target);
        	}else if(a[mid]<target)
        	{
        		return binarySearch(a,mid+1,max,target);
        	}
			return mid;
         }
	public static void main(String[] args) {
		int [] a = {1,2,3,4,4,4,4,4,4,4,4,4,6,7,8,9};
		int pos = binarySearch(a,0,14,10);
		System.out.println(pos);
	}

}
