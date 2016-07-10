package Server;


	 public class overriding{
		 int b=5;
		 public static void main(String args[])
	     {
	    	 B ob = new B();
	    	 ob.show();
	    	 overriding a = ob;
	    	 System.out.println(ob.hashCode());
	    	
	     }
	 }
	 class B extends overriding{
		 int b=10;
		 void show(){
			 int b=15;
			 System.out.println(b);
			 System.out.println(this.b);
			 System.out.println(super.b);
			
		 }
	 
    
}

