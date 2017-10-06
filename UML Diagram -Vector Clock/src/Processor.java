import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 * @author Sample
 * @version 1.0
 *
 */
public class Processor implements Observer {
	//TODo : add appropriate visibility indicators to each member variable
    Buffer messageBuffer ;
    Integer id ;
    //TODO: Think through when would you need a list of vector clocks
    VectorClock vc ; //This is the current vector clock 
    List<VectorClock> store;
    Algorithm algo;
    
    /**
     * Initializes the processor with id, children and unexplored lists. Adds himself in the observers list.
     * @param id of the processor
     */
    public Processor(int id, int totalProcesors) {
        messageBuffer = new Buffer();
        this.id = id; 
        this.vc = new VectorClock(totalProcesors);
        this.messageBuffer.addObserver(this);
        this.store = new ArrayList<>();
        this.algo = new Algorithm(this);
    }
    
   
    /**
     * 
     */
    public void sendMessageToMyBuffer(MessageType msgType, Processor sender){
    	messageBuffer.setMessage(new Message(msgType, new VectorClock(sender.getVectorClock())), sender.getID());
    }
    
    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message Message to be sent
     */
    @SuppressWarnings("unchecked")
	public void sendMessgeToProcessor(MessageType msgType, Object P){
    	//TODO: implement 
    	if (msgType == MessageType.COMPUTATION){
    		algo.computeEvent();
    	}
    	if (msgType == MessageType.SEND){
    		algo.sendEvent((Processor)P);
    	}
    }


    /**
     * Gets called when a node receives a message in it buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
    	
    	Message m = ((Buffer)observable).getMessage();
    	if (m.messageType == MessageType.SEND){
    		calculateVectorClocks(m.getVectorClock(), (int)arg);
    	}
    }

    //TODO: Discuss does this method need to return a vector clock? or is void enough.
    public void calculateVectorClocks(VectorClock observable, int id) {
    	//TODO: Implement the logic to check based on the current vector clocks and the vector clock
    	//Hint: Get vector clocks for this processor and message from this processors buffer
    	//invoke methods of VectorClock
    	System.out.println("Send Event received from Processor " + id);
    	this.vc.updateAt(id, observable.vc[id]);
    	algo.computeEvent();

    }
    
    /**
     * Getter Method for ID
     */
    public int getID(){
    	return this.id;
    }
 
    
    /**
     * Get VectorClock
     * 
     */
    public VectorClock getVectorClock(){
    	return this.vc;
    }
    
    public void printProcessorStore(){
    	for (VectorClock V : store){
    		V.printClock();
    	}
    }
}
