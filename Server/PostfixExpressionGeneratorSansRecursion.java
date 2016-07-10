package Server;


	import java.util.*;

	public class PostfixExpressionGeneratorSansRecursion {
	    static String operators = "+-/*";

	    static String translate(String postfix) {
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

	    static void brute(Integer[] numbers, int stackHeight, String eq) {
	        if (stackHeight >= 2) {
	            for (char op : operators.toCharArray()) {
	                brute(numbers, stackHeight - 1, eq + " " + op);
	            }
	        }
	        boolean allUsedUp = true;
	        for (int i = 0; i < numbers.length; i++) {
	            if (numbers[i] != null) {
	                allUsedUp = false;
	                Integer n = numbers[i];
	                numbers[i] = null;
	                brute(numbers, stackHeight + 1, eq + " " + n);
	                numbers[i] = n;
	            }
	        }
	        if (allUsedUp && stackHeight == 1) {
	            System.out.println(eq + " === " + translate(eq));
	        }
	    }
	    static void expression(Integer... numbers) {
	        brute(numbers, 0, "");
	    }

	    public static void main(String args[]) {
	        expression(1, 2, 3);
	    }
	}
