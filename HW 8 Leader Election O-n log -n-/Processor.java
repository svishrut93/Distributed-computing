import java.util.Observable;
import java.util.Observer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

/**
 * Performs all the processor related tasks
 *
 * @author Sample
 * @version 1.0
 */
public class Processor implements Observer, Runnable {

	private int id;
	private Processor leader;
	private Processor leftNeighbor;
	private Processor rightNeighbor;
	private Buffer myBuffer;
	private boolean terminated;
	boolean asleep;
	List<Message> replyMessagesFromLeft = Collections.synchronizedList(new CopyOnWriteArrayList<Message>());
	List<Message> replyMessagesFromRight = Collections.synchronizedList(new CopyOnWriteArrayList<Message>());
    
    /**
     * @param id of the processor
     */
    public Processor(int pid) {
    	id = pid;
    	leader = null;
    	terminated = false;
    	asleep = true;
    	myBuffer = new Buffer();
    	myBuffer.addObserver(this); // processor observes its buffer
    }
    
    public void setLeftNeighbor(Processor lNeighbor) {
    	leftNeighbor = lNeighbor;
    }

    public void setRightNeighbor(Processor rNeighbor) {
    	rightNeighbor = rNeighbor;
    }

    public int getID() {
    	return id;
    }
    
    public Buffer getBufferData() {
    	return myBuffer;
    }

    public Processor getLeftNeighbor() {
    	return leftNeighbor;
    }
    
    public Processor getRightNeighbor() {
    	return rightNeighbor;
    }

    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     *
     * @param message Message to be sent
     */
    public void sendMessageToMyBuffer(Message message) {
        myBuffer.saveMessage(message);
    }

    /**
     * Gets called when a Processor receives a message in its buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
        Message message = (Message) arg;
        Processor sender = message.getSender();
        MessageType msgType = message.getMessageType();
        int msgPID = message.getPID();
        int phaseNum = -1;
        int hopCount = -1;
        if (msgType.equals(MessageType.PROBE)) {
        	phaseNum = message.getPhaseNum();
        	hopCount = message.getHopCount();
        } else if (msgType.equals(MessageType.REPLY)) {
            phaseNum = message.getPhaseNum();	
        } 
        
        switch(msgType) {
        case PROBE:
        	if (msgPID == id && leader == null) {
        		terminated = true;
        		leader = this;
        		System.out.println("Leader elected: P" + id + ", P" + id + " sends Terminate message");
        		
        		Message terminateMsg = new Message(MessageType.TERMINATE);
        		terminateMsg.setSender(leader);
        		leftNeighbor.sendMessageToMyBuffer(terminateMsg);
        	}
        	if (msgPID > id && hopCount < Math.pow(2, phaseNum)) {
        		Message probeMsg = new Message(MessageType.PROBE, msgPID, phaseNum, hopCount + 1);
        		probeMsg.setSender(this);
        		if (sender.equals(leftNeighbor)) {
        			rightNeighbor.sendMessageToMyBuffer(probeMsg);
        		} else if (sender.equals(rightNeighbor)) {
        			leftNeighbor.sendMessageToMyBuffer(probeMsg);
        		}
        	} else if (msgPID > id && hopCount >= Math.pow(2, phaseNum)) {
        		Message replyMsg = new Message(MessageType.REPLY, msgPID, phaseNum);
        		replyMsg.setSender(this);
        		if (sender.equals(leftNeighbor)) {
        			leftNeighbor.sendMessageToMyBuffer(replyMsg);
        		} else {
        			rightNeighbor.sendMessageToMyBuffer(replyMsg);
        		}
        	}
        	// if none of the above cases is true, swallow the message
        	break;
        case REPLY:
        	if (sender.equals(leftNeighbor)) {
            	replyMessagesFromLeft.add(message);
            } else if (sender.equals(rightNeighbor)) {
            	replyMessagesFromRight.add(message);
            }
        	if (msgPID != id) {
        		message.setSender(this);
        		if (sender.equals(leftNeighbor)) {
        			rightNeighbor.sendMessageToMyBuffer(message);
        		} else if (sender.equals(rightNeighbor)) {
        			leftNeighbor.sendMessageToMyBuffer(message);
        		}
        	} else {
        		if (sender.equals(leftNeighbor) && alreadyReceivedFromRight(message)) {
        			Message probeMsg = new Message(MessageType.PROBE, id, phaseNum + 1, 1);
        			probeMsg.setSender(this);
        			leftNeighbor.sendMessageToMyBuffer(probeMsg);
        			rightNeighbor.sendMessageToMyBuffer(probeMsg);
        		} else if (sender.equals(rightNeighbor) && alreadyReceivedFromLeft(message)) {
        			Message probeMsg = new Message(MessageType.PROBE, id, phaseNum + 1, 1);
        			probeMsg.setSender(this);
        			leftNeighbor.sendMessageToMyBuffer(probeMsg);
        			rightNeighbor.sendMessageToMyBuffer(probeMsg);
        		}
        	}
        	break;
        case TERMINATE:
    	    leader = sender;
        	terminated = true;
        	if (!leftNeighbor.terminated) {
        		leftNeighbor.sendMessageToMyBuffer(message);
        	}
        }
    }

    public void run() {
		try {
			if (asleep) {
				asleep = false;
				Message message = new Message(MessageType.PROBE, id, 0, 1);
				message.setSender(this);
				leftNeighbor.sendMessageToMyBuffer(message);
				rightNeighbor.sendMessageToMyBuffer(message);
			}
		} catch (Exception ex) {
			System.out.println("Exception while running thread");
			ex.printStackTrace();
		}
	}
    
    /**
     * Announce the processor's leader
     */
    public void announceLeader() {
    	System.out.println("P" + id + ": My leader is P" + leader.getID());
    }
    
    /**
     * Check if a processor has already received the Reply message from the left neighbor
     * @param msg the Reply message
     * @return true if message has already been received, false otherwise
     */
    public boolean alreadyReceivedFromLeft(Message msg) {
    	for (Message m : replyMessagesFromLeft) {
    		if (msg.getMessageType().equals(MessageType.REPLY) && msg.getPID() == m.getPID()
    				&& msg.getPhaseNum() == m.getPhaseNum()) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Check if a processor has already received the Reply message from the right neighbor
     * @param msg the Reply message
     * @return true if message has already been received, false otherwise
     */
    public boolean alreadyReceivedFromRight(Message msg) {
    	for (Message m : replyMessagesFromRight) {
    		if (msg.getMessageType().equals(MessageType.REPLY) && msg.getPID() == m.getPID()
    				&& msg.getPhaseNum() == m.getPhaseNum()) {
    			return true;
    		}
    	}
    	return false;
    }
}