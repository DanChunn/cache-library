package policy;

public class PolicyMRU <K> implements Policy <K>{
	
	private Object[] cache;

	public PolicyMRU(){
		
	}

	@Override
	public K evict(int index) {
		K temp = (K) cache[index];
		cache[index] = null;
		return temp;
	}

	@Override
	public void getUpdate(int index, K key) {
		cache[index] = key;
	}

	@Override
	public void putUpdate(int index, K key) {
		cache[index] = key;
	}

	@Override
	public void setCacheSize(int blocksPerSet, int numberOfSets) {
		this.cache = new Object[numberOfSets];
	}
}
