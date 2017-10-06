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
	

}


