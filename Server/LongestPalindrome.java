package Server;

public class LongestPalindrome {
  
	
	public static String longestPalindrome(String a) {
		  int n = a.length();
		  if (n == 0) return "";
		  String longest = a.substring(0, 1);  // a single char itself is a palindrome
		  for (int i = 0; i < n-1; i++) {
		    String p1 = expandAroundCenter(a, i, i);
		    if (p1.length() > longest.length())
		      longest = p1;
		 
		    String p2 = expandAroundCenter(a, i, i+1);
		    if (p2.length() > longest.length())
		      longest = p2;
		  }
		  return longest;
		}
		  static String expandAroundCenter(String s, int c1, int c2) {
		  int l = c1, r = c2;
		  int n = s.length();
		  while (l >= 0 && r <= n-1 && s.charAt(l) == s.charAt(r)) {
		    l--;
		    r++;
		  }
		  
		 // if(l==-1){l=0;}
		  if((l+1)>r-l-1){return ""; }
		  return s.substring(l+1, r-l);
		}
	public static void main(String[] args) {
	String s = longestPalindrome("sarada");

	}

}
