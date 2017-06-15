package sac;
import java.util.*;
import java.util.Map.Entry;

import policy.Policy;

/**
 * BlockSet - used by the SetAssociativeCache to store sets of BlockNodes.
 */
public class BlockSet <K, V>{
    private final int capacity;
    private final int index;
    private HashMap<K , BlockNode<K,V>> map;
    private int count;
    private Policy<K> policy;
    
	/**
	   * Constructor for the BlockSet.
	   * 
	   * @param capacity Capacity of the set.
	   * @param index Index of the set relative within the cache.
	   * @param policy The policy dictates how items are evicted from the set.
	   */ 
    public BlockSet(int capacity, int index, Policy<K> policy) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.count = 0;
        this.index = index;
        this.policy = policy;
    }
    
	/**
	   * Retrieves an item from the set based on a key.
	   * 
	   * @param key Key used for retrieval.
	   * @return Value retrieved by key.
	   */ 
    public V get(K key) {
        if(map.containsKey(key)){
            BlockNode<K,V> temp = map.get(key); 
            policy.getUpdate(index, key);
            return temp.getValue();
        } 
        return null;
    }
    
	/**
	   * Places a key and value to the cache.
	   * 
	   * @param key Item to be placed into cache.
	   * @param value Item to be placed into cache.
	   * @exception IndexOutOfBoundsException An index out of bounds was received.
	   */ 
    public void put(K key, V value){
        if(map.containsKey(key)){
            BlockNode<K,V> temp = map.get(key);  
            temp.setValue(value);
        } else{
            if(count > capacity){
            	throw new IndexOutOfBoundsException("Count is greater than capacity! Something went terribly wrong..");
            }
            if(count == capacity){
            	BlockNode<K,V> nodeToBeEvicted = getEvictNode();
                map.remove(nodeToBeEvicted.getKey());
                decrementCount();
            }
            BlockNode<K,V> temp = new BlockNode<K,V>(key, value);
            map.put(key,temp);
            incrementCount();
        }
        policy.putUpdate(index, key);
    }
     
	/**
	   * Retrieves a BlockNode to be evicted based on the cache replacement policy.
	   * 
	   * @return A BlockNode to be evicted.
	   */ 
    private BlockNode<K,V> getEvictNode(){
    	K key = policy.evict(index);
    	return map.get(key);
    }
    
	/**
	   * Increments the count of the BlockSet.
	   */ 
    private void incrementCount(){
    	this.count++;
    }
    
	/**
	   * Decrements the count of the BlockSet.
	   */ 
    private void decrementCount(){
    	this.count--;
    }
    
	/**
	   * Retrieves the HashMap of the BlockSet.
	   * 
	   * @return HashMap of the BlockSet;
	   */ 
    public HashMap<K, BlockNode<K,V>> getMap(){
    	return this.map;
    }

	/**
	   * Retrieves the index of the BlockSet located in the cache.
	   * 
	   * @return Index of the BlockSet;
	   */ 
    public int getIndex(){
    	return this.index;
    }
    
	/**
	   * Prints the contents of the BlockSet to console.
	   */ 
    public void print(){
    	System.out.println();
    	for(Entry<K, BlockNode<K, V>> kvp : map.entrySet()){
    		System.out.print(kvp.getValue().getValue() + ", ");
    	}
    	System.out.println();
    }

}

