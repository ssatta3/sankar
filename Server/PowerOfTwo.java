package Server;

public class PowerOfTwo {
	
	public static int power(String a) {
	    int x = Integer.parseInt(a);
	    Integer.toBinaryString(x);
	    System.out.println( Integer.toBinaryString(x));
	    
	    return 5;
	}

	public static void main(String[] args) {
       power("1234");
	}

}
