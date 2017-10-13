import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ChannelRecorder extends Thread{

	Map<Buffer, List<Message>> channelState = null;
	Buffer channel = null;
	int startingIndex;
	int lastIndex;
		
	public ChannelRecorder(Buffer channel, Map<Buffer, List<Message>> channelState){
		this.channelState = channelState;
		this.channel = channel;
	}
	
	 public void recordChannel() {
	        //Here print the value stored in the inChannels to stdout or file
	        //TODO:Homework: Channel will have messages from before a marker has arrived. Record messages only after a
	        //               marker has arrived.
	        //               [hint: Use the getTotalMessageCount () method to get the messages received so far.
	        startingIndex = channel.getTotalMessageCount()-1;
	        int currentIndex = startingIndex;
	        List<Message> recordedMessagesSinceMarker = new ArrayList<>();
	            //TODO: Homework: Record messages
	            // [Hint: Get the array that is storing the messages from the channel. Remember that the Buffer class
	            // has a member     private List<Message> messages;  which stores all the messages received.
	            // When a marker has arrived sample this List of messages and copy only those messages that
	            // are received since the marker arrived. Copy these messages into recordedMessagesSinceMarker
	            // and put it in the channelState map.
	            //
	            // ]
	        try{
	        	while(!Thread.currentThread().isInterrupted()){
			        	if (channel.getTotalMessageCount() > currentIndex+1){
			        		currentIndex++;
			        		recordedMessagesSinceMarker.add(channel.getMessage(currentIndex));
			        		channelState.put(channel, recordedMessagesSinceMarker);
			        	}
			        }
	        }
	        finally {
	        	System.out.println("Channel " + channel.getLabel() + " has stopped recording.");
			}
	        

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Starting recording Channel " + channel.getLabel());
		recordChannel();
	}

	
	public void stopRecording(){
		this.interrupt();	
	}
	
}