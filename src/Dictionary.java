



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Dictionary {

	private int size ;
	private MyHashMapImpl<String,ArrayList<String>> hashMap;
	private DisjointSets<String> sets ;
	
	public Dictionary(int size){
		
		
		this.size = size;
		this.hashMap = new MyHashMapImpl<String,ArrayList<String>>(size);
		this.sets = new DisjointSets<String>(size);
		

		
	}
	
	
	/**
	 * This method reads the query File , processes every query
	 * and then outputs the results to the file created from the
	 * path given as parameter 
	 * 
	 * @param fisierInterogari The query file from which every query is read  
	 * @param output_path The output file path 
	 */
	private void readQuery(File fisierInterogari,String output_path) {
		
		try {
			
			FileReader stream_in = new FileReader(fisierInterogari);
			BufferedReader reader = new BufferedReader(stream_in);
			FileWriter stream_out = new FileWriter(output_path);
			BufferedWriter writer = new BufferedWriter(stream_out);
			
			int Q = Integer.parseInt(reader.readLine());
			Scanner sc = null ;
			for(int i = 0 ; i < Q ; i++){
				 sc = new Scanner(reader.readLine());
				switch (sc.nextInt()) {
				case 0:
					String definitions = getDefinition(sc.next());
					writer.write(definitions +"\n");
					break;
				case 1:
					String value = sc.next();
					int numberOfDefinitions = getNumberOfDefinitions(value);
					writer.write(numberOfDefinitions+"\n");
					break;
				case 2:
					String element = sc.next();
					ArrayList<String> synonyms = getSynonyms(element);
					Collections.sort(synonyms);
					for(String s : synonyms){
							writer.write(s+" ");
					}
					writer.write("\n");
					break;

				default:
					break;
				}
			
			}
		
			writer.flush();
			
			sc.close();
			writer.close();
			stream_out.close();
			reader.close();
			stream_in.close();
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}


	/**This method reads the Dictionary content file and 
	 * puts the data into a HashMap data structure 
	 * 
	 * @param hashMap  The hashMap data structure in which data is inserted
	 * @param file The file from which data is read
	 */
	private void readDefinitions(MyHashMapImpl<String,ArrayList<String>> hashMap ,File file){
		FileReader stream = null;
		
		int N =0, M = 0 ;
		try {
			
			stream = new FileReader(file);
			
			
			/*
			 * Read the first line
			 */
			BufferedReader reader= new BufferedReader(stream);
			String line = reader.readLine(); 
			Scanner line_scanner  = new Scanner(line);
			N = line_scanner.nextInt();
			M = line_scanner.nextInt();
			/* 
			 * End of reading the first line 
			 */
			
			
			
			/*
			 * Now read the next N lines
			 */
			String word ;
			String definition ;
			
			for(int i = 0 ; i < N ; i ++){
				
				word = reader.readLine() ;
				definition = reader.readLine();
				
				if(hashMap.get(word) != null && hashMap.get(word).size() != 0){
					/*
					 * Check to see if there is another definition like this one
					 */
					ArrayList<String> definitions = hashMap.get(word);
					
					if(definitions.contains(definition))
						continue; 
					else
						definitions.add(definition);
					
				}else{
					ArrayList<String> definitions = new ArrayList<String>();
					definitions.add(definition);
					hashMap.put(word, definitions);
					sets.addElement(word); 
				}
			
			}
			
			for(int i = 0 ; i < M ; i++){
				line_scanner = new Scanner(reader.readLine());
				
				String word1 = line_scanner.next();
				String word2 = line_scanner.next();

				sets.mergeSetsOf(word1, word2);				
				
			}
			
			reader.close();
			line_scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("File  not found");
		}
		
	}	

	/**This method returns the definitions  of a word
	 * in dictionary based on its key 
	 * 
	 * @param key The key for which to get the definitions
	 * @return The definitions of the word searched by key
	 */
	private String getDefinition(String key ){
		ArrayList<String >definitions = hashMap.get(key);
		if(definitions == null)
			return "\r\n";
		
		return definitions.get(0);
		
	}

	
	/**This method returns the number of definitions
	 * a word can have in the dictionary
	 * 
	 * @param key The key used to get the definitions of a word
	 * @return The number of definitions a word can have
	 */
	private int getNumberOfDefinitions(String key){
		ArrayList<String> definitions = hashMap.get(key);
		
		if(definitions == null)
			return 0;
		return hashMap.get(key).size();
	}
	
	/**This method returns the synonyms of a word from the Dictionary 
	 * 
	 * @param element The String word to search for in the Dictionary
	 * @return The ArrayList containing all the synonyms of the word sent as parameter
	 */
	private ArrayList<String> getSynonyms(String element){
		return sets.setOf(element);
	}
	
	/**This method is a wrapper onto readDefinitions method
	 * in order for a Dictionary object to call this method and
	 * to trigger the start of populating the hashMap 
	 * 
	 * @param file_path The path of the configuration file
	 */
	public void readDefinitionsFromFile(String file_path){
		File fisierLista = new File(file_path);
		readDefinitions(hashMap, fisierLista);
	}
	
	/**This method is a wrapper onto readQuery method
	 * in order for a Dictionary object to call this method and
	 * to trigger the start of extracting data from hashMap
	 * based on the Query file
	 * 
	 * @param file_path The path of the Query file
	 * @param output_path The path of the output file where the data processed is to be inserted
	 */
	public void executeQueriesFromFile(String file_path ,String output_path){
		File fisierInterogari = new File(file_path);
		readQuery(fisierInterogari,output_path);
	}
}
