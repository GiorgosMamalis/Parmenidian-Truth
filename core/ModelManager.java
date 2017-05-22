package core;


import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import model.*;

public class ModelManager {
	
	private DiachronicGraph diachronicGraph;
	private Representation diachronicGraphRepresentation;
	private RepresentationFactory DGRFactory = new RepresentationFactory();

	
	public ModelManager() {	}
	
	public void clear(){
		if(diachronicGraphRepresentation!=null)		
			diachronicGraphRepresentation.clear();
		if(diachronicGraph!=null)
			diachronicGraph.clear();
	}
	
	public String getTargetFolder(){
		
		return diachronicGraphRepresentation.getTargetFolder();
		
	}
	
	public void stopConvergence(){
		
		diachronicGraphRepresentation.stopConvergence();
		
	}
	
	public void saveVertexCoordinates(String projectIni) throws IOException{
		
		diachronicGraphRepresentation.saveVertexCoordinates(projectIni);
		
	}
	
	public void setTransformingMode(){
		
		diachronicGraphRepresentation.setTransformingMode();
		
	}
	
	public void setPickingMode(){
		
		diachronicGraphRepresentation.setPickingMode();
		
	}
	
	
	public void visualize(VisualizationViewer< String, String> vv,String projectIni,String targetFolder,int edgeType) throws IOException {
		diachronicGraphRepresentation.saveVertexCoordinates(projectIni);
		diachronicGraphRepresentation.visualizeIndividualDBVersions(vv,targetFolder,edgeType, diachronicGraph.getVersions(), diachronicGraph.getDictionaryOfGraph());
		diachronicGraphRepresentation.visualizeDiachronicGraph(vv);

	}
	
	public Component loadProject(String sql,String xml,String graphml, double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY,String targetFolder,int edgeType) throws Exception{
		diachronicGraph = new DiachronicGraph(sql,xml,graphml,targetFolder,edgeType,frameX,frameY,scaleX,scaleY,centerX,centerY);
		diachronicGraphRepresentation = DGRFactory.createRepresentation("DGRepresentation", diachronicGraph,sql,xml,graphml,targetFolder,edgeType,frameX,frameY,scaleX,scaleY,centerX,centerY);
		return diachronicGraphRepresentation.show();
		
	}

	public Component refresh(double forceMult, int repulsionRange) {
		
		return diachronicGraphRepresentation.refresh(forceMult,repulsionRange);
		
	}
	
	public void generateReports(String targetFolder, ArrayList<DGMetric> metrics) throws FileNotFoundException {
		for(int i = 0 ; i < metrics.size() ; i++)
			diachronicGraph.executeFactory(targetFolder, metrics.get(i));
	}
	

}
