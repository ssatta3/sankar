package Server;

public class Strstr {
	public static int strStr(final String haystack, final String needle) {
	    if(haystack.length()==0 || needle.length()==0){
	        return -1;
	    }
	    
	    for(int i=0;i<haystack.length()-needle.length();i++){
	        if((haystack.substring(i,needle.length())).equals(needle)){
	            return i;
	        }
	    }
	    return -1;
	}
	public static void main(String[] args) {
		int x = strStr("sankarh","sankar");
        System.out.println(x);
	}

}
