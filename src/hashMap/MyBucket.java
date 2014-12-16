package hashMap;

import java.util.ArrayList;
import java.util.List;

import hashMap.MyHashMap.Bucket;
import hashMap.MyHashMap.Entry;

public class MyBucket<K,V> implements Bucket<K,V>{

	private ArrayList<Entry<K,V>> words = new ArrayList<Entry<K,V>>();
	
	@Override
	public List<? extends hashMap.MyHashMap.Entry<K, V>> getEntries() {
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
		 * delted
		 */
		Entry<K,V> to_delete = null ;
		for(Entry<K,V> aux : words){
			if(aux.getKey().equals(key))
				 to_delete = aux ;
				 words.remove(aux);
		}
		
		return to_delete.getValue();
	}



	@Override
	public int getSize() {
		return words.size();
	}

	

}
