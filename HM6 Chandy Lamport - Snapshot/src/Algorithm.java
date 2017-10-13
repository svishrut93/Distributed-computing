/**
 * This is the simulation of a main algorithm that will run on processors P1, P2, P3
 * This could be a banking application, payroll application or any other distributed application
 */
public class Algorithm {

    /**
     * The processors which will participate in a distributed application
     */
    Processor processor1, processor2, processor3;

    public Algorithm(Processor processor1, Processor processor2, Processor processor3) {
        //TODO: Homeork: Initialize processors so that they represent the topology of 3 processor system
    	this.processor1 = processor1;
    	this.processor2 = processor2;
    	this.processor3 = processor3;
    }



    public void executionPlanP1 () throws InterruptedException{
        
    	Message m = new Message(MessageType.ALGORITHM);
    	m.setFrom(processor1);
    	compute(processor1);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(0));
        compute(processor1);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(1));
        compute(processor1);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(0));
        compute(processor1);
/**
 * TODO: Homework: Implement send message from processor1 to different processors. Add a time gap betweeen two different
 *                send events. Add computation events between two diferent sends.
 *      [Hint: Create a loop that kills time, sleep , wait on somevalue etc..]
 *
 */
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(0));
        compute(processor1);
        Thread.sleep(1000);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(1));
        Thread.sleep(3000);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(0));
        compute(processor1);
        Thread.sleep(2000);
        compute(processor1);
        processor1.sendMessgeTo(m,processor1.getOutChannels().get(1));
    }

    // Write hard coded execution plan for processors
    public void executionPlanP2() throws InterruptedException {

    	Message m = new Message(MessageType.ALGORITHM);
    	m.setFrom(processor2);
    	compute(processor2);
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(0));
        compute(processor2);
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(1));
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(1));
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(0));
        compute(processor2);

        processor2.sendMessgeTo(m,processor2.getOutChannels().get(0));
        Thread.sleep(2000);
        compute(processor2);
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(1));
        Thread.sleep(3000);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(0));
        Thread.sleep(1000);
        compute(processor2);
        processor2.sendMessgeTo(m,processor2.getOutChannels().get(1));
        compute(processor2);
    }

    // Write hard coded execution plan for processors
    public void executionPlanP3() throws InterruptedException {

    	Message m = new Message(MessageType.ALGORITHM);
    	m.setFrom(processor3);
    	compute(processor3);
    	processor3.sendMessgeTo(m,processor3.getOutChannels().get(0));
        compute(processor3);
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(1));
        compute(processor3);
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(1));
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(0));
        compute(processor3);

        processor3.sendMessgeTo(m,processor3.getOutChannels().get(0));
        Thread.sleep(2000);
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(1));
        Thread.sleep(3000);
        compute(processor3);
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(0));
        Thread.sleep(500);
        compute(processor3);
        processor3.sendMessgeTo(m,processor3.getOutChannels().get(1));
        compute(processor3);
    }

    /**
     * A dummy computation.
     * @param p
     */
    public void compute(Processor p) {
        System.out.println("Doing some computation on " + p.getID());
    }

   

}