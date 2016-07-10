package Server;

public class Divide {


	public static int div(int divident, int divisor){
		 int divsr;
	     int i=0;
		 int total;
		 int quotient=0;
		divsr = divisor;
		total = 0;
		while(total<divident){
		while(divisor<=divident-total){
			divisor<<=1;
			i++;
		}
		    divisor>>=1;
			i--;
			total += divisor;
			divisor = divsr;
			quotient += Math.pow(2, i);
			i=0;
		}
		return quotient;
	}
	public static void main(String[] args) {
		int i = div(29478953,7858);
		System.out.println(i);

	}

}
