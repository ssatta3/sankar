package Server;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Robbery {
	static int max;
	
	public static int rob(TreeNode node)
	{
		if(node==null) return 0;
		else if(node.left==null & node.right!=null){
		 max = maximum(node.val+ rob(node.right.left)+rob(node.right.right),rob(node.right),rob(node.right.right)+rob(node.right.left)+rob(node.left)
		                   ,rob(node.left)+rob(node.right));
		}
		else if(node.right==null & node.left!=null){
			 max = maximum(node.val+rob(node.left.left)+rob(node.left.right),
					          rob(node.left.left)+rob(node.left.right)+rob(node.right),rob(node.left),
					           rob(node.left)+rob(node.right));
		}
		else if(node.right==null && node.left==null) return node.val;
		else max = maximum(node.val+ rob(node.left.left)+rob(node.left.right)+rob(node.right.left)+rob(node.right.right),
				          rob(node.left.left)+ rob(node.left.right)+rob(node.right),rob(node.right.right)+rob(node.right.left)+rob(node.left),
				          rob(node.left)+rob(node.right));
		return max;
	}
	public static int maximum(int a, int b,int c,int d)
	{
		int max1 =a;
		if(b>a) max1=b;
		int max2 = c;
		if(d>c) max2=d;
		int max3 = Integer.max(max1,max2);
		return max3;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new  TreeNode(4);
		//root.right = new TreeNode(500);
		//root.left.left = new TreeNode(400);
		//root.left.right = new TreeNode(1);
		//root.right.right = new TreeNode(1);
		
		int max = rob(root);
		System.out.println(max);
		

	}

}
