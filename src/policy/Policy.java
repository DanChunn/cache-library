package policy;

/** 
 * A replacement policy used to select which item to be evicted from the cache.
 * */
public interface Policy <K> {
	
	/** 
	 * Selects a key used to evict an item from the cache.
	 * 
	 * @param index Index of an individual set located within the cache.
	 * @return Returns the key to select the item to be evicted.
	 * */
	public K evict(int index);	
	
	/** 
	 * Performs an update to the Policy when the cache performs a get call.
	 * 
	 * @param index Index of an individual set located within the cache array.
	 * @param key The key item used for lookup of an item within the cache.
	 * */
	public void getUpdate(int index, K key);
	
	/** 
	 * Performs an update to the Policy when the cache performs a put call.
	 * 
	 * @param index Index of an individual set located within the cache array.
	 * @param key The key item used for lookup of an item within the cache.
	 * */
	public void putUpdate(int index, K key);

	/** 
	 * Sets the cache's dimensions for the Policy to use when the cache is first constructed.
	 * 
	 * @param blocksPerSet Number of items within a set.
	 * @param numberOfSets Number of sets within the cache.
	 * */
	public void setCacheSize(int blocksPerSet, int numberOfSets);
}
