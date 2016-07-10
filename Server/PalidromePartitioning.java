package Server;

import java.util.ArrayList;

public class PalidromePartitioning {
     
	public static ArrayList<ArrayList<String>> partition(String a) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	 
		if (a == null || a.length() == 0) {
			return result;
		}
	 
		ArrayList<String> partition = new ArrayList<String>();//track each possible partition
		addPalindrome(a, 0, partition, result);
	 
		return result;
	}
	 
	private static void addPalindrome(String s, int start, ArrayList<String> partition,
			ArrayList<ArrayList<String>> result) {
		//stop condition
		if (start == s.length()) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}
	 
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				partition.add(str); 
				addPalindrome(s, i, partition, result);
				partition.remove(partition.size() - 1);
			}
		}
	}
	 
	private static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
	 
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
	 
			left++;
			right--;
		}
	 
		return true;
	}
	public static void main(String[] args) {
		String s = "aab";
		partition(s);
		
		
	}

}
