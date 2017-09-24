

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Processor implements Observer {
    //Each processsor has a message Buffer to store messages
    private Buffer messageBuffer ;
    private Integer id ;
    private List<Processor> children ;
    private Processor parent;

    //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
    private List<Processor> unexplored ;

    public Processor(int id) {
        messageBuffer = new Buffer();
        this.id = id; //This is an invalid value. Since only +ve values are acceptable as processor Ids.
        children = new ArrayList<>();
        //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
        unexplored = new ArrayList<>();
        parent = null;
        //Each processor is observing itself;
        messageBuffer.addObserver(this);
    }

    //This method will only be used by the Processor
    private Processor removeFromUnexplored(){
        //TODO: implement removing one processor from the list of Children
    	return this.unexplored.remove(0);
    }

    //This method will add a message to this processors buffer.
    //Other processors will invoke this method to send a message to this Processor
    public void sendMessgeToMyBuffer(Message message){
        messageBuffer.setMessage(message, this);
    }


    //This is analogous to recieve method.Whenever a message is dropped in its buffer this Pocesssor will respond
    //TODO: implement the logic of receive method here
    //      Hint: Add switch case for each of the conditions given in receive
    public void update(Observable observable, Object arg) throws IllegalArgumentException{

		if (observable instanceof Buffer){
			
			switch(((Buffer) observable).getMessage()){
			
			case M:	
				if (parent == null){
					parent = (Processor) arg;
					this.unexplored.remove((Processor)arg);
					explore();
				}
				else {
					messageBuffer.deleteObservers();
					messageBuffer.addObserver((Processor)arg);
					messageBuffer.setMessage(Message.ALREADY, this);
				}
				break;
			
			case PARENT:
				children.add((Processor)arg);
				explore();
				break;
				
			case ALREADY:
				explore();
				break;
				
			default:
				throw new IllegalArgumentException("Invalid Message Type Received to Processor: " + this.getID() + " from Processor: " + ((Processor) arg).getID());
			}
			
		}

    }
    
    public void setUnexploredChildren(List<Processor> list){
    	this.unexplored = list;
    }

    private void explore(){
        //TODO: implement this method.
    	if (unexplored.size() > 0){
    		Processor child = removeFromUnexplored();
    		messageBuffer.deleteObservers();
    		messageBuffer.addObserver(child);
    		messageBuffer.setMessage(Message.M, this);
    	}
    	else{
    		if (parent != this){
    			messageBuffer.deleteObservers();
    			messageBuffer.addObserver(parent);
    			messageBuffer.setMessage(Message.PARENT, this);
    		}
    	}
    }
    
    public int getID(){
    	return this.id;
    }
    
    public List<Processor> getUnexplored(){
    	return this.unexplored;
    }
    
    public Buffer getMessageBuffer(){
    	return this.messageBuffer;
    }
    
    public void printSpanningTree(){
    	for (Processor child : children){
    		System.out.println(" Processor: " + this.getID() + " has child " + child.getID());
    		child.printSpanningTree();
    	}
    }
}
