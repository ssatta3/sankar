package Server;

import java.util.ArrayList;
import java.util.List;

public class Nqueens {
	public static List<List<StringBuffer>> driver(int n){
		List<List<StringBuffer>> res = new ArrayList<List<StringBuffer>>();
	
		List<StringBuffer> sol = new ArrayList<StringBuffer>(n);
		nQueens(res,sol,n,0);
		return res;
	}
   public static void nQueens(List<List<StringBuffer>> res,List<StringBuffer> sol, int n,int row){
	   if(row==n){
		   res.add(sol);
		   return;
	   }
	  
	   for(int col=0;col<n;col++){
		   if(sanityCheck(sol,n,col,row)){
			   sol.get(row).setCharAt(col,'Q');
			   nQueens(res,sol,n,row+1);
			   sol.get(row).setCharAt(col, '.');
		   }
	   }
	   return;
   }
   
   public static boolean sanityCheck(List<StringBuffer> sol,int n,int col,int row){
	   for (int i = 0; i != row; ++i)
           if (sol.get(row).charAt(col) == 'Q')
               return false;
       //check if the 45° diagonal had a queen before.
       for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; --i, --j)
           if (sol.get(row).charAt(col) == 'Q')
               return false;
       //check if the 135° diagonal had a queen before.
       for (int i = row - 1, j = col + 1; i >= 0 && j < n; --i, ++j)
           if (sol.get(row).charAt(col) == 'Q')
               return false;
       return true;
   }
	public static void main(StringBuffer[] args) {
		List<List<StringBuffer>> l =driver(6);
	}

}
