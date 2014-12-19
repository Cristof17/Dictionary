
import java.util.List;

/**
 * Interfata pentru o tabela de dispersie asociativa.
 * 
 * @author Mihnea
 *
 * @param <K> tipul cheilor
 * @param <V> tipul valorilor
 */
public interface MyHashMap<K, V> {
	
	/**
	 * Obtine valoarea asociata cheii key.
	 * 
	 * @param key
	 * 			cheia cautata
	 * @return valoarea SAU null daca cheia nu exista
	 */
	public V get(K key);
	
	/**
	 * Adauga o asociere cheie-valoare.
	 * 
	 * @param key
	 * @param value
	 * @return valoarea anterioara asociata cheii key SAU null daca cheia nu exista
	 */
	public V put(K key, V value);
	
	/**
	 * Inlatura asocierea.
	 * 
	 * @param key
	 * @return valoarea asociata cu cheia key SAU null daca cheia nu exista
	 */
	public V remove(K key);
	
	/**
	 * Intoarce dimensiunea tabelei.
	 *  
	 * @return numarul de chei
	 */
	public int size();
	
	/**
	 * Intoarce lista de bucket-uri din tabela.
	 * 
	 * @return Lista de bucket-uri
	 */
	public List<? extends Bucket<K, V>> getBuckets();
	
	/**
	 * Intrare in tabela de dispersie (asociere cheie-valoare).
	 * 
	 * @author Mihnea
	 *
	 * @param <K>
	 * @param <V>
	 */
	public static interface Entry<K, V> {
		
		
		/**
		 * Intoarce cheia intrarii.
		 * 
		 * @return Cheia intrarii
		 */
		public K getKey();
		
		/**
		 * Intoarce valoarea intrarii.
		 * 
		 * @return Valoarea intrarii
		 */
		public V getValue();
	}
	
	/**
	 * Bucket al tabelei de dispersie.
	 * 
	 * @author Mihnea
	 *
	 * @param <K>
	 * @param <V>
	 */
	public static interface Bucket<K, V> {
		
		

		/**
		 * Intoarce lista de intrari continute de acest bucket.
		 * 
		 * @return Lista de intrari
		 */
		public List<? extends Entry<K, V>> getEntries();
		
		public Entry<K,V> getElement(K key);
		
		public void addEntry(K key , V value );
		
		public V remove(K key);
		
		public int getSize();
		
	}
}
