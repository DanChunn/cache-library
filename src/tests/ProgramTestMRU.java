package tests;
import sac.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import policy.PolicyMRU;
import policy.Policy;

public class ProgramTestMRU {
	
	//General test
	@Test
	public void test0(){
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(3,3,policy);
		
		sac.put(0, 0);
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(Integer.valueOf(0), sac.get(0));
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(2), sac.get(2));
		sac.put(3, 3);
		sac.put(4, 4);
		sac.put(5, 5);
		sac.put(6, 6);
		sac.put(7, 7);
		sac.put(8, 8);
		sac.put(9, 9);
		sac.put(10, 10);
		sac.put(11, 11);
		assertEquals(null, sac.get(6));
		assertEquals(null, sac.get(7));
		assertEquals(null, sac.get(8));
		assertEquals(Integer.valueOf(0), sac.get(0));
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(2), sac.get(2));
		//sac.print();
	}
	
	//Num of Sets = 1
	@Test
	public void test1() {
		Policy<Character> policy = new PolicyMRU<>();
        SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(4,1,policy);
        
        sac.put('A', 0);
        sac.put('B', 1);
        sac.put('C', 2);
        sac.put('D', 3);
        sac.put('E', 4);
        sac.put('C', 5);
        sac.put('D', 6);
        sac.put('B', 7);
        assertEquals(Integer.valueOf(0), sac.get('A'));
        assertEquals(Integer.valueOf(7), sac.get('B'));
        assertEquals(Integer.valueOf(6), sac.get('D'));
        assertEquals(Integer.valueOf(4), sac.get('E'));
        assertEquals(null, sac.get('X'));
        assertEquals(null, sac.get('C'));
        //sac.print();
	}
	
	//Num of Sets = 1,  mixed put and get
	@Test
	public void test2() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(Integer.valueOf(1), sac.get(1));
		sac.put(3, 3);
		assertEquals(null, sac.get(1));
		sac.put(4, 4);
		assertEquals(null, sac.get(3));
		assertEquals(Integer.valueOf(2), sac.get(2));
		assertEquals(Integer.valueOf(4), sac.get(4));
		//sac.print();
	}
	
	//Num of Sets = 1,  mixed put and get
	@Test
	public void test3() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(2), sac.get(2));
		sac.put(4, 4);
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(4), sac.get(4));
		assertEquals(null, sac.get(2));
		//sac.print();
	}
	
	//Num of Sets = 1, mixed put and get
	@Test
	public void test4() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(2, 3);
		sac.put(1, 4);
		assertEquals(Integer.valueOf(4), sac.get(1));
		assertEquals(Integer.valueOf(3), sac.get(2));
		//sac.print();
	}
	
	//Mixed dimensions, mixed put and get
	@Test
	public void test5() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,3,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		sac.put(4, 1);
		sac.put(5, 2);
		sac.put(6, 3);
		sac.put(7, 77);
		sac.put(8, 88);
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(2), sac.get(2));
		assertEquals(null, sac.get(4));
		assertEquals(null, sac.get(5));
		assertEquals(Integer.valueOf(77), sac.get(7));
		sac.put(4, 44);
		assertEquals(Integer.valueOf(44), sac.get(4));
		assertEquals(Integer.valueOf(88), sac.get(8));
		//sac.print();
	}
	
	//Blocks per set = 1
	@Test
	public void test6() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(1,3,policy);
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		sac.put(4, 4);
		sac.put(5, 5);
		
		assertEquals(null, sac.get(1));
		assertEquals(Integer.valueOf(4), sac.get(4));
		assertEquals(null, sac.get(2));
		sac.put(5, 11);
		sac.put(7, 77);
		assertEquals(Integer.valueOf(11), sac.get(5));
		assertEquals(Integer.valueOf(77), sac.get(7));
		//sac.print();
	}
	
	//Cache of 1 set of size 1
	@Test 
	public void test7() {
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(1,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		sac.put(3, 3);
		
		assertEquals(null, sac.get(1));
		assertEquals(null, sac.get(2));
		assertEquals(Integer.valueOf(3), sac.get(3));
		//sac.print();
	}
	
	//Collections as keys, mixed put and get
	@Test
	public void test8() {
		Policy<Set<Character>> policy = new PolicyMRU<>();
		SetAssociativeCache<Set<Character>, Integer> sac = new SetAssociativeCache<>(4,1,policy);
		
		HashSet<Character> a = new HashSet<>();
		a.add('A');
		a.add('B');
		HashSet<Character> b = new HashSet<>();
		HashSet<Character> c = new HashSet<>();
		c.add('c');
		HashSet<Character> d = new HashSet<>();
		d.add('A');
		HashSet<Character> f = new HashSet<>();
		f.add('X');
		HashSet<Character> v = new HashSet<>();
		v.add('Y');
		HashSet<Character> z = new HashSet<>();
		z.add('Z');
		HashSet<Character> y = new HashSet<>();
		y.add('Z');
		sac.put(a, 1);
		sac.put(b, 2);
		sac.put(c, 3);
		sac.put(d, 4);
		sac.put(v, 55);
		assertEquals(Integer.valueOf(1), sac.get(a));
		assertEquals(Integer.valueOf(2), sac.get(b));
		assertEquals(Integer.valueOf(3), sac.get(c));
		assertEquals(null, sac.get(d));
		assertEquals(Integer.valueOf(55), sac.get(v));
		sac.put(a, 99);
		assertEquals(Integer.valueOf(99), sac.get(a));
		sac.put(f, 55);
		assertEquals(null, sac.get(a));
		assertEquals(Integer.valueOf(55), sac.get(f));
		sac.put(a, 11);
		sac.put(z, 56);
		assertEquals(null, sac.get(f));
		assertEquals(Integer.valueOf(56), sac.get(z));
		sac.put(y,1231);
		assertEquals(Integer.valueOf(1231), sac.get(z));
		//sac.print();
	}
	
	//Objects as keys
	@Test
	public void test9() {
		Policy<Set<Character>> policy = new PolicyMRU<>();
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
		assertEquals(Integer.valueOf(1), sac.get(a));
		assertEquals(Integer.valueOf(3), sac.get(b));
		assertEquals(Integer.valueOf(3), sac.get(c));
		assertEquals(Integer.valueOf(4), sac.get(d));
		assertEquals(Integer.valueOf(4), sac.get(e));	
		//sac.print();

	}
	
	//String as keys
	@Test
	public void test10(){
		Policy<String> policy = new PolicyMRU<>();
		SetAssociativeCache<String, Integer> sac = new SetAssociativeCache<>(2,2,policy);

		String a = "Hello";
		String b = "Hello";
		
		sac.put(a, 1);
		sac.put(b, 99);
		assertEquals(Integer.valueOf(99), sac.get(a));
		assertEquals(Integer.valueOf(99), sac.get(b));
		//sac.print();
	}
	
	//Collections as values,  mixed put and get, integrity check
	@Test
	public void test11(){
		Policy<Integer> policy = new PolicyMRU<>();
		SetAssociativeCache<Integer, HashSet<Character>> sac = new SetAssociativeCache<>(2,2,policy);
		
		HashSet<Character> a = new HashSet<>();
		a.add('A');
		a.add('B');
		HashSet<Character> b = new HashSet<>();
		HashSet<Character> c = new HashSet<>();
		HashSet<Character> d = new HashSet<>();
		d.add('A');
		HashSet<Character> e = new HashSet<>();
		e.add('b');
		HashSet<Character> f = new HashSet<>();
		e.add('b');
		f.add('c');
		sac.put(1, a);
		sac.put(2, b);
		sac.put(3, c);
		sac.put(4, d);
		assertEquals(b, sac.get(2));
		sac.put(1,b);
		assertEquals(b, sac.get(1));
		sac.put(5, e);
		sac.put(6, f);
		assertEquals(null, sac.get(2));
		assertEquals(null, sac.get(1));
		assertEquals(d, sac.get(4));
		assertEquals(c, sac.get(3));
		assertEquals(e, sac.get(5));
		assertEquals(f, sac.get(6));
		//sac.print();
		
		//Checking integrity
		HashSet<Character> f1 = new HashSet<>();
		f1.add('c');
		assertTrue(sac.get(6).equals(f1));
		assertFalse(sac.get(6).equals(b));
	}
	
}
