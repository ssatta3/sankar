package Server;

public class VerifyPreorder {
    public static boolean preOrder(String preorder){
    	int count=0;
    	
    	for(int i=0;i<preorder.length();i++){
    		if(i==0) {count = count +2;continue;}
    		if(preorder.charAt(i)=='#'){
    			count  =  count-1;
    		}else 
    		{
    			count = count +1;
    		}
    		if(count<=0 && i < preorder.length()-1) return false;
    	}
    	return true;
    }
    public static void main(String args[]){
    String s = "1##2";
    boolean b =preOrder(s);
    System.out.println(b);
}
}
