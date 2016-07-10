package Server;

import java.util.ArrayList;
import java.util.List;

public class GenerateParantheses {

	public static void flip(StringBuilder s,int i,int j){
		StringBuilder s1 =  new StringBuilder(s);
		if(j==s.length()-1||i==s.length()/2){
			return;
		}
		 s1.setCharAt(i,')');
		 s1.setCharAt(j, '(');
		System.out.println(s1);		
		flip(s,i,j+1);
	     flip(s,i+1,j);
		//System.out.println(s1);

	}
	 public static  List<String> generateParenthesis(int n) {
	        List<String> list = new ArrayList<String>();
	        backtrack(list, "", 0, 0, n);
	        return list;
	    }

	    public static void backtrack(List<String> list, String str, int open, int close, int max){

	        if(str.length() == max*2){
	            list.add(str);
	            return;
	        }

	        if(open < max)
	            backtrack(list, str+"(", open+1, close, max);
	        if(close < open)
	            backtrack(list, str+")", open, close+1, max);
	    }
	public static void main(String[] args) {
		StringBuilder s1 = new StringBuilder("(((())))");
		flip(s1,1,4);
        //List l = generateParenthesis(4);
        //for(int i=0;i<l.size();i++){
        	//System.out.println((String)(l.get(i)));
       // }
        
	}

}
