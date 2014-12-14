package word;

public class Entry {
	private String name ;
	private String definition ;

	
	public Entry(String name , String definition){
		this.name = name ;
		this.definition = definition;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDefinition() {
		return definition;
	}


	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	
}
