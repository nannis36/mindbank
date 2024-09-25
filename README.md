The main class to run the longest path algorithm is the class "Solution".

The program reads user inputs from System.in and expects the input to have the following format:

int n: The first line is the number of graph edges to construct. 
    
The next n lines are pairs of ints representing a "from" vertex id and a "to" vertex id separated by a single space. example:
   
	1 2
    
int id: The final line is a single integer representing a vertex id to run the longest path algorithm from **followed by a newline**. 
(Note: the provided example needs an extra blank line at the end to be considered correct)

Example input:
   
	10
    
	0 1
   
	0 2
   
	1 3
   
	1 2
   
	2 4
   
	2 5
   
	2 3
   
	3 5
   
	3 4
   
	4 5
   
	1


      			

Note: if copying and pasting inputs, such as the example above, be sure to copy the blank line below the final vertex id.
Whitespace surrounding inputs shouldn't affect the operation of the program, but it is crucial that integer pairs have exactly 1 space between them.

The output from the algorithm is provided by System.out after all inputs are received. An example from the above input is provided below:
    
    Longest path from vertex 1 is 4 steps to vertex 5
    1 -> 2 -> 3 -> 4 -> 5
