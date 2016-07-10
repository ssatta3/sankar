package Server;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GrayCode {
	
		public static ArrayList<String> grayCode(int a) {
		    int length=0;
		    int j=0;
		    String temp="";
		    ArrayList<Integer> a1 =new ArrayList<Integer>(); 
		    ArrayList<Integer> a2 =new ArrayList<Integer>(); 
		    ArrayList<String> hs=new ArrayList<String>(); 
		    ArrayList<String> hs1=new ArrayList<String>(); 
		     LinkedHashSet<String> res=new LinkedHashSet<String>(); 
		     hs.add("");
		   hs =  generate(a,length,hs);
		   for(String i:hs){
			   if(i.length()==a){a1.add(Integer.parseInt(i,2));}
		   }
		   for(int i=0;i<hs.size()/2;i++){
			   j=i+hs.size()/2;
			   a2.add(a1.get(i));
			   a2.add(a1.get(i+hs.size()/2));
			   
		   }
		   for(int i=0;i<hs.size()/2;i++){
			   j=i+hs.size()/2;
			   if(i%2==0){
			   hs1.add(hs.get(i));
			   hs1.add(hs.get(i+hs.size()/2));
			   }else{hs1.add(hs.get(i+hs.size()/2));  hs1.add(hs.get(i));}
		   }
		    return hs;
		}
		public static ArrayList<String> generate(int a,int length,ArrayList<String> hs){
			ArrayList<String> clone = new ArrayList<>(hs);
			if(length==a){return clone;}
		    int k=hs.size();
		    for(int i=0;i<k;i++){
		        String s = clone.get(0);
		        clone.remove(0);
		        s = "0"+s ;
		        if(!clone.contains(i)){
		        	clone.add(s);
		        	}
		      
		    }
		    for(int i=k-1;i>=0;i--){
		    	String s = hs.get(i);
		    	
		    	s = "1"+s;
		    	if(!clone.contains(i)){
		    		clone.add(s);
		    		}
		    }
		    hs.clear();
		    hs.addAll(clone);
		  
		 return(generate(a,length+1,clone));
		
		    
		}
	
	public static void main(String[] args) {
		ArrayList<String> a = grayCode(3);
		for(String i:a){System.out.println(i);}
		

	}

}
