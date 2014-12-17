

public class Main {

	private static Dictionary dictionary;
	
	public static void main(String args[]) {

		dictionary = new Dictionary(Integer.parseInt(args[0]));
		dictionary.readDefinitionsFromFile(args[1]);
		dictionary.executeQueriesFromFile(args[2], args[3]);

	}
}