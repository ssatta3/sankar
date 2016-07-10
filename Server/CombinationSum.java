package Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {
	

	public static List<List<Integer>> combinationSum(ArrayList<Integer> a, int b) {
       Collections.sort(a);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result = getResult(result, new ArrayList<Integer>(), a, b, 0);

        return result;
    }

    private static List<List<Integer>> getResult(List<List<Integer>> result, List<Integer> cur, List<Integer> candidates, int target, int start){
        if(target<0){return result;}
    	if(target > 0){
            for(int i = 0; i < candidates.size(); i++){
                cur.add(candidates.get(i));
                getResult(result, cur, candidates.subList(i+1, candidates.size()), target - candidates.get(i),i);
                cur.remove(cur.size() - 1);
            }//for
        }//if
        else if(target == 0 ){
            result.add(new ArrayList<Integer>(cur));
        }//else if
        return result;
    }
	public static void main(String[] args) {
		 List<List<Integer>> res = new ArrayList<List<Integer>>();
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList( 8, 10, 6, 11, 1, 16, 8));
		
       Collections.sort(arr);
       List<Integer> l = new ArrayList<Integer>();
       res  = combinationSum(arr,28);
		
		
		
		for(int i=0;i<res.size();i++){
    	   for(int j=0;j<res.get(i).size();j++){
    	 System.out.print(res.get(i).get(j)+" ");
			
    	  }
    	   System.out.println();
       }
	}

	

}
