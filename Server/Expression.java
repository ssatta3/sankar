package Server;


import java.util.*;

public class Expression {
	static String operators = "+-/*"; // List of Operators.
	static int target; // Target Number
	static boolean notfound =true;   // boolean to return when the target is found instead of searching continuously 
	
	// Function to evaluate postfix expressions obtained
	static double evalutePostFix(String exp){
		double res = 0;
		/*if(exp.equals(" 5 5 - 5 / 1 +"));
        	{
        		System.out.println(" failing case");
        	}*/
		Stack<String> list = new Stack<String>();
		double n1=0;    
		double n2=0;     
		String[] s1 = exp.split(" "); 
		for (String s:s1) {
			if (s == " ") {
			} else {
				if (!(s.equals("+")||s.equals("/")||s.equals("*")||s.equals("-"))) {
					list.push(s);
					//          list.printS();
				} else {
					try {
						n1 = Double.parseDouble("" + list.pop());
						n2 = Double.parseDouble("" + list.pop());
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("error");
					}
					switch (s) {
					case "+":
						list.push(Double.toString((n1 + n2)));
						break;
					case "-":
						list.push(Double.toString((n1 - n2)));
						break;
					case "*":
						list.push(Double.toString((n1 * n2)));
						break;
					case "/":
						if(n2==0){return -999999.0;}// for handling divide by 0 case
						list.push(Double.toString((n1 / n2)));
						break;

					default:
						System.out.println("Invalid operator order!");
					}
				}
			}
		}


		res = Double.parseDouble((String) list.pop());

		return res;
	}
	static String postToInfix(String postfix) {
		Stack<String> expr = new Stack<String>();
		Scanner sc = new Scanner(postfix);
		while (sc.hasNext()) {
			String t = sc.next();
			if (operators.indexOf(t) == -1) {
				expr.push(t);
			} else {
				expr.push("(" + expr.pop() + t + expr.pop() + ")");
			}
		}
		return expr.pop();
	}
	static void driver(Integer[] numbers) {
		boolean b = getPostFixExp(numbers,0, "");
		if(b){
			System.out.println("none");
		}
	}

	static boolean getPostFixExp(Integer[] numbers, int countUsed, String eq) {
		if (countUsed >= 2) {
			for (char op : operators.toCharArray()) {
				if(notfound)
					getPostFixExp(numbers, countUsed - 1, eq + " " + op);
			}
		}
		boolean allUsed = true;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] != null) {
				allUsed = false;
				Integer n = numbers[i];
				numbers[i] = null;
				if(notfound)
					getPostFixExp(numbers, countUsed + 1, eq + " " + n);
				numbers[i] = n;
			}
		}
		if (allUsed && countUsed == 1) {

			double res=evalutePostFix(eq);
			if(res==target){
				notfound = false;
				System.out.println(postToInfix(eq));
				return notfound;
			}
		}
		return notfound;
	}
	public static void main(String args[]) {

		//String[] s1 = args.split(" ");
		target = Integer.parseInt(args[0]);
		Integer[] numbers = new Integer[args.length-1];
		for(int i=1;i<args.length;i++){
			numbers[i-1] = Integer.parseInt(args[i]);
		}
		driver(numbers);
	}
}

