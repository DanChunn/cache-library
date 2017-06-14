package tests;
import sac.*;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import policy.Policy;
import policy.PolicyLRU;


public class ProgramTestLRU {
	
	@Test
	public void test0(){
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(3,3,policy);
		
		sac.put(0, 0);
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals((Integer)0, sac.get(0));
		assertEquals(1, sac.get(1));
		assertEquals(2, sac.get(2));
		sac.put(3, 3);
		sac.put(4, 4);
		sac.put(5, 5);
		sac.put(6, 6);
		sac.put(7, 7);
		sac.put(8, 8);
		sac.put(9, 9);
		sac.put(10, 10);
		sac.put(11, 11);
		assertEquals(null, sac.get(0));
		assertEquals(null, sac.get(1));
		assertEquals(null, sac.get(2));
		assertEquals(9, sac.get(9));
		assertEquals(10, sac.get(10));
		assertEquals(11, sac.get(11));
		sac.print();
	}
	
	@Test
	public void test1() {
		Policy<Character> policy = new PolicyLRU<>();
        SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(4,1,policy);
        
        sac.put('C', 0);
        assertEquals(0, sac.get('C'));
        sac.put('B', 1);
        sac.put('C', 2);
        sac.put('D', 3);
        sac.put('E', 4);
        sac.put('D', 5);
        sac.put('F', 6);
        assertEquals(6, sac.get('F'));
        assertEquals(2, sac.get('C'));
        assertEquals(4, sac.get('E'));
        assertEquals(5, sac.get('D'));
        assertEquals(null, sac.get('X'));
        assertEquals(null, sac.get('B'));
        sac.print();
	}

	@Test
	public void test2() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(1, sac.get(1));
		sac.put(3, 3);
		assertEquals(null, sac.get(2));
		sac.put(4, 4);
		assertEquals(null, sac.get(1));
		assertEquals(3, sac.get(3));
		assertEquals(4, sac.get(4));
		sac.print();
	}
	
	@Test
	public void test3() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(1, sac.get(1));
		assertEquals(2, sac.get(2));
		sac.put(4, 4);
		assertEquals(null, sac.get(1));
		assertEquals(4, sac.get(4));
		assertEquals(2, sac.get(2));
		sac.print();
	}
	
	@Test
	public void test4() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(2, 3);
		sac.put(1, 4);
		assertEquals(4, sac.get(1));
		assertEquals(3, sac.get(2));
		sac.print();
	}
	
	@Test
	public void test5() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,3,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		sac.put(4, 1);
		sac.put(5, 2);
		sac.put(6, 3);
		sac.put(7, 77);
		sac.put(8, 88);
		assertEquals(null, sac.get(1));
		assertEquals(null, sac.get(2));
		assertEquals(1, sac.get(4));
		assertEquals(2, sac.get(5));
		assertEquals(77, sac.get(7));
		sac.put(1, 11);
		assertEquals(null, sac.get(4));
		assertEquals(11, sac.get(1));
		assertEquals(88, sac.get(8));
		sac.print();
	}
	
	//N = 1
	@Test
	public void test6() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(1,3,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		sac.put(4, 4);
		sac.put(5, 5);
		
		assertEquals(null, sac.get(1));
		assertEquals(4, sac.get(4));
		assertEquals(3, sac.get(3));
		sac.print();
	}
	
	//Cache of 1 set of size 1
	@Test 
	public void test7() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(1,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		
		assertEquals(null, sac.get(1));
		assertEquals(null, sac.get(2));
		assertEquals(3, sac.get(3));
		sac.print();
	}
	
	//Objects as keys
	@Test
	public void test8() {
		Policy<Set<Character>> policy = new PolicyLRU<>();
		SetAssociativeCache<Set<Character>, Integer> sac = new SetAssociativeCache<>(4,1,policy);
		
		HashSet<Character> a = new HashSet<>();
		a.add('A');
		a.add('B');
		HashSet<Character> b = new HashSet<>();
		HashSet<Character> c = new HashSet<>();
		HashSet<Character> d = new HashSet<>();
		d.add('A');
		sac.put(a, 1);
		sac.put(b, 2);
		sac.put(c, 3);
		sac.put(d, 4);
		
		assertEquals(1, sac.get(a));
		assertEquals(3, sac.get(b));
		assertEquals(3, sac.get(c));
		assertEquals(4, sac.get(d));
		sac.print();
	}
	
	//Objects as keys
	@Test
	public void test9() {
		Policy<Set<Character>> policy = new PolicyLRU<>();
		SetAssociativeCache<Set<Character>, Integer> sac = new SetAssociativeCache<>(2,2,policy);
		
		HashSet<Character> a = new HashSet<>();
		a.add('A');
		a.add('B');
		HashSet<Character> b = new HashSet<>();
		HashSet<Character> c = new HashSet<>();
		HashSet<Character> d = new HashSet<>();
		d.add('A');
		HashSet<Character> e = new HashSet<>();
		e.add('b');
		sac.put(a, 1);
		sac.put(b, 2);
		sac.put(c, 3);
		sac.put(d, 4);
		sac.put(e, 4);
		sac.print();
		assertEquals(1, sac.get(a));
		assertEquals(3, sac.get(b));
		assertEquals(3, sac.get(c));
		assertEquals(4, sac.get(d));
		assertEquals(4, sac.get(e));	
		sac.print();
	}
	@Test
	public void test10(){
		Policy<String> policy = new PolicyLRU<>();
		SetAssociativeCache<String, Integer> sac = new SetAssociativeCache<>(2,2,policy);

		String a = "Hello";
		String b = "Hello";
		
		sac.put(a, 1);
		sac.put(b, 99);
		assertEquals(99, sac.get(a));
		assertEquals(99, sac.get(b));
	}
	
}

