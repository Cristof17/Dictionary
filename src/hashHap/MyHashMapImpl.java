package hashHap;

import java.util.ArrayList;
import java.util.List;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V>{

	private ArrayList<Bucket<K,V>> array;
	private int numberOfBuckets ;
	private int length ;
	
	public MyHashMapImpl(int length){
		this.length = length ;
	}
	
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
		if(array == null)
			array = new ArrayList<Bucket<K,V>>(length);
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
