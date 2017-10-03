


public class VectorClock implements Comparable<VectorClock>{
	//TODO: read up how to use a comparable and a comparator
	//TODO: Do you see an advantage in making it an Integer ?? 
	int[] vc;

	public VectorClock( int noOfProcesses ) {
		vc = new int [noOfProcesses];
		int i = 0 ; 
		for(int v : vc)
		{
			this.vc[i]= v; 
			i++;
		}
		//TODO : Set all to 0.  Do you need to explicitly initilize to 0
	}

	public int compareTo(VectorClock o, int id ) {
		// TODO implement a compare to method that will compare two vector clocks
		//What if the definition of equality of two vector clocks
		//return -1 if 
		//return 0 if 
		//return 1 if 
		//return 2 if not comparable
		if(this.vc[id] < o.vc[id])
		{
			return 1 ; 
		}
		else if (this.vc[id]<o.vc[id])
		{
			return 2 ;
		}
		else
		{
			return 0 ; 
		}
	}
	/**
	 * Based on a event vector clock will be incremented, changed or updated.
	 * Which index should be updated will be decided by a processor
	 * @param index
	 * @param value
	 */
	public void updateAt(int index, int value){
		//TODO : Apply Vector clock algorithm 
		
		System.out.println("Entered here ");
		vc[index]= value;
		
		System.out.println("State of processor "+this + "after execution");
		for(int i = 0 ; i < 3 ; i ++)
		{
			System.out.println(this.vc[i]);
		}
			
	}

	@Override
	public int compareTo(VectorClock o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
