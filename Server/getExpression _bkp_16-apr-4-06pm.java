package Server;

import java.util.Arrays;
import java.util.TreeSet;

public class getExpression 
{
	static int n = 3;
	static int[] arr; 
	static TreeSet<String> expr; 
	public static void main(String[] args) throws Exception
	{
		boolean[] isUsed = new boolean[3];
		Arrays.fill(isUsed, false);
		arr = new int[]{1,2,3};
		char[] symbols = {'+', '-', '*', '/'};
		char[] binString = new char[6];
		expr = new TreeSet<String>();
		generateAllBinaryStrings(0,  binString, isUsed);
		
		for (String str : expr) 
		{
			//System.out.print(str + " ");
			String e = str.replace("(", "");
			e = e.replace(")", "");
			
			char currInt = e.charAt(0);
			int currVal = Integer.parseInt(Character.toString(currInt));
			
			for(int k =1; k < e.length(); k++)
			{
				char ch  = e.charAt(k);
				if(ch == '+' || ch == '-' || ch == '*' || ch == '/')
				{
					char currInt1 = e.charAt(++k);
					currVal = Integer.parseInt(Character.toString(currInt1));
				}
				
			}
			
			
		}
		
		
		
	}
	
	public static void generateAllBinaryStrings(int n, char[] binString, boolean[] isUsed)
    {
    	if(n>=5)
    	{
    		
    		//expr.add(binString.to)
    		String str = "";
    		for(int index=0; index < binString.length-1 ; index++)
    		{
    			char ch = binString[index];
    			String str1 = String.valueOf(binString);
    			if(evaluate(str1.substring(0,binString.length-1))){
    				System.out.println(str1.substring(0,binString.length-1));
    				System.out.println("found");
    				break;
    			
    					 }
    			if(ch == '+' || ch == '-' || ch == '*' || ch == '/')
    			{
    				str = "(" + str + Character.toString(ch);
    			}
    			else if(index!=0)
    			{
    				str  = str +  Character.toString(binString[index])+ ")";
    			}
    			else
    				str  = str +  Character.toString(binString[index]);
    		}
    		//System.out.println(binString);
    	
    		expr.add(str);
    	}
    	else
    	{
    		for(int i=0; i<3; i+=1)
    		{
    			if(!isUsed[i])
    			{
    					binString[n]= Integer.toString(arr[i]).charAt(0);
        				isUsed[i]= true;
        				binString[n+1] = '+';
                		generateAllBinaryStrings(n+2, binString, isUsed);
                		binString[n+1] = '*';
                		generateAllBinaryStrings(n+2, binString, isUsed);
                		binString[n+1] = '-';
                		generateAllBinaryStrings(n+2, binString, isUsed);
                		binString[n+1] = '/';
                		generateAllBinaryStrings(n+2, binString, isUsed);
                		isUsed[i]= false;
    				
    			}
    			
    		}
    	}
    }
	
	public static boolean evaluate(String s){
		char c;
		int i=1;
		double res=(double)Integer.parseInt(s.charAt(0)+"");
		//System.out.println(s.length());
		while(i!=s.length()-1){
			c=s.charAt(i);
			switch(c){
			case '+': res = res+(double)Integer.parseInt(s.charAt(i+1)+"");
			                i++; 
		                   	break;
			case '/': res = res/(double)Integer.parseInt(s.charAt(i+1)+"");
			                 i++;
			                 break;
			case '*': res = res*(double)Integer.parseInt(s.charAt(i+1)+"");
			                 i++;
			                 break;
			case '-': res = res-(double)Integer.parseInt(s.charAt(i+1)+"");
			                 i++;
			                 break;
			default : i++; break;
			}
		}
		if(res==5) {
		return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
}