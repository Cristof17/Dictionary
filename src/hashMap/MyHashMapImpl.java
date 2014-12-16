package hashMap;


import java.util.ArrayList;
import java.util.List;
import hashMap.MyHashMap.Bucket;;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V>{

	private ArrayList<Bucket<K,V>>array;
	private int numberOfBuckets ;
	
	public MyHashMapImpl(int length){
		this.numberOfBuckets = length ;
		initializeArray();
	}
	
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
		while(bucket.getSize() != 0)
			bucket.remove(key);
		
		return value;
	}
	

	@Override
	public int size() {
		return numberOfBuckets;
	}

	@Override
	public List<? extends hashMap.MyHashMap.Bucket<K, V>> getBuckets() {
		return array;
	}
	
	private int translate(int hashCode){
		return Math.abs(hashCode ) % numberOfBuckets; 
	}

}
