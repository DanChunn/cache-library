# cache-library
An in-memory cache for Java.

A UML diagram of the cache and available algorithms are provided in the root folder.
Test cases can be found in the tests folder.
More test examples can be found in the examples folder.

#### Example Use:

    	//An MRU policy instance is created.
		Policy<Integer> policy1 = new PolicyMRU<>();
		
		//A cache is created with 3 sets of size 2 each.
		SetAssociativeCache<Integer, Character> sac1 = new SetAssociativeCache<>(2,3,policy1);
		
		sac1.put(0, 'a');
		sac1.put(1, 'b');
		sac1.put(2, 'c');
		sac1.put(3, 'd'); 
		sac1.put(4, 'e'); 
		sac1.put(5, 'f'); 
		sac1.put(6, 'g'); //full set, replaces (3,'d') 
		sac1.put(1, 'z'); //overwrites (1, 'b') to (1,'z') and is now MRU within set
		sac1.put(7, 'y'); //full set, replaces (1, 'z')
		sac1.get(2);      //(2,'c') is now MRU within set
		sac1.put(8, 'w'); //full set, replaces (2,'c')
		sac1.get(1);      //returns null   
		sac1.get(2);      //returns null
		
		System.out.println();
		for(int i = 0; i <= 8; i++){
			System.out.println(i + " : " + sac1.get(i));
		}
		
		sac1.print();
		
		//printed statements
		0 : a
		1 : null
		2 : null
		3 : null
		4 : e
		5 : f
		6 : g
		7 : y
		8 : w
		-----
		N:2 Sets:3

		Index:0 (0:a)  (6:g) 
		Index:1 (4:e)  (7:y) 
		Index:2 (5:f)  (8:w) 

#### Current Features:
  - MRU, LRU policies
  - Interface for custom cache replacement policies
  
#### Primary Technology used in this application:
  - Java
  - Eclipse



