import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of Processors for the ring topology");
		int n = sc.nextInt();
		
		List<Processor> processors = new ArrayList<>();
		
		init(processors,n);
		
		printTopology(processors);
		
		ExecutorService executor = Executors.newFixedThreadPool(n);
		
		try {
			for (int i = 0 ; i < n; i++){
				executor.submit(processors.get(i));
			}
			executor.shutdown();
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		printLeaders(processors);
		
	}
	
	public static void printLeaders(List<Processor> processors){
		//All leaders were already verified
		int leaderID = processors.size();
		//this loop verifies that every processor has the correct leader elected to it
		for (Processor p: processors){
			if (p.getLeaderID()  != leaderID){
				System.out.println("Processor P:" +p.getID()+" does not have the correct leader");
			}
		}
		System.out.println("Leader Elected P:" + leaderID);
	}
	
	public static void printTopology(List<Processor> processors){
		System.out.println("Printing ring topology");
		Processor p = processors.get(0);
		Processor start = p;
		System.out.print("P:"+p.getID() + " --> ");
		p = p.getLeftNeighbour();
		while(p != start){
			System.out.print("P:"+p.getID() + " --> ");
			p = p.getLeftNeighbour();
		}
		System.out.print("P:"+p.getID());
		System.out.println();
	}
	
	public static void init(List<Processor> processors, int n){
		int id =1;
		
		for(int i = 0; i < n; i++){
			processors.add(new Processor(id++,null));
		}
		
		for(int i =0; i < n; i++){
			Processor p = processors.get(i);
			if (p.getID() == n){
				p.setLeftNeighbour(processors.get(0));
			}
			else{
				p.setLeftNeighbour(processors.get(i+1));
			}
		}
		
	}

}