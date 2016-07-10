package Server;

import java.util.Stack;

public class StackReverse {
	public static void popping(Stack s) {
		if (s.isEmpty())
			return;
		int temp = (int) s.pop();
		popping(s);
		insertAtBottom(s,temp);
	}

	public static void insertAtBottom(Stack s, int temp) {
      if(s.isEmpty())
      {
    	  s.push(temp);
    	  return;
      }
	  int temp1 = (int) s.pop();
      insertAtBottom(s,temp);
      s.push(temp1);
	}

	public static void main(String args[]) {
		Stack s = new Stack();
       s.push(1);
       s.push(2);
       s.push(3);
       popping(s);
        for(int i=0;i<3;i++)
        {
        	System.out.println((int)s.pop());
        }
	}
}
