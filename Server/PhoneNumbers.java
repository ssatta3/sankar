package Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PhoneNumbers {
	 HashMap<Integer,ArrayList<Character>> hm;
	 ArrayList<String> ans;
	 int j=0;
	 int i=0;
	 String s = "";
	PhoneNumbers(){
   ans = new ArrayList<String>();
   hm = new HashMap<Integer,ArrayList<Character>>();
  
   hm.put(1, new ArrayList(Arrays.asList('1')));
   hm.put(2,new ArrayList(Arrays.asList('a','b','c')));
   hm.put(3,new ArrayList(Arrays.asList('d','e','f')));
   hm.put(4,new ArrayList(Arrays.asList('g','h','i')));
   hm.put(5,new ArrayList(Arrays.asList('j','k','l')));
   hm.put(6,new ArrayList(Arrays.asList('m','n','o')));
   hm.put(7,new ArrayList(Arrays.asList('p','q','r','s')));
   hm.put(8,new ArrayList(Arrays.asList('t','u','v')));
   hm.put(9, new ArrayList(Arrays.asList('w','x','y','z')));
   hm.put(0, new ArrayList(Arrays.asList('0')));
	}
	public ArrayList<String> letterCombinations(String a) {
		if(j>hm.get(Integer.parseInt(a.substring(i,i+1))).size()-1){ 
			ans.add(s); 
			i=0;
			return ans;
			}
		while(i<a.length()-1){
			int x = Integer.parseInt(a.substring(i,i+1));
			hm.get(x).get(j);
			s = s+ hm.get(x).get(j);
			i++;
			letterCombinations(a);
			j++;
		}
		return ans;
	}
	
	
	public static void main(String[] args) {
	  char c ='x';
	    
		PhoneNumbers p = new PhoneNumbers();
		p.letterCombinations("234");
	}

}
