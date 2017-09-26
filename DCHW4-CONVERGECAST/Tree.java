
public class Tree {
	
	
	Node root;											//root of the tree (Object of type Node)
	
	static int findMax(Node node)
    {
        if (node == null)
            return Integer.MIN_VALUE;
 
        int temp = node.data;
        
        int leftside = findMax(node.left);             //left subtree recursive call
        int rightside = findMax(node.right);           //right subtree recursive call 
 
        if (leftside > rightside)                       
            temp = leftside;                           
        
        if (rightside > temp)
            temp= rightside;
        
        
        return temp;
    }
	
	public static void main(String args[])
    {
        
		 Node root;
		
		Tree tree1 = new  Tree();
        tree1.root = new Node(2);
        tree1.root.left = new Node(7);
        tree1.root.right = new Node(5);
        tree1.root.left.left = new Node(2);
        tree1.root.left.right = new Node(6);
        tree1.root.right.right= new Node(9);
        tree1.root.right.right.left = new Node(4);
        tree1.root.left.right.left = new Node(5);
        tree1.root.left.right.right = new Node(11);
 
        System.out.println("Highest value inside tree is "+tree1.findMax(tree1.root));
        		
    }
}


