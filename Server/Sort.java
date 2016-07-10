package Server;

import java.util.Random;

public class Sort {
	static int[] x;
    public static int[] bubbleSort(int[] a)
    {
    	int n=a.length;
    	int temp;
    	for(int i=0;i<n;i++)
    	{
    		for(int j=0;j<n-i-1;j++)
    		{
    			if(a[j]>a[j+1])
        		{
        			temp=a[j];
        			a[j]=a[j+1];
        			a[j+1]=temp;
        		}
    		}
    	}
		return a;
    }
    public static int[] selectionSort(int[] a)
    {
    	int temp;
    	int temp1;
    	for(int i=0;i<a.length;i++)
    	{
    		temp=i;
    		for(int j=i+1;j<a.length;j++)
    		{
    			if(a[temp]>a[j])
    			{
    			   temp=j;
    			}
    		}
    		temp1=a[temp];
    		a[temp]=a[i];
    		a[i]=temp1;
    	}
		return a;
    }
    public static int[] insertionSort(int[] a)
    {   int temp;
    	for(int i=0;i<a.length;i++)
    	{
    		int key = a[i];
    		for(int j=i-1;j>=0;j--)
    		{
    			if(a[j+1]>a[j]) break;
    			if(a[j+1]<a[j])
    			{
    				  temp=a[j];
    				a[j]=a[j+1];
    				a[j+1]=temp;
    			}
    		}
    	}
    	return a;
    }
    public static int[] merge(int[] l, int[] r,int[] a )
    {
    	int i=0;int j=0;int k=0;
    	
    	while(i<l.length&&j<r.length)
    	{
    		if(l[i]<=r[j])
    		{
    			a[k] = l[i]; 
    			i++;
    			k++;
    		}else if(l[i]>r[j])
    		{
    			a[k]=r[j];
    			j++;
    			k++;
    		}
    	}
    	while(i<l.length)
    	{
    		a[k]=l[i];
    		i++;
    		k++;
    	}
    	while(j<r.length)
    	{
    		a[k]=r[j];
    		j++;
    		k++;
    	}
    	return a;
    }
    public static void mergeSort(int[] a,int n)
    {
    	if(n==1) return;
    	int leftSize;
    	int rightSize;
    	if(n%2==0)
    	{
    		leftSize = n/2;
    	    rightSize = n/2;
    	}else{
    		leftSize = n/2;
    		rightSize = n/2+1;
    	}
    	int[] left = new int[leftSize];
    	int[] right = new int[rightSize];
    	for(int i=0;i<leftSize;i++)
    	{
    		left[i]=a[i];
    	}
    	for(int i=0;i<rightSize;i++)
    	{
    		right[i]= a[i+leftSize];
    	}
    	mergeSort(left,leftSize);
    	mergeSort(right,rightSize);
        x=merge(left,right,a);
    }
    public static int partition(int[] a,int start,int end)
    {
    	Random rand = new Random();
    	int temp;
    	int partitionIndex = start+rand.nextInt(end-start+1);
    	//int partitionIndex = end;
    	int pivot = a[partitionIndex];
    	int pi=start;
    	for(int i=start;i<end;i++)
    	{
    		if(a[i]<=pivot)
    		{
    			temp = a[i];
    			a[i]=a[pi];
    			a[pi]=temp;
    			pi++;
    		}
    	}
    	temp = a[pi];
    	a[pi]=pivot;
    	pivot = temp;
    	return pi;
    }
    public static void quickSort(int[] a,int start,int end)
    {
    	if(end<=start) return;
    	int partitionIndex =   partition(a,start,end);
    	quickSort(a,start,partitionIndex-1);
    	quickSort(a, partitionIndex+1, end);
    	 
    }
    static int[] a;
    public static int[] mergeSort1(int[] a,int left,int right){
    	int mid = left + (right-left)/2;
    	if(mid==left|| mid == right) return a;
    	mergeSort1(a,left,mid);
    	mergeSort1(a,mid+1,right);
    	int[] b = merge1(a,left,mid,right);
    	return b;
    }
    public static int[] merge1(int[] a, int left,int mid,int right){
    	int i = left;
    	int j = mid+1;
    	int k = left;
    	int[] merged  = new int[a.length];
    	while(i<=mid && j<=right){
    		if(a[i]<=a[j]){
    			merged[k]= a[i];
    			i++;
    			k++;
    		}else if(a[i]>a[j]){
    			merged[k] = a[j];
    			j++;
    			k++;
    		}
    	}
    	while(i<=mid){
    		merged[k]=a[i];
    		i++;
    		k++;
    		
    	}
    	while(j<=right){
    		merged[k]=a[j];
    		j++;
    		k++;
    	}
    	return merged;
    }
    
    
    
    
    
    public static void main(String args[])
    {
    	int[] a = {155,78,23,98,9,7,89,67,56,100,123,1,4,79};
    	int[] merged = mergeSort1(a,0,13);
    	//selectionSort(a);
    	//insertionSort(a);
    	//bubbleSort(a);
    	/*quickSort(a,0,a.length-1);*/
    	for(int i=0;i<merged.length;i++)
    	{
    	System.out.println(merged[i]);
    	}
    	
    }
}
