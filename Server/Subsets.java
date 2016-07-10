package Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

public class Subsets {
	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a) {
	    Collections.sort(a);
	    String s="";
	    String temp="";
	    LinkedHashSet<String> hs = new LinkedHashSet<String>();
	    for(int i=0;i<a.size();i++){
	        s=s+Integer.toString(a.get(i));
	    }
	    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
	    hs = generate(s,temp,hs);
	    for(String str:hs){
	    	ArrayList<Integer> al = new ArrayList<Integer>();
	    
	    	for(int i=0;i<s.length();i++){
	    		al.add(Integer.parseInt(s.substring(i,i+1)));
	    		
	    	}
	    }
	    
	    
	    
	    return ans;
	}
	public static LinkedHashSet<String> generate(String s,String temp,LinkedHashSet<String> hs){
	    if(!hs.contains(temp)){hs.add(temp);}
	    if(s.length()==0){ return hs;}
	    
	    for(int i=0;i<s.length();i++){
	        temp = temp+ Character.toString(s.charAt(i));
	        generate(s.substring(i+1),temp,hs);
	        temp=temp.substring(0,temp.length()-1);
	    }
	    
	    return hs;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList(Arrays.asList(1,2,3,4));
		subsets(al);

	}

}
