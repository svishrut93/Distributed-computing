<h1>What this is about </h1>

This is an implementation of Chandy Lamports Snap-shot algorithm.
The program works on the following topology of distributed system.

![alt text](https://github.com/svishrut93/Distributed-computing/blob/master/HM6%20Chandy%20Lamport%20-%20Snapshot/Topology.PNG)


<h2> Sample Output </h2>
Doing some computation on 1<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Doing some computation on 2<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Recording my registers... Processor: 1<br>
Recording my program counters...Processor: 1<br>
Recording my local variables...Processor: 1<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Starting recording Channel 21<br>
Recording my registers... Processor: 3<br>
Recording my program counters...Processor: 3<br>
Recording my local variables...Processor: 3<br>
Starting recording Channel 31<br>
Recording my registers... Processor: 2<br>
Recording my program counters...Processor: 2<br>
Recording my local variables...Processor: 2<br>
Starting recording Channel 23<br>
Channel 31 has stopped recording.<br>
Channel 21 has stopped recording.<br>
Starting recording Channel 12<br>
Channel 12 has stopped recording.<br>
Channel 23 has stopped recording.<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Processing Algorithm message....<br>
Doing some computation on 3<br>
Doing some computation on 1<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
Processing Algorithm message....<br>
Doing some computation on 2<br>
------------------------Printing Snapshot for Processor P:1----------------------------<br>
Number of Algorithms message received untill first marker message = 3<br>
Channel C:21<br>
Message Algorithm received from Processor P:2<br>
Channel C:31<br>
Message Algorithm received from Processor P:3<br>
--------------------------------------------------------------------------------------------<br>
------------------------Printing Snapshot for Processor P:2----------------------------<br>
Number of Algorithms message received untill first marker message = 3<br>
--------------------------------------------------------------------------------------------<br>
------------------------Printing Snapshot for Processor P:3----------------------------<br>
Number of Algorithms message received untill first marker message = 5<br>
--------------------------------------------------------------------------------------------<br>
<br>
