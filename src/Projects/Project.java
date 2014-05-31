package Projects;

import java.util.ArrayList;

public class Project {
	
	private ArrayList<String> files;
	
	public Project(){
		
		files = new ArrayList<String>();
	}
	
	public void addFile(String path){
		files.add(path);
	}
	
	public void removeFile(String path){
		files.remove(path);
	}

}
