package Server;
public class Lloddevenshift{
	 static LinkNode start;
	 static class LinkNode {
		 int data;
		 LinkNode next;

		 LinkNode(int data){
		 	this.data = data;
		 	next = null;
		 }
		 }
public LinkNode shift(LinkNode head)
{
	LinkNode temp2 = head.next;
	LinkNode temp3 = head.next;
	LinkNode temp1 = head;
	LinkNode temp4;
	LinkNode temp5;
	while(temp2!=null&&temp1.next.next!=null)
	{
	temp4=temp1.next.next;
	temp1.next =temp1.next.next;
	temp1=temp4;
	temp5 = temp2.next.next;
	temp2.next=temp2.next.next;
	temp2=temp5;
	}
	temp1.next = temp3;
	return head;
}

void printList(LinkNode head){
	LinkNode temp = head;
	while(temp!=null){
		System.out.println(temp.data);
		temp = temp.next;
	}
}
public static void main(String[] args){
	Lloddevenshift list = new Lloddevenshift();
	list.start = new LinkNode(5);
	list.start.next = new LinkNode(6);
	list.start.next.next = new LinkNode(7);
	list.start.next.next.next = new LinkNode(3);
	list.start.next.next.next.next = new LinkNode(1);
	//list.start.next.next.next.next.next = new LinkNode(8);
	LinkNode printvar = list.shift(start);
	list.printList(printvar);
}
}