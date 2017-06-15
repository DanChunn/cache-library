package tests;
import sac.*;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import policy.Policy;
import policy.PolicyLRU;

/*Testing of various commands and cache sizes*/
public class ProgramTestLRU {
	
	
	
	//General test
	@Test
	public void test0(){
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(3,3,policy);
		
		sac.put(0, 0);
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals((Integer)0, sac.get(0));
		assertEquals((Integer)1, sac.get(1));
		assertEquals((Integer)2, sac.get(2));
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
		assertEquals(Integer.valueOf(9), sac.get(9));
		assertEquals(Integer.valueOf(10), sac.get(10));
		assertEquals(Integer.valueOf(11), sac.get(11));
		//sac.print();
	}
	
	
	//Num of Sets = 1
	@Test
	public void test1() {
		Policy<Character> policy = new PolicyLRU<>();
        SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(4,1,policy);
        
        sac.put('C', 0);
        assertEquals(Integer.valueOf(0), sac.get('C'));
        sac.put('B', 1);
        sac.put('C', 2);
        sac.put('D', 3);
        sac.put('E', 4);
        sac.put('D', 5);
        sac.put('F', 6);
        assertEquals(Integer.valueOf(6), sac.get('F'));
        assertEquals(Integer.valueOf(2), sac.get('C'));
        assertEquals(Integer.valueOf(4), sac.get('E'));
        assertEquals(Integer.valueOf(5), sac.get('D'));
        assertEquals(null, sac.get('X'));
        assertEquals(null, sac.get('B'));
        //sac.print();
	}

	
	//Num of Sets = 1,  mixed put and get
	@Test
	public void test2() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(Integer.valueOf(1), sac.get(1));
		sac.put(3, 3);
		assertEquals(null, sac.get(2));
		sac.put(4, 4);
		assertEquals(null, sac.get(1));
		assertEquals(Integer.valueOf(3), sac.get(3));
		assertEquals(Integer.valueOf(4), sac.get(4));
		//sac.print();
	}
	
	//Num of Sets = 1,  mixed put and get
	@Test
	public void test3() {
		Policy<Integer> policy = new PolicyLRU<>();
		SetAssociativeCache<Integer, Integer> sac = new SetAssociativeCache<>(2,1,policy);
		
		sac.put(1, 1);
		sac.put(2, 2);
		assertEquals(Integer.valueOf(1), sac.get(1));
		assertEquals(Integer.valueOf(2), sac.get(2));
		sac.put(4, 4);
		assertEquals(null, sac.get(1));
		assertEquals(Integer.valueOf(4), sac.get(4));
		assertEquals(Integer.valueOf(2), sac.get(2));
		//sac.print();
	}
	
	//Num of Sets = 1, mixed put and get
	@Test
	public void test4() {
		Policy<Integer> policy = new PolicyLRU<>();
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
		assertEquals(Integer.valueOf(1), sac.get(4));
		assertEquals(Integer.valueOf(2), sac.get(5));
		assertEquals(Integer.valueOf(77), sac.get(7));
		sac.put(1, 11);
		assertEquals(null, sac.get(4));
		assertEquals(Integer.valueOf(11), sac.get(1));
		assertEquals(Integer.valueOf(88), sac.get(8));
		//sac.print();
	}
	
	//Blocks per set = 1
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
		assertEquals(Integer.valueOf(4), sac.get(4));
		assertEquals(Integer.valueOf(3), sac.get(3));
		//sac.print();
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
		assertEquals(Integer.valueOf(3), sac.get(3));
		//sac.print();
	}
	
	//Collections as keys, mixed put and get
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
		HashSet<Character> f = new HashSet<>();
		f.add('X');
		HashSet<Character> v = new HashSet<>();
		v.add('Y');
		HashSet<Character> z = new HashSet<>();
		z.add('Z');
		sac.put(a, 1);
		sac.put(b, 2);
		sac.put(c, 3);
		sac.put(d, 4);
		sac.put(v, 55);
		assertEquals(Integer.valueOf(1), sac.get(a));
		assertEquals(Integer.valueOf(3), sac.get(b));
		assertEquals(Integer.valueOf(3), sac.get(c));
		assertEquals(Integer.valueOf(4), sac.get(d));
		sac.put(a, 99);
		assertEquals(Integer.valueOf(99), sac.get(a));
		sac.put(f, 55);
		sac.put(a, 11);
		sac.put(z, 56);
		assertEquals(null, sac.get(b));
		assertEquals(Integer.valueOf(55), sac.get(f));
		//sac.print();
	}
	
	//Objects as keys,  mixed put and get
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
		Policy<String> policy = new PolicyLRU<>();
		SetAssociativeCache<String, Integer> sac = new SetAssociativeCache<>(2,2,policy);

		String a = "Hello";
		String b = "Hello";
		
		sac.put(a, 1);
		sac.put(b, 99);
		assertEquals(Integer.valueOf(99), sac.get(a));
		assertEquals(Integer.valueOf(99), sac.get(b));
		//sac.print();
	}
	
	//Collections as values,  mixed put and get,  integrity check
	@Test
	public void test11(){
		Policy<Integer> policy = new PolicyLRU<>();
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
		assertEquals(b, sac.get(2));
		assertEquals(b, sac.get(1));
		assertEquals(null, sac.get(4));
		assertEquals(null, sac.get(3));
		assertEquals(e, sac.get(5));
		assertEquals(f, sac.get(6));
		//sac.print();
		
		//Checking integrity
		HashSet<Character> f1 = new HashSet<>();
		f1.add('c');
		assertTrue(sac.get(6).equals(f1));
		assertFalse(sac.get(6).equals(b));
		assertTrue(f1.contains('c'));
		assertFalse(f1.contains('d'));
		assertTrue(sac.get(6).contains('c'));
		assertFalse(sac.get(6).contains('d'));
	}
	
}

