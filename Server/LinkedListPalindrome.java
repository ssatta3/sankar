package Server;

import java.util.Scanner;
import java.util.Stack;

public class LinkedListPalindrome {
	class Node {
		int data;
		Node next;
	}
	static Node head;
	void insert(int data, int pos){
		Node temp = new Node();
		temp.data = data;
		if(pos == 1){
			temp.next = head;
			head = temp ;
			return;
		}
		
		Node temp1 = new Node();
		temp1 = head;
		for(int i = 1; i < pos - 1; i++){
			temp1 = temp1.next;
		}
		temp.next = temp1.next;
		temp1.next = temp;
	}
	
	void delete(int pos){
		Node temp = new Node();
		temp = head;
		if(pos == 1){
			temp.next = head;
			return; 
		}
		if(pos < 1 || pos > countNodes())
			return;
		for(int i = 1; i < pos - 1; i++){
			temp = temp.next;
		}
		Node temp1 = temp.next;
		temp.next = temp1.next;
	}
	
	/*boolean isPalindrome(){
		Node fastPtr = new Node();
		Node slowPtr = new Node();
		
		fastPtr = head;
		slowPtr = head;
		
		Stack<Integer> stk = new Stack<>();
		while(fastPtr.next != null || fastPtr != null){
			fastPtr = fastPtr.next;
			if(fastPtr != null){
				
			}
			slowPtr = slowPtr.next;
		}
		
	}*/
	
	int countNodes(){
		Node temp = new Node();
		temp = head;
		int count = 0;
		while(temp.next != null){
			count++;
			temp = temp.next;
		}
		return count;
	}
	
	void printList(){
		Node temp = new Node();
		temp = head;
		
		while(temp != null){
			System.out.println(temp.data);
			temp = temp.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedListPalindrome ll = new LinkedListPalindrome();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int pos = 0;
		for(int i = 0; i < n; i++){
			int data = scan.nextInt();
			ll.insert(data, ++pos);
			ll.printList();
		}
		scan.close();
		ll.delete(6);
		ll.printList();
	}
}