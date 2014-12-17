import java.util.ArrayList;
import java.util.List;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V>{

	private ArrayList<Bucket<K,V>>array;
	private int numberOfBuckets ;
	
	public MyHashMapImpl(int length){
		this.numberOfBuckets = length ;
		initializeArray();
	}
	
	/**This method is used to instantiate every Bucket object
	 * from the Buckets Array 
	 * 
	 */
	private void initializeArray() {
		
		if(array == null)
			array = new ArrayList<Bucket<K,V>> ();
		
		for(int i= 0 ; i < numberOfBuckets ; i++)
			array.add(new MyBucket<K,V>());
						
	}

	@Override
	public V get(K key) {
		if(array.get(translate(key.hashCode())).getElement(key) == null)
				return null ;
		V value = array.get(translate(key.hashCode())).getElement(key).getValue();
		return value ;
	}

	@Override
	public V put(K key, V value) {
		if(array == null)
			array = new ArrayList<Bucket<K,V>>(numberOfBuckets);
		array.get(translate(key.hashCode())).addEntry(key,value);
		return null;
	}

	@Override
	public V remove(K key) {
		int position = translate(key.hashCode());
		Bucket<K,V> bucket = array.get(position);
		
		V value = bucket.remove(key);
		return value;
	}
	

	@Override
	public int size() {
		return numberOfBuckets;
	}

	@Override
	public List<? extends MyHashMap.Bucket<K, V>> getBuckets() {
		return array;
	}
	
	
	/**Method which returns narrows the limits of values an index
	 * in the array can have based on the length of the array
	 * 
	 * @param hashCode The big number to be trimmed down
	 * @return The trimmed down index
	 */
	private int translate(int hashCode){
		return Math.abs(hashCode ) % numberOfBuckets; 
	}

	
	public class MyEntry<K,V> implements Entry<K,V> {

		private K key;
		private V definition ;
		
		public MyEntry(K key ,V value ){
			this.key = key ;
			this.definition = value ;
		}
		
		/**This method returns the key associated with this Entry object
		 * 
		 */
		public K getKey() {
			return key;
		}

		/**
		 * This method returns the value associated with this Entry object
		 */
		public V getValue() {
			return definition;
		}
		

	}
	
	
	public class MyBucket<K,V> implements Bucket<K,V>{

		private ArrayList<Entry<K,V>> words = new ArrayList<Entry<K,V>>();
		
		@Override
		public List<? extends MyHashMap.Entry<K, V>> getEntries() {
			return words;
		}

		
		@Override
		public Entry<K, V> getElement(K key) {
			for(Entry<K,V> aux :words){
				if(aux.getKey().equals(key))
					return aux;
			}
			return null;	
		}

		@Override
		public void addEntry(final K key, final V value ) {
			
			Entry<K,V> to_add = new MyEntry<K, V>(key, value);
			words.add(to_add); // add an entry to the bucket			
		}
		
		
		@Override
		public V remove(K key) {
			/*
			 * Because we could have more than one word 
			 * in a bucket we must identify the word to be
			 * deleted
			 */
			
			for(int i = 0 ; i < words.size() ;i ++){
				if(words.get(i).getKey().equals(key))
					return words.remove(i).getValue();
			}
			return null;
		}



		@Override
		public int getSize() {
			return words.size();
		}

		

	}

	
}



