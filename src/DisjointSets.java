import hashMap.MyHashMap;
import hashMap.MyHashMapImpl;

import java.util.ArrayList;

public class DisjointSets<T> {
	private ArrayList<Entry<T>> elements;
	private MyHashMap<T, Integer> indexes; 
	
	private class Entry<T> {
		public T element;
		public int setIndex;
		public  Entry(T element) {
			this.element = element;
			setIndex = elements.size();
		}
 	}
	
	public DisjointSets() {
	}
	
	public DisjointSets(int length ){
		this.indexes = new MyHashMapImpl<T, Integer>(length);
	}
	
	
	public void addElement(T elem) {
		Entry<T> new_entry = new Entry<T>(elem);
		elements.add(new_entry);
		indexes.put(new_entry.element, new_entry.setIndex);
	}
	
	public void mergeSetsOf(T e1, T e2) {
		int index1 = getIndexOf(e1);
		int index2 = getIndexOf(e2);
		
		changeIndexes(index1 ,index2);
	}
	
	public ArrayList<T> setOf(T elem) {
		return null;
	}
	
	private int getIndexOf(T element){
		for(Entry<T> aux : elements){
			if(aux.element.equals(element))
				return aux.setIndex;
		}
		return Integer.MIN_VALUE ;
	}
	
	private void changeIndexes(int index1 , int index2 ){
		
		int minIndex = Math.min(index1, index2);
		int maxIndex = Math.max(index1, index2);
				
		for(Entry<T> aux : elements)
			if(aux.setIndex == maxIndex){
				
				aux.setIndex = minIndex;
				
				indexes.remove(aux.element);
				indexes.put(aux.element,minIndex);
			}
	}
	
	
}