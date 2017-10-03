
import java.util.List;

public class Algorithm {
	int noOfProcessors;
	//Processor p0, p1, p2; // Assume there are three processors.
	Processor PA;

	public Algorithm(Processor P) {
		super();
		//this.noOfProcessors = 3;
		// TODO : initialize all the processors
		
		// p1=
		// p2 =
	}

	// Write hard coded execution plan for processors
	public void executionPlanForP0() {
		// TODO: call events on P0 for compute.
		// Call send events to send message
		// Call receive messages
		

	}

	// Write hard coded execution plan for processors
	public void executionPlanForP1() {
		// TODO: call events on P0 for compute.
		// Call send events to send message
		// Call receive messages

	}

	// Write hard coded execution plan for processors
	public void executionPlanForP2() {
		// TODO: call events on P0 for compute.
		// Call send events to send message
		// Call receive messages

	}
	/**
	 * 
	 * @param p
	 * @param computeMessage
	 */
	public void compute(Processor p, Message computeMessage) {
		// TODO: implement. What will be the value of vector clock passed to
		// this message?
		p.sendMessgeToMyBuffer(computeMessage);
	}
	
	
	public void executeComputationEvent(Processor P) //update value of the receivers vector clock 
	{
		System.out.println("Computation event of Processor : "+P.id);
		
		
		int changedValue = P.getVC().vc[P.getID()]+1;
		P.vc.updateAt(P.getID(), changedValue);
		//VectorClock newVC = new VectorClock(P.getVC());
	}
	
	public void executeSendEvent(Processor P) // update value of both sender's and receiver's vector clock 
	{
		System.out.println("Send event of Procesor : "+ P.id);
		this.executeComputationEvent(P);
		P.messageBuffer.setMessage(new Message(MessageType.SEND, P.vc),P);
	}

	
	
	public void send(Processor to, Message m) {
		// TODO: implement. What will be the value of vector clock passed to
		// this message?

	}

}

