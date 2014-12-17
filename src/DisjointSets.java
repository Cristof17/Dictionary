

import java.util.ArrayList;

public class DisjointSets<T> {
	private ArrayList<Entry<T>> elements;
	private MyHashMap<T, Integer> indexes; 
	private int length;
	
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
		this.elements = new ArrayList<Entry<T>>();
		this.length = length;
	}
	
	/**
	 * This method adds an element to the array of elements and
	 * also to the indexes HashMap 
	 * @param elem
	 */
	public void addElement(T elem) {
		Entry<T> new_entry = new Entry<T>(elem);
		elements.add(new_entry);
		indexes.put(new_entry.element, new_entry.setIndex);
	}
	
	/**
	 * This method merges two sets c
	 * @param e1 The element from the first set 
	 * @param e2 The element from the second set
	 */
	public void mergeSetsOf(T e1, T e2) {
		int index1 = getIndexOf(e1);
		int index2 = getIndexOf(e2);
		
		changeIndexes(index1 ,index2);
	}
	
	
	/**
	 * This method returns the set from which the element given as parameter
	 * is part of
	 * @param elem The element for which the set is to be returned
	 * @return The set of the element given as parameter
	 */
	public ArrayList<T> setOf(T elem) {
		int indexOfElement = indexes.get(elem);
		ArrayList<T> set = new ArrayList<T>();
		
		for(Entry<T> aux : elements)
			if(aux.setIndex == indexOfElement && !aux.element.equals(elem))
				set.add(aux.element);
			
		return set;
	}
	
	/**
	 * This method returns the set index of a given element
	 * @param element The element for which to return the given set index
	 * @return The index of the set of the given element
	 */
	private int getIndexOf(T element){
		for(Entry<T> aux : elements){
			if(aux.element.equals(element))
				return aux.setIndex;
		}
		return Integer.MIN_VALUE ;
	}
	
	
	/**
	 * This method changes the indexes of the two sets' indexes
	 * @param index1 The index of the first set
	 * @param index2 The index of the second set
	 */
	private void changeIndexes(int index1 , int index2 ){
		
		int minIndex = Math.min(index1, index2);
		int maxIndex = Math.max(index1, index2);
				
		for(Entry<T> aux : elements)
			if(aux.setIndex == maxIndex){
				aux.setIndex = minIndex;
				indexes.remove(aux.element);
				indexes.put(aux.element ,aux.setIndex);
			}
	}
	
}