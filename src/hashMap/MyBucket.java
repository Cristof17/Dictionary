package hashMap;

import java.util.ArrayList;
import java.util.List;

import hashMap.MyHashMap.Bucket;
import hashMap.MyHashMap.Entry;

public class MyBucket<K,V> implements Bucket<K,V>{

	private ArrayList<Entry<K, V>> words = new ArrayList<MyHashMap.Entry<K,V>>();
	
	@Override
	public List<? extends hashMap.MyHashMap.Entry<K, V>> getEntries() {
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
		for(Entry<K,V> e :words)
			if(e.getKey().equals(key) && e.getValue().equals(value))
				//the object already exists so there is no need
				//to add another one like it
				return;
		
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

	@Override
	public int getNumberOfDefinitions(K key) {
		//here we are sure that we do not have duplicates
		return words.size();
	}
	

}
