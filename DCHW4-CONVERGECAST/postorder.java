import java.awt.List;
import java.util.ArrayList;

public class postorder {

	Node root;
	static ArrayList al = new ArrayList<Integer>() ;
	
	public void traversal(Node node)
    {
      
		if(node==null) return;
        
		traversal(node.left); 						//left subtree recursive call        
        traversal(node.right);						//right subtree recursive call
        
       // System.out.print(node.data + " ");
        al.add(node.data);
       
		
    }
	
	public void display(ArrayList <Integer> al) 		//Display the concatenated list 
	{
		System.out.println("Concatenated values");
		for(int i = 0 ; i < al.size(); i ++)
		{
			System.out.print(al.get(i)+" ");
		}
	}
	public static void main(String args[])
    {
        
		 Node root;
		 String ans ; 
		 
		
		postorder tree1 = new  postorder();
        tree1.root = new Node(2);
        tree1.root.left = new Node(7);
        tree1.root.right = new Node(5);
        tree1.root.left.left = new Node(2);
        tree1.root.left.right = new Node(6);
        tree1.root.right.right= new Node(9);
        tree1.root.right.right.left = new Node(4);
        tree1.root.left.right.left = new Node(5);
        tree1.root.left.right.right = new Node(11);
 
        tree1.traversal(tree1.root);
        tree1.display(al);
    }

}
