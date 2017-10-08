import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Schedular implements Runnable{

	String fileName;
	List<Processor> processors;
	Processor P;
	
	public Schedular(String fn, Processor P, List<Processor> processors){
		this.fileName = fn;
		this.P = P;
		this.processors = processors;
	}
	
	public void run(){
		String line = "";
		 try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = new FileReader(fileName);
	
	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	            while((line = bufferedReader.readLine()) != null) {
	            	String threadName = Thread.currentThread().getName();
//		            System.out.println("Thread Name = "+ threadName);
		            if (Objects.equals(line, "C")){
	            		P.sendMessgeToProcessor(MessageType.COMPUTATION, null);
	            	}
		            if(Objects.equals(line.charAt(0), 'S')){
		            	int procId = Character.getNumericValue(line.charAt(line.length()-1));
		            	Processor receiver = processors.get(procId);
		            	P.sendMessgeToProcessor(MessageType.SEND, receiver);
		            }
	            }   
	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                  
	        }
	}
	
}