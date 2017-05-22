package export;

import java.io.File;
import java.io.FileFilter;

import model.HecateManager;

public class HecateScript implements HScript{	
	private File selectedDirectory;
	private File[] sqlFiles;
	private HecateManager worker = new HecateManager();
	
	public HecateScript(File folder) throws Exception{
		Exception wrong = new Exception();

		selectedDirectory=folder;
		sqlFiles = selectedDirectory.listFiles(new SQLFileFilter());
		
		if(sqlFiles.length==0){
			throw wrong;
		}
		
		
	}
	
	public void createTransitions(){
		worker.createTransitions(sqlFiles,selectedDirectory);
	}
	
	public class SQLFileFilter implements FileFilter {
		
		public boolean accept(File pathname) {
			if(pathname.getName().endsWith(".sql"))
				return true;
			return false;
			
		}
		
	}
	

}
