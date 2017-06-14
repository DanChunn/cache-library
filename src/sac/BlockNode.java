package sac;

/**
 * BlockNode - used by the BlockSet to store key value pairs.
 */
public class BlockNode<K, V>{
	private K key; //Tag
	private V value; //Data

	/**
	   * Constructor for the BlockNode.
	   * 
	   * @param key Key to be stored.
	   * @param value Value to be stored.
	   */ 
	public BlockNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	   * Retrieves the BlockNode's key.
	   * 
	   * @return Key of BlockNode.
	   */ 
	public K getKey(){
		return this.key;
	}
	
	/**
	   * Retrieves the BlockNode's value.
	   * 
	   * @return Value of BlockNode.
	   */ 
	public V getValue(){
		return this.value;
	}
	
	/**
	   * Sets the value of the BlockNode.
	   * 
	   * @param value Value to be stored.
	   */ 
	public void setValue(V value){
		this.value = value;
	}
}
