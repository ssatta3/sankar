package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerfectSquare {
   
	public static int minSquares(int n){
	int[] dp = new int[n+1];
	for(int i=0;i<dp.length;i++){
		dp[i] = Integer.MAX_VALUE;
	}
	dp[0] = 0;
	
	for(int i =1;i<=n;i++ ){
		for(int j=1;j*j<=i;j++){
			dp[i] = Integer.min((int)dp[i],(int)dp[i-j*j]+1);
		}
	}
	return (int)dp[n];
	}
	public static void main(String[] args) {
		
		System.out.println(minSquares(27));
     
	}

}
