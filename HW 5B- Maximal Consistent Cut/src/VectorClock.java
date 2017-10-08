import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class VectorClock implements Comparable<Object>{
	//TODO: read up how to use a comparable and a comparator
	//TODO: Do you see an advantage in making it an Integer ?? 
	int[] vc;
	ReentrantLock lock = new ReentrantLock();

	public VectorClock( int noOfProcesses ) {
		vc = new int [noOfProcesses];
		//TODO : Set all to 0.  Do you need to explicitly initilize to 0
	}
	
	public VectorClock(List<Integer> vectorClock){
		vc = vectorClock.stream().mapToInt(i->i).toArray();
	}
	
	public VectorClock(VectorClock vectorClock) {
		// TODO Auto-generated constructor stub
		this.vc = new int[vectorClock.vc.length];
		int i = 0;
		for (int v : vectorClock.vc){
			this.vc[i] = v;
			i++;
		}
	}

	
	public int compareTo(VectorClock o, int id) {
		
		if (this.vc[id] < o.vc[id]){
			return 1;
		}
		else if (this.vc[id] < o.vc[id])
			return 2;
		else
			return 0;
	}
	/**
	 * Based on a event vector clock will be incremented, changed or updated.
	 * Which index should be updated will be decided by a processor
	 * @param index
	 * @param value
	 */
	public void updateAt(int index, int value){
		
		try {
			lock.lock();
			vc[index]= value;
//			printClock();
		}
		finally{
			lock.unlock();
		}
	}
	
	/**
	 * Method that prints the Clock
	 */
	public void printClock(){
		for (int val : vc){
			System.out.print(val+", ");
		}
		System.out.println();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		VectorClock temp = (VectorClock)o;
		for (int i =0; i < vc.length; i ++){
			if (temp.vc[i] > this.vc[i] )
				return 1;
		}
		return 0;
	}
	
}