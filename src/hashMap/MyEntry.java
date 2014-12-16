package hashMap;

import hashMap.MyHashMap.Entry;

import java.util.ArrayList;

public class MyEntry<K,V> implements Entry<K,V> {

	private K key;
	private V definition ;
	
	public MyEntry(K key ,V value ){
		this.key = key ;
		this.definition = value ;
	}
	
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return definition;
	}
	

}
