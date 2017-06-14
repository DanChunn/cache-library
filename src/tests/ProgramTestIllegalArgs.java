package tests;
import sac.*;

import org.junit.Test;

import policy.*;

public class ProgramTestIllegalArgs {


	@Test(expected = IllegalArgumentException.class)
	public void test1() {
		Policy<Character> policy = new PolicyLRU<>();
		SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(4,0,policy);
		sac.print();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test2(){
		Policy<Character> policy = new PolicyLRU<>();
		SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(4,-1,policy);
		sac.print();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test3() {
		Policy<Character> policy = new PolicyLRU<>();
		SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(0,4,policy);
		sac.print();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test4(){
		Policy<Character> policy = new PolicyLRU<>();
		SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(-1,4,policy);
		sac.print();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test5(){
		Policy<Character> policy = null;
		SetAssociativeCache<Character, Integer> sac = new SetAssociativeCache<>(-1,4,policy);
		sac.print();
	}
}
