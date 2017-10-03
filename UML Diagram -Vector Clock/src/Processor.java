import java.util.Observable;
import java.util.Observer;


public class Processor implements Observer {
	
	
	//TODo : add appropriate visibility indicators to each member variable
    Buffer messageBuffer ;
    Integer id ;
    Algorithm algo;
   
    private Processor reciever;
    //TODO: Think through when would you need a list of vector clocks
    VectorClock vc ; //This is the current vector clock 
    
    /**
     * Initializes the processor with id, children and unexplored lists. Adds himself in the observers list.
     * @param id of the processor
     */
    public Processor(int id, int totalProcesors) {
        messageBuffer = new Buffer();
        this.id = id; 
        this.vc=new VectorClock(totalProcesors);
        this.algo=new Algorithm(this);
        messageBuffer.addObserver(this);
        System.out.println("Processor Created");
    }
    
    
    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message Message to be sent
     */
    public void sendMessgeToMyBuffer(Message message)
    {
    	//TODO: implement
        //messageBuffer.setMessage(message, this);

    }
    
    public void sendMessgeToProcessor(Message message,Processor random) //random = reciever
    {
        
    	if(message.messageType.equals(MessageType.COMPUTATION))
    		algo.executeComputationEvent(random);
    	
    	else
    		algo.executeSendEvent(random);
    

    	//TODO: implement 
    }
    
    


    /**
     * Gets called when a node receives a message in it buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
    	System.out.println("Reached this point ");
    	calculateVectorClocks(observable, arg);
    }

    //TODO: Discuss does this method need to return a vector clock? or is void enough.
   
    
    public void calculateVectorClocks(Observable observable, Object arg) {
    	//TODO: Implement the logic to check based on the current vector clocks and the vector clock
    	//Hint: Get vector clocks for this processor and message from this processors buffer
    	//invoke methods of VectorClock
    	
    	if (observable instanceof Buffer){
			
    		switch(( (Buffer) observable).getMessage().messageType)
    		{
    		case COMPUTATION :   //dosent matter if receiver or sender
    			
    			reciever = (Processor)arg ;
    			reciever.algo.executeComputationEvent(reciever);
    			
   
    		case SEND:
    			
    			reciever = (Processor) arg;
    			sender.algo.executeSendEvent(reciever);
			
    				
    		}
		} 	
    	
    }
    
    
    public int getID()
    {
    	return this.id;
    }
	
    
    public VectorClock getVC()
    {
    	return this.vc;
    }
	
}