package Server;

public class RemoveDuplicatesLexo {

	public static void removeByBooleanMap(String s){
		boolean[] b = new boolean[200];
		for(int i=0;i<200;i++){
			b[i]=true;
		}
		for(int i=0;i<s.length();i++){
			if(b[s.charAt(i)]=true){
				b[s.charAt(i)]=false;
			}
		}
		for(int i=0;i<200;i++){
			if(b[i]==false){
				System.out.println((char)i);
			}
		}
		
		
	}
	public static void main(String[] args) {
		String s="cbacdcbc";
		removeByBooleanMap(s);

	}

}
