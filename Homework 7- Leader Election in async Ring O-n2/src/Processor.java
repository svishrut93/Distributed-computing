import java.util.Observable;
import java.util.Observer;

public class Processor implements Runnable, Observer{

	private int id;
	private Processor leader, leftNeigh;
	private Buffer messageBuffer ;
	
    /**
     * @param id of the processor
     */
    public Processor(int id, Processor leftNeigh) {
    	
    	this.id = id;
    	this.leader = this;//every processor elects itself as the leader first
    	this.leftNeigh = leftNeigh;
    	this.messageBuffer = new Buffer();
    	this.getMessageBuffer().addObserver(this);
    	
    }

    
    //This is analogous to recieve method.Whenever a message is dropped in its buffer this Pocesssor will respond
    //TODO: implement the logic of receive method here
    //      Hint: Add switch case for each of the conditions given in receive
    public void update(Observable observable, Object arg) throws IllegalArgumentException{

    	if (observable instanceof Buffer){
    		
    		Buffer bf = (Buffer)observable;
    		Message m = bf.getMessage();
    		Processor sender = m.getFrom();

    		switch(m.getMessageType()){
    		
	    		case LEADER:{
	    			
	    			//this is the final leader, receives message after going around the ring
	    			if (this == sender){
	    				Message m1 = new Message(MessageType.TERMINATION);
	    				m1.setFrom(this);
	    				sendMessageToLeftNeigh(m1);
	    				break;
	    			}
	    			
	    			//if the sender's id is more than the processor's id then send the message to left processor //otherwise swallow the message
	    			if (getID() < sender.getID()){
	    				sendMessageToLeftNeigh(m);
	    	    			break;
	    			}
	    			break;
	    			
	    		}
	    		case TERMINATION:{
	    			if (this == sender){
	    				break;
	    			}
	    			else{
	    				//update the leader
	    				setLeader(sender);
		    			sendMessageToLeftNeigh(m);
		    			break;
	    			}
	    		}
    		
    		}
    	
    	}
		
    }
    
    /**
     * THis method send the message m to its left processor
     * 
     * @param m
     */
    public void sendMessageToLeftNeigh(Message m){
    	
    	this.getLeftNeighbour().getMessageBuffer().setMessage(m);
    	
    }
       
    /**
     * Getter Method
     * @return messageBuffer for Processor
     */
    public Buffer getMessageBuffer(){
    	return this.messageBuffer;
    }
    
    public int getID(){
    	return this.id;
    }
    
    public Processor getLeftNeighbour(){
    	return this.leftNeigh;
    }
    
    public void setLeftNeighbour(Processor p){
    	this.leftNeigh = p;
    }
    
    public void setLeader(Processor p){
    	this.leader = p;
    }
    
    public int getLeaderID(){
    	return this.leader.getID();
    }
    
    public Processor getLeader(){
    	return this.leader;
    }
    
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		initLeaderElection();
	}	
	
	
	/**
	 * This method starts the leader election for each thread
	 */
	public void initLeaderElection(){
		
		Message m = new Message(MessageType.LEADER);
		m.setFrom(this);
		sendMessageToLeftNeigh(m);
		
	}
}