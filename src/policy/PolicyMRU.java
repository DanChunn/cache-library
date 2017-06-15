package policy;

import java.util.ArrayList;

/**
 * {@inheritDoc}
 * 
 * This MRU Policy maintains a list of keys.
 * Each key represents the most recently used item
 * for each set of the cache.
 */
public class PolicyMRU <K> implements Policy <K>{
	
	/*Structure that represents the cache*/
	private ArrayList<K> cache;
	
	/**
	 * Constructor for the PolicyMRU.
	 */	 
	public PolicyMRU(){
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public K evict(int index) {
		K temp = cache.get(index);
		cache.set(index, null);
		return temp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void getUpdate(int index, K key) {
		cache.set(index, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putUpdate(int index, K key) {
		cache.set(index, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCacheSize(int blocksPerSet, int numberOfSets) {
		this.cache = new ArrayList<>();
		for(int i = 0; i < numberOfSets; i++){
			cache.add(i, null);
		}
	}
}

