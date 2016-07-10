package Server;

public class JumpGame {
	
	
	public static boolean jumps(int[] a){
		int maxJump= a[0];
		boolean b =true;
	    for(int i=0;i<a.length;i++){
	    	if(a[i]==0 && maxJump == 0){
	    		return false;
	    	}
	    	if(a[i]>maxJump){
	    		maxJump = a[i];
	    	}
	    	else{
	    		maxJump--;
	    	}
	    }
	    return true;
	}
	

	public static void main(String[] args) {
	
		int[] a = {1,0,1,0};
		boolean b = jumps(a);
		System.out.println(b);
	}

}
