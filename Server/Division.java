package Server;

import java.util.HashMap;
import java.util.HashSet;

public class Division {
	public static String fractionToDecimal(long numerator, long denominator) {
	    numerator = Math.abs(numerator);
	    denominator = Math.abs(denominator);
	    String s ="";
	    s.su
		int rem = (int)numerator;
	    int q = Integer.MAX_VALUE;
	    int prevRem = Integer.MAX_VALUE;
	    HashSet<String> hs = new HashSet<String>();
	    HashMap<Integer,Integer> hm = new HashMap<>();
	    hm.
	    StringBuffer res = new StringBuffer("");
	    int i=1;
	    while(rem!=0){
	    	
	    	  int counter=1;
	    	  while(numerator<denominator){
		        	 if(counter==1){
		        	 if(!hs.contains(".")){
		        		 res.append(".");
		        	     numerator*=10; 
		        	     counter++;
		        	     hs.add(".");
		        	 }else{ 
		        		 numerator*=10;
		        		 counter++;
		        		 }
		        	 }else{
		        		 numerator*=10;
		        		 res = res.append("0");
		        		 counter++;
		        	 }
		         }
	          q = (int)Math.floorDiv(numerator , denominator);
	         hs.add(Integer.toString(q));
	         res = res.append(Integer.toString(q));
	         rem = (int)(numerator - q * denominator);
	         
	         if(hm.containsKey(rem) && hs.contains(".")){          
	        	 res.insert(hm.get(rem), "(");
	        	 res.deleteCharAt(res.length()-1);
	        	 res.append(")");
	        	 break;}
	         hm.put(rem,i);
	         numerator=rem;
	        prevRem = rem;
	        i++;
	    }  
	    return res.toString();
	}
	public static void main(String[] args) {
		float m =(float)3/2;
		//System.out.println(m);
       System.out.println(fractionToDecimal(1,11));
	}

}
