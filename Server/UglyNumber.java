package Server;

public class UglyNumber {
	public static int getNthNumber(int[] primes, int n){
		int[] ugly = new int[n];
		for(int i=0; i<n; i++){
			ugly[i] = Integer.MAX_VALUE;
		}
	    int[] indexes = new int[primes.length];
	    ugly[0]=1;
	    for(int i=1;i<n;i++){
	    	for(int j=0;j<primes.length;j++){
	    		ugly[i] = Integer.min(ugly[i], primes[j]*ugly[indexes[j]]);
	    	}
	    	    for(int j=0;j<primes.length;j++){
	    	if(ugly[i] ==primes[j]*ugly[indexes[j]]){
	    		indexes[j]++;
	    	}
	    }
	    }
	    return ugly[n-1];
	}

	public static void main(String[] args) {
	int[] primes= {2,3,5};
	int x =getNthNumber(primes, 355);
	System.out.println(x);

	}

}
