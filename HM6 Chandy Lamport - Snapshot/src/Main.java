import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tphadke on 9/27/17.
 */
public class Main {

    public static void main(String args[]) {

        //Channels from P3 to P1 and P2 to P1
        Buffer channelP31 = new Buffer("31");
        Buffer channelP21 = new Buffer("21");

        //Channels from P3 to P2 and P1 to P2
        Buffer channelP32 = new Buffer("32"); //source destination
        Buffer channelP12 = new Buffer("12"); //source Dest

        //Channels from P2 to P3 and P1 to P3
        Buffer channelP23 = new Buffer("23");
        Buffer channelP13 = new Buffer("13");


        List<Buffer> inChannelsP1 = new ArrayList<>();
        inChannelsP1.add(channelP31);
        inChannelsP1.add(channelP21);
        List<Buffer> outChannelsP1 = new ArrayList<>();
        outChannelsP1.add(channelP13);
        outChannelsP1.add(channelP12);
        Processor processor1 = new Processor(1, inChannelsP1, outChannelsP1); //Only observes in channels.

        List<Buffer> inChannelsP2 = new ArrayList<>();
        inChannelsP2.add(channelP12);
        inChannelsP2.add(channelP32);
        List<Buffer> outChannelsP2 = new ArrayList<>();
        outChannelsP2.add(channelP21);
        outChannelsP2.add(channelP23);
        Processor processor2 = new Processor(2, inChannelsP2, outChannelsP2); //Only observes in channels.


        List<Buffer> inChannelsP3 = new ArrayList<>();
        inChannelsP3.add(channelP13);
        inChannelsP3.add(channelP23);
        List<Buffer> outChannelsP3 = new ArrayList<>();
        outChannelsP3.add(channelP31);
        outChannelsP3.add(channelP32);
        Processor processor3 = new Processor(3, inChannelsP3, outChannelsP3); //Only observes in channels.

        /**
         * Choose one processor to initialize a snapshot. Please note that any processor has the capability to
         * initiate a snapshot.
         * //TODO: Homework: initiate snapshot
         * [Hint: call the initiateSnapshot method ]
         */
        
        Algorithm algo = new Algorithm(processor1, processor2, processor3);
        
        //execute execution plans for each processor in different threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
			  	Schedular sch1 = new Schedular(processor1, algo);
				executor.submit(sch1);
				Schedular sch2 = new Schedular(processor2, algo);
				executor.submit(sch2);
				Schedular sch3 = new Schedular(processor3, algo);
				executor.submit(sch3);
//				executor.shutdown();
				//initiate snapshot from processor 1
				Thread.sleep(500);
		        processor1.initiateSnapShot();
		        executor.shutdown();
		        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		        if(executor.isShutdown()){
		        	printProcessorSnapShot(processor1);
					printProcessorSnapShot(processor2);
					printProcessorSnapShot(processor3);
				}
		}
		
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

    }
    
    public static void printProcessorSnapShot(Processor P){
    	System.out.println("------------------------Printing Snapshot for Processor P:" +P.getID()+"----------------------------");
    	System.out.println("Number of Algorithms message received untill first marker message = "+ P.getAns());
    	
    	P.channelState.forEach( (channel,listOfMessages)-> {
    		if (!listOfMessages.isEmpty()){
    			System.out.println("Channel C:"+ channel.getLabel());
    			for(Message m: listOfMessages){
    				if (m.getMessageType() == MessageType.ALGORITHM)
    					System.out.println("Message Algorithm received from Processor P:" + m.getFrom().getID());
    			}
    		}
    		
    	});
    	
    	System.out.println("--------------------------------------------------------------------------------------------");
    }


}