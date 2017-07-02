package policy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * {@inheritDoc}
 * 
 * This LRU Policy maintains a list of BlockSets.
 * Each BlockSet represents a set within the cache and
 * contains a doubly linked list used to maintain the 
 * least recently used item of the set.
 */
public class PolicyLRU <K> implements Policy <K>{
	
	/*Structure that represents the cache*/
	private ArrayList<NodeSet<K>> cache; 
	
	/**
	 * Constructor for the PolicyLRU.
	 */	 
	public PolicyLRU(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public K evict(int index) {
		NodeSet<K> set = cache.get(index);
		return set.getEvictKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void getUpdate(int index, K key) {
		NodeSet<K> set = cache.get(index);
		set.getUpdate(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putUpdate(int index, K key) {
		NodeSet<K> set = cache.get(index);
		set.putUpdate(key);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCacheSize(int blocksPerSet, int numberOfSets) {
		this.cache = new ArrayList<>();
		for(int i = 0; i < numberOfSets; i++){
			cache.add(i, new NodeSet<K>(blocksPerSet));
		}
	}
}

/**
 * An object used to store node entries as a set within the cache.
 * Maintains least recently used with a doubly linked list of BlockNodes.
 */ 
class NodeSet<K> { 
    private HashMap<K, Node<K>> map;
    private Node<K> head;
    private Node<K> tail; //LRU item
    private int count;
    private int capacity;
    
	/**
	   * Constructor for the BlockSet.
	   * 
	   * @param capacity Capacity of the set.
	   */ 
	public NodeSet(int capacity){
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.count = 0;
	}
	
	/**
	   * Updates the LRU linked list when performing a get call.
	   * 
	   * @param key Key used for lookup.
	   */ 
	public void getUpdate(K key){
        if(map.containsKey(key)){
        	Node<K> temp = map.get(key);
            deleteNode(temp);
            addToHead(temp);
        } 
	}
	
	/**
	   * Updates the LRU linked list when performing a put call.
	   * 
	   * @param key Key used for lookup.
	   */ 
	public void putUpdate(K key){
        if(map.containsKey(key)){
        	Node<K> temp = map.get(key); 
            deleteNode(temp);
            addToHead(temp);
        } else{
            if(count > capacity){
            	throw new IndexOutOfBoundsException("Count is greater than capacity!");
            }
            if(count == capacity){
            	Node<K> nodeToBeEvicted = getEvictNode();
                map.remove(nodeToBeEvicted.getKey());
                deleteNode(nodeToBeEvicted);
            }
            Node<K> temp = new Node<K>(key);
            addToHead(temp);
            map.put(key,temp);
        }
	}
	
	/**
	   * Deletes a node from the LRU linked list.
	   * 
	   * @param node Node to be removed.
	   */ 
	private void deleteNode(Node<K> node){
    	if(node == null) return;
        decrementCount();
        if(node == head){ 
        	Node<K> temp = node.getNext();
            if(temp != null) temp.setPrev(null);
            node.setNext(null);
            node.setPrev(null);
            head = temp;
            return;
        }
        
        if(node == tail){ 
        	Node<K> temp = node.getPrev();
            if(temp != null) node.setNext(null);
            node.setNext(null);
            node.setPrev(null);
            tail = temp;
            return;
        }
        
        if(node.getPrev() != null) node.getPrev().setNext(node.getNext());
        if(node.getNext() != null) node.getNext().setPrev(node.getPrev());
    }
	
	/**
	   * Adds a node to the head of the linked list.
	   * 
	   * @param node Node to be added.
	   */ 
	private void addToHead(Node<K> node){
    	if(node == null) return;
    	incrementCount();
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        
        head.setPrev(node);
        node.setNext(head);
        node.setPrev(null);
        head = node;
    }
	
	/**
	   * Increments the count of the NodeSet.
	   */ 
	private void incrementCount(){
		this.count++;
	}
  
	/**
	   * Decrements the count of the NodeSet.
	   */ 
	private void decrementCount(){
		this.count--;
	}	
	
	/**
	   * Retrieves a node to evict.
	   * 
	   * @return Node item that is the least recently used.
	   */ 
    private Node <K> getEvictNode(){
    	return this.tail;
    }
    
	/**
	   * Retrieves a key to evict.
	   * 
	   * @return Key item that is the least recently used.
	   */ 
    public K getEvictKey(){
    	return this.tail.getKey();
    }
    

}

/**
 * Node object used to store data entries.
 * Used by the BlockSet for a doubly linked list.
 */ 
class Node <K> {
	
	private K key;
	private Node<K> prev;
	private Node<K> next;
	
	/**
	 * Constructor for the Node.
	 * 
	 * @param key Key to be stored.
	 */ 
	public Node(K key){
		this.key = key;		
	}
	
	/**
	   * Retrieves the key.
	   * 
	   * @return Key item of the node.
	   */ 
	public K getKey(){
		return this.key;
	}
	
	/**
	   * Retrieves the previous adjacent node.
	   * 
	   * @return Previous node.
	   */ 
	public Node<K> getPrev(){
		return this.prev;
	}
	
	/**
	   * Retrieves the next adjacent node.
	   * 
	   * @return Next node.
	   */ 
	public Node<K> getNext(){
		return this.next;
	}
	
	/**
	   * Sets the previous adjacent node.
	   */ 
	public void setPrev(Node<K> prev){
		this.prev = prev;
	}
	
	/**
	   * Sets the next adjacent node.
	   */ 
	public void setNext(Node<K> next){
		this.next = next;
	}
}