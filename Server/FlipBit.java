package Server;

public class FlipBit {
	  
	 public static int getMax(int n){
		Integer x = n;
		 int leftCount=0;
		 int rightCount=0;
		 int zeroCount=0;
		 int searchingFor=0;
		 int maxLength=1;
		 for(int i=0;i<Integer.BYTES*8;i++){
			 searchingFor = n&1;
			 if(searchingFor==1){
				 rightCount++;
			 }
			 else if(searchingFor==0){
				 leftCount = (n&2)==0 ? 0: rightCount;
				 rightCount=0;
			 }
			 maxLength = Math.max(leftCount+rightCount+1,maxLength);
			 n>>=1;
		 }
          return maxLength;			 
		 }
		 
		 
	 

	public static void main(String[] args) {
		 int x = 77;
		 int max =getMax(1775);
		 System.out.print(max);

	}

}
