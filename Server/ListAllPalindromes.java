package Server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ListAllPalindromes {
	public static HashSet listAll(String s)
	{
		HashSet hs = new HashSet();
		for(int i=0;i<s.length()-2;i++)
		{
			int j=i+2;
			int k=i;int l=j;
			if(reverse(s.substring(i,j+1))){
				hs.add(s.substring(i,j+1));
				k--;l++;
			
				while(k>=0&&l<=s.length()-1)
				{
					if(reverse(s.substring(k,l+1)))
					{
						hs.add(s.substring(k,l+1));
						
					}else{
						break;
					}
					
					k--;l++;
				}
				if(l==s.length()-1)
				{
					while(k>=0)
					{
						if(reverse(s.substring(k,l+1)))
						{
							hs.add(s.substring(k,l+1));
							k--;
						}else{
							break;
						}
					}
				}
			}
			
		}
		for(int i=0;i<s.length()-2;i++)
		{
			int j=i+1;
			int k=i;int l=j;
			if(reverse(s.substring(i,j+1))){
				hs.add(s.substring(i,j+1));
				k--;l++;
			
				while(k>=0&&l<=s.length()-1)
				{
					if(reverse(s.substring(k,l+1)))
					{
						hs.add(s.substring(k,l+1));
						
					}else{
						break;
					}
					
					k--;l++;
				}
				if(l==s.length()-1)
				{
					while(k>=0)
					{
						if(reverse(s.substring(k,l+1)))
						{
							hs.add(s.substring(k,l+1));
							k--;
						}else{
							break;
						}
					}
				}
			}
			
		}
		for(int i=0;i<s.length();i++)
		{
			hs.add(s.charAt(i));
		}
		return hs;
		
	}
	public static Boolean reverse(String s){
		int high=s.length()-1; 
		int low=0;
		Boolean b=true;
		while(high>low){
			if(s.charAt(high)==s.charAt(low))
			{
				high--;
				low++;
				continue;
			}else{
				b= false;
				break;
			}	
		}
		return b;		
	}
	
	
	public static void main(String[] args) {
		String s = "sivaram pulugurtha";
		Boolean b = reverse(s);
		System.out.println(b);
		HashSet h = listAll(s);
		Iterator itr = h.iterator();
		while(itr.hasNext())
		{ 
			System.out.println(itr.next());
		}
	}

}
