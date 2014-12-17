package dictionary;

import hashMap.MyHashMapImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import disjointSets.DisjointSets;

public class Dictionary {

	private int size ;
	private MyHashMapImpl<String,ArrayList<String>> hashMap;
	private DisjointSets<String> sets ;
	
	public Dictionary(int size){
		
		
		this.size = size;
		this.hashMap = new MyHashMapImpl<String,ArrayList<String>>(size);
		this.sets = new DisjointSets<String>(size);
		

		
	}
	
	
	
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
					writer.write(definitions +"\r\n");
					System.out.println(definitions);
					break;
				case 1:
					String value = sc.next();
					int numberOfDefinitions = getNumberOfDefinitions(value);
					writer.write(numberOfDefinitions+"\r\n");
					System.out.println(numberOfDefinitions);
					break;
				case 2:
					String element = sc.next();
					ArrayList<String> synonyms = getSynonyms(element);
					Collections.sort(synonyms);
					for(String s : synonyms){
							writer.write(s+" ");
							System.out.print(s+" ");
					}
					writer.write("\r\n");
					System.out.print("\r\n");
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

	private String getDefinition(String key ){
		ArrayList<String >definitions = hashMap.get(key);
		if(definitions == null)
			return "\r\n";
		
		return definitions.get(0);
		
	}
	
	private int getNumberOfDefinitions(String key){
		ArrayList<String> definitions = hashMap.get(key);
		
		if(definitions == null)
			return 0;
		return hashMap.get(key).size();
	}
	
	
	private ArrayList<String> getSynonyms(String element){
		return sets.setOf(element);
	}
	
	public void readDefinitionsFromFile(String file_path){
		File fisierLista = new File(file_path);
		readDefinitions(hashMap, fisierLista);
	}
	
	public void executeQueriesFromFile(String file_path ,String output_path){
		File fisierInterogari = new File(file_path);
		readQuery(fisierInterogari,output_path);
	}
}
