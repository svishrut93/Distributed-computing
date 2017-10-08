How to work around with Code

Create an execution Plan for a Processor in a file and save it.
Currently there are only three type of events supported.
1) COMPUTATION EVENT: For a computation event just write C (in CAPITAL) for that Processor
2) SEND EVENT: For a send event write S (in CAPITAL). For example in order to simulate a send event to Processor 2, write S 2.
3) CUT: Send a cut to each processor to calculate the maximal cut.
Once you have created execution plans for each processor, run the main.java file from the src folder.
On execution it will ask you to input the number of processors and the name of the files containing their execution plans.


Since all the processors are simulated to run individually in their own threads, the execution timeline for each Processor may vary. 
The store output will show the vector clocks as they were updated for each processor and it provides intuition of the order in which the execution plan at each processor may occur.

Once an execution stream for each processor is created, the program asks for a cut, respective to which the maximal cut needs to be defined. 
Input the cut with space separated. Example if there are two processors and the store of each processor is as follows: 
Printing Store of Processor 0
1, 0, 
2, 0, 
3, 2, 
4, 2, 
5, 2, 
6, 2, 
Printing Store of Processor 1
0, 1, 
0, 2, 
2, 3, 
2, 4, 
2, 5, 
5, 6, 

Input a cut for example 4 1 or 4 2
