package Server;

import java.util.ArrayList;
import java.util.Arrays;

public class Perms {
	public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> a) {
	    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
	    ArrayList<Integer> temp = new ArrayList<Integer>();
	    ans = generate(a,ans,0,temp);
	    System.out.println(ans.size());
	    return ans;
	    
	}
	public static ArrayList<ArrayList<Integer>> generate(ArrayList<Integer> a,
	ArrayList<ArrayList<Integer>> ans,int start,ArrayList<Integer> temp){
	    
	    if(temp.size()==a.size()){
	    	
	    ArrayList<Integer> al = 	new ArrayList<Integer>();
	    al.addAll(temp);
	    	ans.add(al); return ans;}
     	   for(int i=start;i<a.size();i++){
     		   
     	       temp.add(a.get(i));
     		   
     	       
     	   } 
     	  generate(a,ans,start+1,temp);
	       temp.remove(temp.size()-1);
	    return ans;
	    
	    
	}
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
		permute(al);

	}

}
