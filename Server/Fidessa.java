package Server;

public class Fidessa {

	
	public static int lastIndex(String s)
	{
		int lastIndex;
		int[] track = new int[150];
		int i=0;
		for( i =0 ; i<s.length();i++)
		{
		int a = s.charAt(i);
		if(65<=a && 90>=a)
		{
			track[a]++;
		}
		if(97<=a&&122>=a)
		{
			if(track[a-32] ==0)
			{
				break;
			}
			else{
				track[a-32]--;
			}
		}
	}
		return i-1;
	}
	public static void main(String[] args) {
           String s = "ABBbbac";
           int i = lastIndex(s);
           System.out.println(i);
	}

}
