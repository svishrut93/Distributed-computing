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
		filesNames.add("File1");
		filesNames.add("File2");
		
		for (int i = 0; i < n; i++){
			System.out.println("Enter name of File for Processor" + (i+1));
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
		
		for (Processor P : processors){
			System.out.println("Printing Store of Processor " + P.getID());
			P.printProcessorStore();
		}
		String cut = sc.nextLine();
		System.out.println("Enter the cut for the processors");
		cut = sc.nextLine();
		String[] cuts =  cut.split(" ");
		List<Integer> cutsList = new ArrayList<>(Arrays.asList(cuts).stream().map(Integer::parseInt).collect(Collectors.toList()));
		for (Processor P : processors){
			P.sendMessgeToProcessor(MessageType.CUT, cutsList);
		}
		
	}

}