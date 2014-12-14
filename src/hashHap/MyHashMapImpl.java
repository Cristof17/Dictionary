package hashHap;

import hashHap.MyHashMap.Entry;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<? extends hashHap.MyHashMap.Bucket<K, V>> getBuckets() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int translate(int hashCode){
		return Math.abs(hashCode ) % numberOfBuckets; 
	}

}
