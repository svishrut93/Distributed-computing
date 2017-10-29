import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of Processors for the ring topology");
		int n = sc.nextInt();
		
		sc.close();
		
    	List<Processor> processors = new ArrayList<Processor>();
    	initProcessors(processors, n);
    	printTheTopology(processors, n);
    	
        ExecutorService executor = Executors.newFixedThreadPool(n);
        try {
        	for (Processor p : processors) {
        		executor.submit(p);
        	}
        	
        	executor.shutdown();
        	executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        	
        	if (executor.isShutdown()) {
        		for (Processor p : processors) {
        			p.announceLeader();
        		}
        	}
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
    
    /**
     * Initialize all processors
     * @param processors list of processors
     * @param n the total number of processors
     */
    public static void initProcessors(List<Processor> processors, int n) {
    	
    	int id = 1;
    	
    	int i;
    	for (i = 0; i < n; i++) {
    		processors.add(new Processor(id++));
    	}
    	for (i = 0; i < n; i++) {
    		Processor p = processors.get(i);
    		if (p.getID() == n) {
    			p.setRightNeighbor(processors.get(0));
    			p.setLeftNeighbor(processors.get(i-1));
    		} else if (p.getID() == 1) {
    			p.setRightNeighbor(processors.get(i+1));
    			p.setLeftNeighbor(processors.get(n-1));;
    		} else {
    			p.setRightNeighbor(processors.get(i+1));
    			p.setLeftNeighbor(processors.get(i-1));
    		}
    	}
    }
    
    /**
     * Print out the topology of processors
     * @param processors list of processors
     * @param n the total number of processors
     */
    public static void printTheTopology(List<Processor> processors, int n) {
    	
    	System.out.print("Topology: ");
    	Processor tempProcessor = processors.get(0);
    	int i = 0;
    	while(tempProcessor.getRightNeighbor() != null && i <= n) {
    		System.out.print("Processor " + tempProcessor.getID());
    		if (i != n) {
    			System.out.print(" -> ");
    		} else {
    			System.out.println();
    		}
    		tempProcessor = tempProcessor.getRightNeighbor();
    		i++;
    	}
    }
}