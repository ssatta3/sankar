package Server;

import java.util.HashMap;

public class LongestSubstringWoRepeat {
	public static int lengthOfLongestSubstring(String a) {
	    if(a.length()==0){return 0;}
	    int maxLength=1;
	    int length =1;
	    int prevStart=0;
	    HashMap<Character,Integer> hm  = new HashMap<Character,Integer>();
	    for(int i=0;i<a.length();i++){
	         
	        if(hm.containsKey(a.charAt(i))){
	           
	            int pos = hm.get(a.charAt(i));
	            length = length-(pos+1)+prevStart+1;
	            int k =hm.get(a.charAt(i));
	            for(int j=0;j<k-1;j++){
	            	hm.remove(a.charAt(j));
	            	}
	            hm.put(a.charAt(i),i);
	            prevStart = i;
	        }else{
	            hm.put(a.charAt(i),i);
	            length = hm.size();
	        }
	        
	        maxLength = Integer.max(length,maxLength);
	        
	    }
	    return maxLength;
	    
	}
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("dadbc"));
	}

}
