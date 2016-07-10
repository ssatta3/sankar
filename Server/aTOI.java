package Server;

public class aTOI {
	public static int atoi(final String a) {
	    String a1 = a.trim();
	    String res = "";
	    if(a1.charAt(0)== '+' || a1.charAt(0)=='-'|| (a1.charAt(0)>=48&&a1.charAt(0)<=57)) 
	    {res += a1.charAt(0);}
	    
	    for(int i=1;i<a1.length();i++){
	        if(a1.charAt(i)>=48 && a1.charAt(i)<=57){
	            res+=a1.charAt(i);
	        }else{
	            break;
	        }
	    }
	    if(res.equals("")){return 0;}
	    try {
    return Integer.parseInt(res);
  } catch (NumberFormatException e) {
    if(res.charAt(0)=='-'){
        return Integer.MIN_VALUE;
    }else{
        return Integer.MAX_VALUE;
    }
  }
	}
	public static void main(String[] args) {
		System.out.println(atoi(" V515V 5793K 627 23815945269 1 1249794L 631 8755 7"));

	}

}
