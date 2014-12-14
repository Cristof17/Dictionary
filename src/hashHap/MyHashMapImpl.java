package hashHap;


import java.util.ArrayList;
import java.util.List;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V>{

	private ArrayList<Bucket<K,V>> array;
	private int numberOfBuckets ;
	
	public MyHashMapImpl(int length){
		this.numberOfBuckets = length ;
		initializeArray();
	}
	
	private void initializeArray() {
		
		if(array == null)
			array = new ArrayList<MyHashMap.Bucket<K,V>> ();
		
		for(int i= 0 ; i < numberOfBuckets ; i++)
			array.add(new Bucket<K,V>() {
				
				private ArrayList<Entry<K, V>> words = new ArrayList<MyHashMap.Entry<K,V>>();
				
				@Override
				public List<? extends hashHap.MyHashMap.Entry<K, V>> getEntries() {
					return words;
				}

				
				
				@Override
				public Entry<K, V> getElement(K key) {	
					return words.get(0);
				}

				@Override
				public void addEntry(final K key, final V value ) {
					
					Entry<K, V> to_add = new Entry<K, V>() {

						@Override
						public K getKey() {
							return key ;
						}

						@Override
						public V getValue() {	
							return value ;
						}
					};
					
					words.add(to_add); // add an entry to the bucket			
				}



				@Override
				public V remove(K key) {
					Entry<K,V> entry = words.remove(0);
					
					//remove the rest of the words if there are any with 
					//the same values as the one deleted ;
					
					return entry.getValue();
				}



				@Override
				public int getSize() {
					return words.size() ;
				}
				
			});
		
	}

	@Override
	public V get(K key) {
		
		return array.get(translate(key.hashCode())).getElement(key).getValue();
	}

	@Override
	public V put(K key, V value) {
		if(array == null)
			array = new ArrayList<Bucket<K,V>>(numberOfBuckets);
		array.get(translate(key.hashCode())).addEntry(key ,value);
		return null;
	}

	@Override
	public V remove(K key) {
		int position = translate(key.hashCode());
		Bucket<K,V> bucket = array.get(position);
		
		V value = bucket.remove(key);
		while(bucket.getSize() != 0)
			bucket.remove(key);
		
		return value;
	}

	@Override
	public int size() {
		return numberOfBuckets;
	}

	@Override
	public List<? extends hashHap.MyHashMap.Bucket<K, V>> getBuckets() {
		return array;
	}
	
	private int translate(int hashCode){
		return Math.abs(hashCode ) % numberOfBuckets; 
	}

}
