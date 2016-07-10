package Server;

import java.util.Stack;

public class NonRecursive {
	static int max=0;
	static int i=0;
public static void inOrderTraversal(BinaryTree root)
    {
	Stack s = new Stack();
	s.add(root);
	while(!s.isEmpty())
	{
		BinaryTree temp = (BinaryTree) s.pop();
		//System.out.println(temp.data);
		if(temp.right!=null)
		{
			s.push(temp.right);
		}
		if(temp.left!=null){
			s.push(temp.left);
		}
		
	}
	}
public static int inOrderRecursive(BinaryTree root)
{
	
	
  if(root==null)
  {
	  return 0;
  }
 // System.out.println(root.data);
  if(root.left==null && root.right==null)
  {
	  if(i>max) {
		  max=i;
		  }
	  i=i-1;
  }
  i=i+1;
  inOrderRecursive(root.left);
  //System.out.println(root.data);
  inOrderRecursive(root.right);
 return max;
}

	public static void main(String[] args) {	
       BinaryTree root = new BinaryTree(1);
       root.right = new BinaryTree(3);
       root.left = new BinaryTree(2);
       root.right.left = new BinaryTree(6);
       root.right.right = new BinaryTree(7);
       root.left.right=new BinaryTree(8);
       root.left.left=new BinaryTree(8);
       root.left.left.left=new BinaryTree(8);
       root.left.right.left=new BinaryTree(8);
       root.left.right.left.left = new BinaryTree(9);
       root.left.right.left.left.left = new BinaryTree(10); 
       root.left.right.left.right = new BinaryTree(11);
       //root.left.right.left.right.right = new BinaryTree(11);
      int max= inOrderRecursive(root);
      System.out.println(max);
       //inOrderTraversal(root);
	}
}
class BinaryTree
   {
    int data;
    BinaryTree left;
    BinaryTree right;
   BinaryTree(int data)
   {
	   this.data= data;
	   left = null;
	   right = null;
   }
   
	}
