package sac;
import java.util.*;

import policy.Policy;

/**
 * An N-way Set-Associative Cache.
 * The Cache is an array of N size containing BlockSets.
 * BlockSets are of M size and contain a HashMap that stores BlockNodes as its values.
 * BlockNodes are the objects that store the data.
 * Utilizes a Policy to evict items from within the cache.
 */
public class SetAssociativeCache <K, V>  {
	
	private int blocksPerSet;
	private int numberOfSets;
	private Policy<K> policy;
	
	private ArrayList<BlockSet<K,V>> cache;
	
	/**
	   * Constructor for the cache.
	   * 
	   * @param blocksPerSet Number of items per set.
	   * @param numberOfSets Number of sets within the cache.
	   * @param policy The replacement policy to be used by the cache.
	   * 
	   * @exception IllegalArgumentException An invalid argument was received.
	   */ 
	public SetAssociativeCache(int blocksPerSet, int numberOfSets, Policy<K> policy){
		if(blocksPerSet <= 0){
			throw new IllegalArgumentException("Blocks per set cannot be <= 0.");
		}
		if(numberOfSets <= 0){
			throw new IllegalArgumentException("Numbers of Sets cannot be <= 0.");
		}
		if(policy == null){
			throw new IllegalArgumentException("Policy cannot be null.");
		}

		this.blocksPerSet = blocksPerSet;
		this.numberOfSets = numberOfSets;
		this.cache = new ArrayList<>();	
		this.policy = policy;
		this.policy.setCacheSize(blocksPerSet, numberOfSets); //calls policy to set size
		for(int i = 0; i < numberOfSets; i++){
			cache.add(i, new BlockSet<K, V>(blocksPerSet, i, policy));
		}
	}
	
	/**
	   * Retrieves an item from the cache based on a key.
	   * 
	   * @param key Key used for retrieval.
	   * 
	   * @exception Illegal argument exception.
	   * 
	   * @return Value retrieved by key.
	   */ 
	public V get(K key){
		if(key == null){
			throw new IllegalArgumentException("Key cannot be null.");
		}
	
		int index = Math.abs(key.hashCode() % numberOfSets);
		BlockSet<K,V> bs = cache.get(index);
		return bs.get(key);
	}
	
	/**
	   * Places a key and value to the cache.
	   * 
	   * @param key Item to be placed into cache.
	   * @param value Item to be placed into cache.
	   * 
	   * @exception IllegalArgumentException An invalid argument was received.
	   */ 
	public void put(K key, V value){
		if(key == null || value == null){ 
			throw new IllegalArgumentException("Key or Value cannot be null.");
		}
		
		int index = Math.abs(key.hashCode() % numberOfSets);
		BlockSet<K,V> bs = cache.get(index);
		bs.put(key, value);
	}
	
	/**
	   * Prints the contents of the cache to console.
	   */ 
	public void print(){
		System.out.println("-----");
		System.out.println("N:" + blocksPerSet + " Sets:" + numberOfSets);
		for(int i = 0; i < cache.size(); i++){
			BlockSet<K,V> bs = cache.get(i);
			HashMap<K, BlockNode<K,V>> map = bs.getMap();
			System.out.println();
			System.out.print("Index:" + i);
			
			for(Map.Entry<K, BlockNode<K,V>> kvp : map.entrySet()){
				BlockNode<K,V> node = kvp.getValue();
				System.out.print(" ("+node.getKey() + ":" + node.getValue() + ") ");
			}
			
		}
		System.out.println();
	}
}