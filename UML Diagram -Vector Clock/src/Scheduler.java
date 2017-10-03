import java.util.concurrent.TimeUnit;

public class Scheduler {
	
	public static void main(String[] args)
	{
		Processor P0 = new Processor(0,3);
    	Processor P1 = new Processor(1,3);
    	Processor P2 = new Processor(2,3);
    	
    	System.out.println("Main thread starts....");
    	
    	
    	int id0 = 0 ; 
    	int id1=1; 
    	int id2= 2; 
    	
    	Thread t0 = new Thread(new tasker(P0,P1,P2,id0));
    	Thread t1 = new Thread(new tasker(P0,P1,P2,id1));
    	Thread t2 = new Thread(new tasker(P0,P1,P2,id2));
    	t0.start();
    	t1.start();
    	t2.start();

	}
}


class tasker implements Runnable{
	
	private Processor p1;
	private Processor p2;
	private Processor p3;
	private int id ; 
	
	public void run()
	{
		System.out.println("Sched run method");
		if(this.id==0)
		{
			this.executionPlanForP0(p1, p2, p3);
		}
		else if (this.id==1)
		{
			this.executionPlanForP1(p1, p2, p3);
		}
		else
		{
			this.executionPlanForP2(p1, p2, p3);
		}
				
		
	}
	
	public tasker (Processor A, Processor B, Processor C, int id)
	{
		this.p1=A;
		this.p2=B;
		this.p3=C; 
		this.id = id ; 
		System.out.println("Sched method");
		
	}
	
	public void executionPlanForP0(Processor A, Processor B, Processor C)
	{
		
		System.out.println("State of vc b4 execution");
		for(int i = 0 ; i < 3 ; i ++)
		{
			System.out.println(A.vc.vc[i]);
		}
		
		
		A.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, A.vc) , A )  ; 
		A.sendMessgeToProcessor(new Message(MessageType.SEND, A.vc),   B  );
		// A.sendMessgeToProcessor(new Message(MessageType.SEND,A.vc),C);
		 //A.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, A.vc) , A )  ; 
		 //Receives
		 //A.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, A.vc) , A )  ; 
		
		 System.out.println("this is plan of P0 ");

		 
	}
	

	public void executionPlanForP1(Processor A, Processor B, Processor C) 
	{
		//recieve event 
		//receive event 
		//B.sendMessgeToProcessor (new Message(MessageType.COMPUTATION, B.vc) , C );
		//receieve event
		 System.out.println("this is plan of P1");
		
	}
	public void executionPlanForP2(Processor A, Processor B, Processor C) 
	{
		
		//C.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, C.vc) , C )  ; 
		//C.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, C.vc) , C )  ; 
		//C.sendMessgeToProcessor(  new Message(MessageType.SEND, C.vc) , B )  ;
		//recieve
		//C.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, C.vc) , A )  ; 
		//recieve
		//C.sendMessgeToProcessor(  new Message(MessageType.COMPUTATION, C.vc) , C )  ; 
		
		System.out.println("this is plan of P2 ");
		
	}
	
	
}
