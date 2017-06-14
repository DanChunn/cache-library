package sac;


import java.util.HashSet;
import java.util.Set;

import policy.Policy;
import policy.PolicyLRU;
import policy.PolicyMRU;

public class Program {
    public static void main(String[] args) {
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
		e.add('b');
		HashSet<Character> f = new HashSet<>();
		f.add('c');
		sac.put(1, a);
		sac.put(2, b);
		sac.put(3, c);
		sac.put(4, d);
		sac.put(5, e);
		sac.put(6, f);
		sac.print();

		HashSet<Character> v = sac.get(5);
		for (Character character : v) {
			System.out.println(character);
		}
    }
}
