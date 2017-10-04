import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tphadke on 8/29/17.
 */
public class Main {
    Map <Processor, List<Processor> > graph ;

    public  Main(){

        init();

    }

    public static void main ( String args[]){
        Main m = new Main();
        //TODO: Choose a processor as a Root
        System.out.println("Enter ID of root processor (0 to 5)");
        Scanner sc = new Scanner(System.in);
        Processor root = m.selectRootProcessor(sc.nextInt());
        //TODO: Send an initial message Message.M to this processor.
        try{
	        if (root != null){
	        	root.sendMessgeToMyBuffer(Message.M, root);
	        	System.out.println("The root Processor has ID " + root.getID());
	        	root.printSpanningTree();
	        }
	        else
	        	throw new IllegalArgumentException("No such root exists");
        }
        catch (IllegalArgumentException e) {
			// TODO: handle exception
        	System.out.println("Illegal argument found. " + e.getMessage());
		}
        catch (Exception e) {
			// TODO: handle exception
        	System.out.println(e.getMessage());
		}
        
    }

    /**
     * 
     * @param id
     * @return Processor with id as the root processor
     */
    
    private Processor selectRootProcessor(int id){
    	
    	for (Processor P : this.graph.keySet()){
    		if (P.getID() == id)
    			return P;
    	}
    	return null;
    }
    
    /*
     * Main Method to initialize the graph of processors 
     * 
     * */
    @SuppressWarnings("serial")
	public void init(){
        
    	//TODO: Populate the Graph with processors 0,1,2,3...
    	graph = new HashMap<>();
    	Processor P0 = new Processor(0);
    	Processor P1 = new Processor(1);
    	Processor P2 = new Processor(2);
    	Processor P3 = new Processor(3);
    	Processor P4 = new Processor(4);
    	Processor P5 = new Processor(5);
    	P0.setUnexploredChildren(new ArrayList<Processor>(){{add(P1); add(P2); add(P3);}});
    	P1.setUnexploredChildren(new ArrayList<Processor>(){{add(P2); add(P4); add(P0);}});
    	P2.setUnexploredChildren(new ArrayList<Processor>(){{add(P0); add(P1); add(P5);}});
    	P3.setUnexploredChildren(new ArrayList<Processor>(){{add(P0);}});
    	P4.setUnexploredChildren(new ArrayList<Processor>(){{add(P1); add(P5);}});
    	P5.setUnexploredChildren(new ArrayList<Processor>(){{add(P2); add(P4);}});
    	graph.put(P0, P0.getUnexplored());
    	graph.put(P1, P1.getUnexplored());
    	graph.put(P2, P2.getUnexplored());
    	graph.put(P3, P3.getUnexplored());
    	graph.put(P4, P4.getUnexplored());
    	graph.put(P5, P5.getUnexplored());
    	
    }
}

