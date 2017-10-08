import java.util.List;

public class Algorithm {
	int noOfProcessors;
	Processor P; 
	
	/**
	 * 
	 * @param P
	 */
	public Algorithm(Processor P) {
		this.noOfProcessors = 3;
		this.P = P;
	}

	/**
	 * 
	 * 
	 * @param computeMessage
	 */
	public void computeEvent() {
		System.out.println("In Computation of Processor " + P.getID());
		int newValue = P.getVectorClock().vc[P.getID()]+1;
		P.vc.updateAt(P.getID(), newValue);
		VectorClock newVC = new VectorClock(P.getVectorClock());
		P.store.add(newVC);
	}

	/**
	 * 
	 * @param to
	 */
	public void sendEvent(Processor to) {
		System.out.println("In Send Event of Processor " + P.getID());
		computeEvent();
		to.sendMessageToMyBuffer(MessageType.SEND, P);
	}
	
	
	/**
	 * 
	 * @param cuts
	 */
	public void calculateMaximumCut(List<Integer> cuts){
		int id = P.getID();
		VectorClock vectorClockFromCuts = new VectorClock(cuts);
		boolean status = false;
		int index = cuts.get(id);
		VectorClock temp;
		while(!status && index >= 0){
			temp = P.store.get(index); 
			int returnVal = vectorClockFromCuts.compareTo(temp);
			if (returnVal == 0){
				status = true;
				System.out.println("Maximum Consistent Cut at Processor " + P.getID() + " = ");
				temp.printClock();
			}
			index--;
		}
		if(!status)
			System.out.println("No Consistent Cut for Processor " + P.getID() + " exists.");
	}

}