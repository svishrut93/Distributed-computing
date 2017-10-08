
<h1>How to work around with Code</h1>
Create an execution Plan for a Processor in a file and save it.</br> 
Currently there are only three type of events supported.</br>
1) COMPUTATION EVENT: For a computation event just write C (in CAPITAL) for that Processor</br>
2) SEND EVENT: For a send event write S <processer_id_of_receiver> (in CAPITAL). For example in order to simulate a send event to Processor 2, write S 2.</br>
3) CUT: Send a cut to each processor to calculate the maximal cut. 

Once you have created execution plans for each processor, run the main.java file from the src folder.</br>
On execution it will ask you to input the number of processors and the name of the files containing their execution plans.</br></br>

Since all the processors are simulated to run individually in their own threads, the execution timeline for each Processor may vary. </br>
The store output will show the vector clocks as they were updated for each processor and it provides intuition of the order in which the execution plan at each processor may occur.</br>

Once an execution stream for each processor is created, the program asks for a cut, respective to which the maximal cut needs to be defined. </br>
Input the cut with space separated. Example if there are two processors and the store of each processor is as follows: </br>
Printing Store of Processor 0</br>
1, 0, </br>
2, 0, </br>
3, 2, </br>
4, 2, </br>
5, 2, </br>
6, 2, </br>
Printing Store of Processor 1</br>
0, 1, </br>
0, 2, </br>
2, 3, </br>
2, 4, </br>
2, 5, </br>
5, 6, </br>

Input a cut for example 4 1 or 4 2


<h1>SAMPLE OUTPUT</h1>

![alt text](https://github.com/svishrut93/cs249-Distributed-computing/blob/master/HW%205B-%20Maximal%20Consistent%20Cut/Sample_Output.PNG)





