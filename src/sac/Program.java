package sac;

import policy.Policy;
import policy.PolicyMRU;

public class Program {
    public static void main(String[] args) {
    		Policy<Integer> policy = null;
    	
    		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(3,3,policy);
    		System.out.println("HELLO");
    		sac.put(0, 0);
    		sac.put(1, 1);
    		sac.put(2, 2);
    		sac.put(3, 3);
    		sac.put(4, 4);
    		sac.put(5, 5);
    		sac.put(6, 6);
    		sac.put(7, 7);
    		sac.put(8, 8);
    		sac.put(9, 9);
    		sac.put(10, 10);
    		sac.put(11, 11);
    		System.out.println(sac.get(6));
    		System.out.println(sac.get(7));
    		System.out.println(sac.get(8));
    		System.out.println(sac.get(0));
    		System.out.println(sac.get(1));
    		System.out.println(sac.get(2));
    		System.out.println(sac.get(9));
    		System.out.println(sac.get(10));
    		System.out.println(sac.get(11));
    		
    		/*
    		assertEquals(null, sac.get(0));
    		assertEquals(null, sac.get(1));
    		assertEquals(null, sac.get(2));
    		assertEquals(9, sac.get(9));
    		assertEquals(10, sac.get(10));
    		assertEquals(11, sac.get(11));*/
    		sac.print();
    }
}
