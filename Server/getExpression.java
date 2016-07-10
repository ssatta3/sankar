package Server;

import java.util.Arrays;
import java.util.TreeSet;

public class getExpression 
{
	static int n = 3;
	static int[] arr; 
	static int ipSize;
	static TreeSet<String> expr; 
	static boolean shallContinue = true;
	static int target;
	public static void main(String[] args) throws Exception
	{
		
		
		arr = new int[]{10,2};
		target = 1;
		ipSize = arr.length;
		boolean[] isUsed = new boolean[ipSize];
		Arrays.fill(isUsed, false);
		char[] symbols = {'+', '-', '*', '/'};
		//char[] binString = new char[6];
		String binString="";
		expr = new TreeSet<String>();
		generateAllBinaryStrings(0,  binString, isUsed);

		/*for (String str : expr) 
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


		}*/



	}

	public static void generateAllBinaryStrings(int n, String binString, boolean[] isUsed)
	{
		
		if(n>=ipSize)
		{
			System.out.println(binString);
			return;
			/*
			
			System.out.println(binString);
			//expr.add(binString.to)
			String str = "";
			String str1 = String.valueOf(binString);
			if(evaluate(str1.substring(0,binString.length()-1))){
				System.out.println(str1.substring(0,binString.length()-1));
				System.out.println("found");
				
				shallContinue = false;
				for(int index=0; index < binString.length()-1 ; index++)
				{
					char ch = binString.charAt(index);
					
					if(ch == '+' || ch == '-' || ch == '*' || ch == '/')
	    			{
	    				str = "(" + str + Character.toString(ch);
	    			}
	    			else if(index!=0)
	    			{
	    				str  = str +  Character.toString(binString.charAt(index))+ ")";
	    			}
	    			else
	    				str  = str +  Character.toString(binString.charAt(index));
				}
				System.out.println(str);

			}
			
			expr.add(str);
		*/}
		else
		{
			for(int i=0; i<ipSize; i+=1)
			{
				if(!isUsed[i])
				{
					//binString[n]= Integer.toString(arr[i]).charAt(0);
					binString = binString + Integer.toString(arr[i]);
					isUsed[i]= true;
					binString = binString + "+";
					generateAllBinaryStrings(n+1, binString, isUsed);
					
					binString = binString.substring(0, binString.length()-1);
					binString= binString +  '*';
					generateAllBinaryStrings(n+1, binString, isUsed);
					binString = binString.substring(0, binString.length()-1);
					binString= binString +  '-';
					if(shallContinue)
						generateAllBinaryStrings(n+1, binString, isUsed);
					
					binString = binString.substring(0, binString.length()-1);
					binString= binString +  '/';
					if(shallContinue)
						generateAllBinaryStrings(n+1, binString, isUsed);
					//binString = binString.substring(0, binString.length()-1);
					isUsed[i]= false;
					binString="";

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
		if(res==target) {
			return true;
		}else{
			return false;
		}
	}





}