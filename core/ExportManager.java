package core;

import export.HScript;
import export.HScriptFactory;
import export.PowerPointGenerator;
import export.VideoGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExportManager {
	
	public ExportManager(){}
	
	public void createTransitions(File file) throws Exception{
		HScriptFactory HSF = new HScriptFactory();
		HScript hecate = HSF.getHScript("HecateScript", file);
		hecate.createTransitions();
		hecate=null;
		
	}
	
	public void createPowerPointPresentation(ArrayList<String> FileNames,String targetFolder,String projectName) throws FileNotFoundException, IOException{
		PowerPointGenerator pptx=new PowerPointGenerator(targetFolder,projectName);			
		pptx.createPresentation(FileNames);
		pptx=null;
	}
	
	public void createVideo(File file) throws IOException{
		VideoGenerator vg = new VideoGenerator(file);
		vg.exportToVideo();
		vg=null;
		
	}
}
