package Server;

public class RegexMatching {
     static boolean b = true;
	public static boolean match(String text, String pattern){
		int i=0; int j=0;
		
		if(pattern.charAt(j)==text.charAt(i)&& pattern.charAt(j)!='*'&& pattern.charAt(j+1)!='*'){
			return match(text.substring(1),pattern.substring(1));
		} else if(pattern.charAt(j+1)== '*'){
			while()
			
		}
		   
		}
	
	public static void main(String[] args) {
		

	}

}
