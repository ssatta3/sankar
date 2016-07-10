package Server;

public class NextNumber {

	public static void nextNum(int n){
	  int posofFirstOne=0;
	  int posofSecondOne=0;
	  int one =1;
	  int i=0;
	  while(n>0){
		  if((n & 1) == 1){
			 posofFirstOne = i;
		  }
		  i++;
		  n>>1;
	  }
	}
	public static void  clearBit(int n,int pos){
		int one =1;
		int inv = ~(one<<pos);
		int n1 = n & inv;
		setBit(n1,pos-1);	
	}
	public static int setBit(int n,int pos){
		int one =1;
		int mask = one<<=pos;
		int x = n|mask;
		System.out.println(x);
		return x;
	}
	public static void main(String[] args) {
		
      nextNum(12);
	}

}
