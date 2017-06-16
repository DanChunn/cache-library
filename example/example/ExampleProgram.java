package example;

import java.util.Scanner;

import policy.*;
import sac.SetAssociativeCache;


/** 
 * An example program demonstrating a cache that takes <Integer, Character> pairs.
 * Can be run in the console.
 * */
public class ExampleProgram {
    public static void main(String[] args) {
    	
    	Policy<Integer> policy = null;
    	
    	System.out.println("Set Associative Cache");
    	System.out.println("-- by Daniel Chunn --");
    	System.out.println("------Example--------");
    	System.out.println();
    	Scanner sc = new Scanner(System.in);  
    	System.out.println("Available Commands. Enter a number to execute command.");
    	System.out.println("1) Create cache with LRU policy.");
    	System.out.println("2) Create cache with MRU policy.");
    	System.out.println("3) Exit.");
    	
    	int n = sc.nextInt(); 
    	while(n <= 0 || n > 3){
    		System.out.println("Must enter a number above.");
    		n = sc.nextInt(); 
    	}
    	
    	if(n == 1){
    		policy = new PolicyLRU<>();
    		System.out.println("LRU Policy created.");
    	} else if (n == 2){
    		policy = new PolicyMRU<>();
    		System.out.println("MRU Policy created.");
    	} else if (n == 3){
    		exitProgram(sc);
    	} 
    	
    	System.out.println("Enter the amount of entries per set:");

    	n = sc.nextInt(); 
    	while(n <= 0 || n > Integer.MAX_VALUE){
    		checkBounds(n);
    		n = sc.nextInt(); 
    	}
    	int blocks = n;
    	
    	System.out.println("Enter the amount of sets in the cache");
    	
    	n = sc.nextInt(); 
    	while(n <= 0 || n > Integer.MAX_VALUE){
    		checkBounds(n);
    		n = sc.nextInt(); 
    	}
    	int sets = n;
    	
    	
    	SetAssociativeCache<Integer, Character> sac = new SetAssociativeCache<>(blocks,sets,policy);
    	System.out.println("The cache has been created. It has sets of size " + blocks + " and has " + sets + " sets.");
    	System.out.println("*** For this example, the keys are Integers and the values are Characters. ***");
    	System.out.println();
    	System.out.println("Available Commands. Enter a string to execute command.");
    	System.out.println("get INTEGER            | returns a character value      | ex. get 5");
    	System.out.println("put INTEGER CHARACTER  | places the pair into the cache | ex. put 5 A");
    	System.out.println("print                  | prints the cache items         | ex. print");
    	System.out.println("exit                   | exits the program              | ex. exit");
    	
    	String s = "";
    	
    	while(s != "exit"){
    		s = sc.nextLine();
    		String[] arr = s.split(" ");
    		
    		if(arr == null || arr.length == 0){
    			printError();
    			continue;
    		}
    		
    		try{
    			
        		if(arr[0].equals("get") ){

        			Integer k = Integer.parseInt(arr[1]);
        			
        			if(sac.get(k) == null){
        				System.out.println("No value found.");
        			} else{
        				System.out.println(">> "+sac.get(k));
        			}
        		} 
        		else if (arr[0].equals("put")){
        			
        			Integer k = Integer.parseInt(arr[1]);
        			Character v = arr[2].charAt(0);
        			sac.put(k, v);
        			System.out.println(("("+ k + " , " + v + ") placed!"));
        		} 
        		
        		else if (arr[0].equals("print")){
        			
        			sac.print();
        		} 
        		
        		else if (arr[0].equals("exit")){
        			
        			exitProgram(sc);	
        		} 
    		} catch (Exception e){
    			printError();
    			continue;
    		}

    	}

    	exitProgram(sc);
    	
		
    	/**
    	 * Some more examples listed below...
    	 */
    	
    	/*
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
		}*/
    }
    
    private static void printError(){
    	System.out.println("Improper input. Try again.");
    }
    
    private static void checkBounds(int n){
		if(n <= 0){
			System.out.println("Enter a number above 0.");
		} else{
			System.out.println("That value is too large.");
		}
    }
    
    private static void exitProgram(Scanner sc){
    	System.out.println("Exiting program...");
		sc.close();
		System.exit(0);
    }
}
