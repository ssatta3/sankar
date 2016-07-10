package Server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Sudoku {
	public static int isValidSudoku(final List<String> a) {
	    HashSet hs = new HashSet();
	    //row check
	    for(String s:a){
	        for(int i=0;i<s.length();i++){
	            if(hs.contains(s.charAt(i)) && s.charAt(i)!='.'){
	            	return 0;
	            	}
	            else { 
	            	hs.add(s.charAt(i));
	            	}
	        }
	        hs.clear();
	    }
	    //column check
	    for(int i=0;i<9;i++){
	        for(String s:a){
	            if(hs.contains(s.charAt(i)) && s.charAt(i)!='.'){
	            	return 0;
	            	} 
	            else {hs.add(s.charAt(i));}
	        }
	        hs.clear();
	    }
	    //grid check
	    int i=0;
	    int j=0;
	    int counter=0;
	    for(int k=0;k<a.size();k++){
	        for(i=j;i<j+3;i++){
	           if(hs.contains(a.get(k).charAt(i)) && a.get(k).charAt(i)!='.'){
	        	   return 0;
	        	   } 
	           else {
	        	   hs.add(a.get(k).charAt(i));
	        	   }
	            counter++;
	            if(counter%9==0){hs.clear();}
	            if(counter%27==0){j=j+3;k=0;}
	        }
	        if(counter==81){break;}
	    }
	    return 1;
	    }
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("....5..1.");
		l.add(".4.3.....");l.add(".....3..1");l.add("8......2.");
		l.add("..2.7....");l.add(".15......");l.add(".....2...");
		l.add(".2.9.....");l.add("..4......");
		System.out.println(isValidSudoku(l));
	}

}
