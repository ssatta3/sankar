package Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class CoinJam 
{
	private static int n;
	private static int j;
	private static int validJamCoinsGotSofar=0;
	public static void main(String[] args) throws Exception
	{
		BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.parseInt(ip.readLine());
		String input = ip.readLine();
		n = Integer.parseInt(input.split(" ")[0]);
		j = Integer.parseInt(input.split(" ")[1]);
		//j =200;
		char[] jamCoin_string = new char[n]; 
		System.out.println("Case #1:");
		getAllValidJamCoins(0,jamCoin_string);
		//isValidJamCoin("111100");
		
		
		/*for(int base=2; base<=10; base++)
		{
			System.out.println(getNumInBase("111100", base) + " " + isPrime(getNumInBase("111100", base)));
		}*/
		
	}
	
	 public static void getAllValidJamCoins(int len, char[] jamCoin)
	 {
	    if(len>n-1)
	    {
	    	String currJamCoin = new String(jamCoin);
	    	if(jamCoin[0]=='1' & jamCoin[n-1]=='1')
		    	if(isValidJamCoin(currJamCoin))
		    	{
		    		validJamCoinsGotSofar++;
		    	}
	    }
	    else
	    {
	    	if(validJamCoinsGotSofar==j)
	    		return;
	    	jamCoin[len]='1';
	    	getAllValidJamCoins(len+1, jamCoin);
	    	
	    	jamCoin[len]='0';
		    getAllValidJamCoins(len+1, jamCoin);
	    	
	    	
	    }
	}
	 
	public static boolean isValidJamCoin(String jamCoin)
	{
		BigInteger[] numInBase = new BigInteger[9];
		BigInteger[] divisorInBase = new BigInteger[9];
		BigInteger k = BigInteger.valueOf(-1);
		int currBase=0;
		int base;
		for( base=2; base<=10; base++)
		{	
			currBase= base-2;
			numInBase[currBase] = getNumInBase(jamCoin, base);
			// taking -1 as divisor if numver is prime
			divisorInBase[currBase] = isPrime(numInBase[currBase]);
			if(divisorInBase[currBase]==k)
			{
				return false;
			}
		}
		if(base==11)
		{
			System.out.print(jamCoin );
			for (BigInteger i : divisorInBase) 
			{
				System.out.print(" "+i);
			}
			System.out.println();
			return true;
		}
		return false;
	}
	
	public static BigInteger isPrime(BigInteger num)
	{
		BigInteger divisor=BigInteger.valueOf(-1);
		BigInteger i= BigInteger.valueOf(2);
		BigInteger k = BigInteger.valueOf(0);
		while(i.compareTo(num)==-1)
		{
			if(num.mod(BigInteger.valueOf(6)).equals(1)|| num.mod(BigInteger.valueOf(6)).equals(5)) break;
			if(num.mod(i).equals(k))
			{
				divisor=i;
				break;
			}
			i= i.add(BigInteger.valueOf(1));
		}
		
		return divisor;
	}
	
	public static BigInteger getNumInBase(String jamCoin, int base)
	{
		// generating a number in base
		BigInteger num= BigInteger.valueOf(0);
		int digit=0;
		for(int i =0 ; i<jamCoin.length(); i++)
		{
			int currIndex = jamCoin.length() - (i+1);
			digit = jamCoin.charAt(currIndex)=='0' ? 0 : 1;
			num = num.add(BigInteger.valueOf((long) (digit*Math.pow(base, i))));
		}
		return num;
	}
}
