package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringConcatenation {
	public static ArrayList<Integer> findSubstring(String a, final List<String> b) {
		 HashMap<String,Integer> hm  = new HashMap<String,Integer>();
		   HashMap<String,Integer> hm1  = new HashMap<String,Integer>();
		   ArrayList<Integer> ans = new ArrayList<Integer>();
		   int wordLength = b.get(0).length();
		   for(int i=0;i<b.size();i++){
		       if(hm.containsKey(b.get(i))){
		           int val = hm.get(b.get(i));
		           val++;
		           hm.put(b.get(i),val);
		          
		       }else{hm.put(b.get(i),1);}
		   }
		   hm1.putAll(hm);
		   int i=0;
		   int j=0;
		   while(i<a.length()-wordLength+1){
		       
		       if(hm1.containsKey(a.substring(i,i+wordLength))){
		           if(hm1.get(a.substring(i,i+wordLength))==1){
		               hm1.remove(a.substring(i,i+wordLength));
		               
		               if(hm1.size()==0){
		               ans.add(i-((b.size()-1)*wordLength));hm1.putAll(hm);
		               i=j;i++;j=i; i = i-wordLength;
		               }
		              //if(i!=j || i==0)
		               i=i+wordLength;
		           }else{
		               int val1 = hm1.get(a.substring(i,i+wordLength));
		              hm1.put(a.substring(i,i+wordLength),val1-1);
		               i=i+wordLength;
		           }
		          
		       }else{
		    	   hm1.putAll(hm);i=j+1;j++;
		    	   }
		   }
		  return ans;  
		}
	public static void main(String[] args) {
		String s = "barfoothefoobarman";
		List<String> s1 = new ArrayList<String>();
		s1.add("foo"); s1.add("bar");//s1.add("aaa");s1.add("aaa");s1.add("aaa");
        findSubstring(s, s1);
	}

}
