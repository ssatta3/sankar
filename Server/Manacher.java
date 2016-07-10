package Server;

public class Manacher {
	
	public static int[] manach(String s){
		int c=0; int mirror = 0;int r=0;
		int[] p = new int[s.length()];
		for(int i=1;i<s.length();i++){
		   mirror = 2*c-1;
		   if(i<r){
			   p[i] = Math.min(r-i, p[mirror]);
		   }
		if(i+1+p[i]<s.length()){
		while(s.charAt(i+(1+p[i]))==s.charAt(i-(1+p[i]))){
			p[i]++;
		}
		if(i+p[i]>r){
			c=i;
			r=i +p[i];
		}
		
	}
		}
		return p;
	}
	public static void main(String[] args) {
		String s = "@#a#b#a#b#a#b#a#&";
		int[] p = manach(s);
		for(int i=0;i<p.length;i++){
			System.out.println(p[i]);
		}

	}

}
