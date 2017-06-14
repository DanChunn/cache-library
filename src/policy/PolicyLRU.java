package policy;

import java.util.HashMap;

import sac.BlockNode;


public class PolicyLRU <K> implements Policy <K>{
	private NodeSet<K>[] cache;    
	
	public PolicyLRU(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public K evict(int index) {
		NodeSet<K> set = cache[index];
		return set.getEvictKey();
	}

	@Override
	public void getUpdate(int index, K key) {
		NodeSet<K> set = cache[index];
		set.getUpdate(key);
	}

	@Override
	public void putUpdate(int index, K key) {
		NodeSet<K> set = cache[index];
		set.putUpdate(key);

	}

	@Override
	public void setCacheSize(int blocksPerSet, int numberOfSets) {
		this.cache = new NodeSet[numberOfSets];
		//this.numberOfSets = numberOfSets;
		for(int i = 0; i < cache.length; i++){
			cache[i] = new NodeSet<K>(blocksPerSet);
		}
	}
	
}

class NodeSet<K> {
    private HashMap<K, Node<K>> map;
    private Node<K> head;
    private Node<K> tail;
    private int count;
    private int capacity;
    
	public NodeSet(int capacity){
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.count = 0;
	}
	
	public void getUpdate(K key){
        if(map.containsKey(key)){
        	Node<K> temp = map.get(key);
            deleteNode(temp);
            addToHead(temp);
        } 
	}
	
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
	
	public void deleteNode(Node<K> node){
    	if(node == null) return;
        count--;
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
	
	public void addToHead(Node<K> node){
    	if(node == null) return;
        count++;
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
	
    public K getEvictKey(){
    	return tail.getKey();
    }
    
    public Node <K> getEvictNode(){
    	return tail;
    }
}


class Node <K> {
	
	private K key;
	private Node<K> prev;
	private Node<K> next;
	
	public Node(K key){
		this.key = key;		
	}
	
	public K getKey(){
		return this.key;
	}
	
	public Node<K> getNext(){
		return this.next;
	}
	
	public Node<K> getPrev(){
		return this.prev;
	}

	public void setPrev(Node<K> prev){
		this.prev = prev;
	}
	
	public void setNext(Node<K> next){
		this.next = next;
	}
}