import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Enter number of Processors");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		List<String> filesNames = new ArrayList<>();
		
		for (int i = 0; i < n; i++){
			System.out.println("Enter name of File " + (i+1));
			filesNames.add(sc.next());
		}
		
		List<Processor> processors = new ArrayList<>();
		for (int i = 0; i < n; i++){
			processors.add(new Processor(i, n));
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(n);
		
		try {
			for (int i = 0 ; i < n; i++){
				Schedular sch = new Schedular(filesNames.get(i), processors.get(i), processors);
				executor.submit(sch);
			}
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		//Printing the store to show the execution timeline of each Processor
		for (Processor P : processors){
			System.out.println("Printing Store of Processor " + P.getID());
			P.printProcessorStore();
		}
		
		
	}

}
