
public class Message {
	MessageType messageType;
	VectorClock vc;
	
	
	public Message(MessageType mt, VectorClock vc) {
		this.messageType=mt;
		this.vc = vc;
	}
	
	public VectorClock getVectorClock(){
		return this.vc;
	}
	
}
		
		
		
		
		
}



