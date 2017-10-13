<h1>What this is about </h1>

This is an implementation of Chandy Lamports Snap-shot algorithm.
The program works on the following topology of distributed system.

![alt text](https://github.com/svishrut93/Distributed-computing/blob/master/HM6%20Chandy%20Lamport%20-%20Snapshot/Topology.PNG)


<h2> Sample Output </h2>
Doing some computation on 1
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 1
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 1
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 1
Doing some computation on 2
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 2
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 2
Processing Algorithm message....
Recording my registers... Processor: 1
Recording my program counters...Processor: 1
Recording my local variables...Processor: 1
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Processing Algorithm message....
Starting recording Channel 21
Recording my registers... Processor: 3
Recording my program counters...Processor: 3
Recording my local variables...Processor: 3
Starting recording Channel 31
Recording my registers... Processor: 2
Recording my program counters...Processor: 2
Recording my local variables...Processor: 2
Starting recording Channel 23
Channel 31 has stopped recording.
Channel 21 has stopped recording.
Starting recording Channel 12
Channel 12 has stopped recording.
Channel 23 has stopped recording.
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 2
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 3
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Processing Algorithm message....
Doing some computation on 3
Doing some computation on 1
Processing Algorithm message....
Doing some computation on 2
Processing Algorithm message....
Doing some computation on 2
------------------------Printing Snapshot for Processor P:1----------------------------
Number of Algorithms message received untill first marker message = 3
Channel C:21
Message Algorithm received from Processor P:2
Channel C:31
Message Algorithm received from Processor P:3
--------------------------------------------------------------------------------------------
------------------------Printing Snapshot for Processor P:2----------------------------
Number of Algorithms message received untill first marker message = 3
--------------------------------------------------------------------------------------------
------------------------Printing Snapshot for Processor P:3----------------------------
Number of Algorithms message received untill first marker message = 5
--------------------------------------------------------------------------------------------
