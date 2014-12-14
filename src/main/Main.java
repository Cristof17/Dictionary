package main;

import hashHap.MyHashMapImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import word.Entry;

public class Main {

	public static void main(String args[]){
		
		/*
		 * Read the arguments 
		 */
		
		MyHashMapImpl<Integer, String> hashMap = new MyHashMapImpl<Integer,String>(Integer.parseInt(args[0]));
		File fisierLista = new File(args[1]);
		File fisierInterogari = new File(args[2]);
		File fisierOut = new File(args[3]);
	
		
		
		
	}
	
	private static void readDefinitions(MyHashMapImpl<String,String> hashMap ,File file){
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
				hashMap.put(word, definition);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("File  not found");
		}
		
	}
}
