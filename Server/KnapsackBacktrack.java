package Server;

public class KnapsackBacktrack {
	int presentValue;
	public static int doKnapSack(int[] w,int tw,int[] value,int n)
	{   
		if(tw==0||n==0) return 0;
		if(w[n-1]>tw) return 0;
	    int ks = Integer.max(value[n-1]+doKnapSack(w,tw-w[n-1],value,n-1),doKnapSack(w,tw,value,n-1));
	    return ks;
	}
    public static int doKnapSackDynamic(int tw,int[] w,int[] values,int n)
    {
    	int[][] costMatrix = new int[w.length+1][tw+1];
    	for(int i=0;i<w.length+1;i++)
    	{
    		costMatrix[i][0]=0;
    	}
    	for(int i=0;i<tw+1;i++)
    	{
    		costMatrix[0][i]=0;
    	}
    	for(int i=1;i<w.length+1;i++)
    	{
    		for(int j=1;j<tw+1;j++)
    		{
    			if(w[i-1]<=j)
    			{
    			costMatrix[i][j] = Integer.max(costMatrix[i-1][j],values[i-1]+costMatrix[i-1][j-w[i-1]]);
    		}
    			else{
    				costMatrix[i][j]= costMatrix[i-1][j];
    			}
    		}
    	}
    	return costMatrix[w.length][tw];
    }
	public static void main(String[] args) {
		int weight[] = {10, 20, 30};
		int value[] = {60, 100, 120};
		int W = 90;
		int ans1 = doKnapSackDynamic(W, weight, value, 3);
		System.out.println(ans1+ "from dp");
		int ans = doKnapSack(weight,W,value,3);
		System.out.println(ans+ "from back traack ");
	
	}
}
