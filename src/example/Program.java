package example;

import policy.*;
import sac.SetAssociativeCache;


/** 
 * An example program on how to create your own Set Associative Cache.
 * */
public class Program {
    public static void main(String[] args) {
    	
    	//A policy instance is created.
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
		sac1.put(7, 'y'); //full set, replaces (1, 'b')
		sac1.get(2);      //(2,'c') is now MRU within set
		sac1.put(8, 'w'); //full set, replaces (2,'a')
		
		System.out.println();
		for(int i = 0; i <= 8; i++){
			System.out.println(i + " : " + sac1.get(i));
		}
		
		sac1.print();

		
		
    	//A policy instance is created.
		Policy<Integer> policy2 = new PolicyLRU<>();
		
		//A cache is created with 1 sets of size 4 each.
		SetAssociativeCache<Integer, Character> sac2 = new SetAssociativeCache<>(4,1,policy2);
		
		sac2.put(0, 'a');
		sac2.put(1, 'b');
		sac2.put(2, 'c');
		sac2.put(3, 'd');
		sac2.put(4, 'e'); //full set, replaces (0,'a')
		sac2.put(5, 'f'); //full set, replaces (1,'b')
		sac2.get(2);      //(2,'c') moves to front and is not LRU
		sac2.put(6, 'z'); //full set, replaces (3,'d')
		sac2.put(4, 'y'); //overwrites (4,'e') to (4,'y') moves to front and is not LRU
		sac2.get(2); 
		sac2.put(7, 'w'); //full set, replaces (5,'f')
		
		System.out.println();
		for(int i = 0; i <= 7; i++){
			System.out.println(i + " : " + sac2.get(i));
		}
    }
}
