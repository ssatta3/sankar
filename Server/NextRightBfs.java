package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class TreeLinkNode {
	      int val;
	      TreeLinkNode left, right, next;
	      TreeLinkNode(int x) { val = x; }
	  }
public class NextRightBfs {
	static TreeLinkNode prev=null;

	public static int findHeight(TreeLinkNode root){
		if(root == null) {
			return 0;
		}
		int lHeight = findHeight(root.left);
		int rHeight = findHeight(root.right);
		return Integer.max(lHeight,rHeight)+1;
	}
	public static void driver(int height,TreeLinkNode root){
		for(int i=1;i<=height;i++){
			prev = null;
			connect(root,i);
		}
	}
	static HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer,ArrayList<Integer>>();
	public static void verticalPrint(TreeLinkNode root,int hDist){
		
		if(root==null){return;}
		verticalPrint(root.left, hDist-1);
			if(hm.get(hDist)!=null)
			hm.get(hDist).add(root.val);
			else{
				ArrayList<Integer> a = new ArrayList<>();
				a.add(root.val);
				hm.put(hDist,a);
			}
		verticalPrint(root.right, hDist+1);
			
	}

	
	public static void connect(TreeLinkNode root,int level){
		if(root==null){
			return;
		}
		if(level == 1){
			if(prev == null){
				prev = root;
			}
			if(prev!=null){
				System.out.print(root.val);
				
				prev.next = root;
				prev = root;
			}
		}else if(level>1){
		connect(root.left,level-1);
		connect(root.right,level-1);
		}
	}
	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.left.left= new TreeLinkNode(4);
		root.left.right= new TreeLinkNode(5);
		root.right = new TreeLinkNode(3);
		root.right.right = new TreeLinkNode(7);
		root.right.left = new TreeLinkNode(6);
		root.right.right.right = new TreeLinkNode(9);
		root.right.left.right = new TreeLinkNode(8);
		//int height =  findHeight(root);
		//driver(height, root);
		verticalPrint(root,0);
		

	}

}
