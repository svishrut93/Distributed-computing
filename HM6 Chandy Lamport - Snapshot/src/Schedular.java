
import java.util.*;

public class Schedular implements Runnable{

	Processor P;
	Algorithm algo ;
	
	public Schedular(Processor P, Algorithm algo){
		this.P = P;
		this.algo = algo;
	}
	
	public void run(){
		
		try {
			switch (P.getID()){
			
				case 1:
					algo.executionPlanP1();
					break;
				case 2:
					algo.executionPlanP2();
					break;
				case 3:
					algo.executionPlanP3();
					break;
			
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception " + e.getMessage());
		}
		
	}
	
}