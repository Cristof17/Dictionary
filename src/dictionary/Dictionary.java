package dictionary;

import hashMap.MyHashMapImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import disjointSets.DisjointSets;

public class Dictionary {

	private String[] args;
	private File fisierLista;
	private File fisierInterogari ;
	private File fisierOut ;
	private MyHashMapImpl<String,ArrayList<String>> hashMap;
	private DisjointSets<String> sets ;
	
	public Dictionary(String[] args){
		
		
		
		this.args = args ;
		this.hashMap = new MyHashMapImpl<String,ArrayList<String>>(Integer.parseInt(args[0]));
		this.sets = new DisjointSets<String>(Integer.parseInt(args[0]));
		
		this.fisierLista = new File(args[1]);
		this.fisierInterogari = new File(args[2]);
		this.fisierOut = new File(args[3]);
		
		readDefinitions(hashMap, fisierLista);
		readQuery(fisierInterogari);
		
	}
	
	
	
	private void readQuery(File fisierInterogari) {
		try {
			
			FileReader stream = new FileReader(fisierInterogari);
			BufferedReader reader = new BufferedReader(stream);
			
			int Q = Integer.parseInt(reader.readLine());
			Scanner sc = null ;
			for(int i = 0 ; i < Q ; i++){
				 sc = new Scanner(reader.readLine());
				switch (sc.nextInt()) {
				case 0:
					System.out.println(getDefinition(sc.next()));
					break;
				case 1:
					System.out.println(getNumberOfDefinitions(sc.next()));
					break;
				case 2:
					String element = sc.next();
					ArrayList<String> synonyms = getSynonyms(element);
					System.out.println("Synonyms for " +element);
					for(int j = 0 ; j < synonyms.size() ; j++){
						if(j == synonyms.size() -1){
							System.out.println(synonyms.get(j-1)+" ");
						}else
							System.out.print(synonyms.get(j)+" ");
					}
					break;

				default:
					break;
				}
			
			}
		
			sc.close();
			reader.close();
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
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
	
}
