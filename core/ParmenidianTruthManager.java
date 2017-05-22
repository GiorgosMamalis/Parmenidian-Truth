package core;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import parmenidianEnumerations.Metric_Enums;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import model.DGMetric;
import model.OperationClusteringCoefficientReport;
import model.OperationConnectedComponentsCountReport;
import model.OperationEdgeBetweennessReport;
import model.OperationEdgeCountReport;
import model.OperationEdgeCountReportForGcc;
import model.OperationGraphDiameterReport;
import model.OperationVertexBetweenessReport;
import model.OperationVertexCountReport;
import model.OperationVertexCountReportForGcc;
import model.OperationVertexDegreeReport;
import model.OperationVertexInDegreeReport;
import model.OperationVertexOutDegreeReport;

public class ParmenidianTruthManager implements Manager {
	
	private ModelManager modelManager;
	private ExportManager exportManager;
	
	public ParmenidianTruthManager(){
		modelManager = new ModelManager();
		exportManager = new ExportManager();	
	}
	
	@Override
	public void clear(){	
		modelManager.clear();
	}
	
	@Override
	public String getTargetFolder(){
		return modelManager.getTargetFolder();
	}
	
	@Override
	public void stopConvergence(){
		modelManager.stopConvergence();
	}
	
	@Override
	public void saveVertexCoordinates(String projectIni) throws IOException{
		modelManager.saveVertexCoordinates(projectIni);
	}
	
	@Override
	public void setTransformingMode(){	
		modelManager.setTransformingMode();
	}
	
	@Override
	public void setPickingMode(){
		modelManager.setPickingMode();
	}
	
	@Override
	public void visualize(VisualizationViewer< String, String> vv,String projectIni,String targetFolder,int edgeType) throws IOException {	
		modelManager.visualize(vv,projectIni, targetFolder, edgeType);
	}
	
	@Override
	public Component loadProject(String sql,String xml,String graphml, double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY,String targetFolder,int edgeType) throws Exception{
		return modelManager.loadProject( sql, xml, graphml,  frameX, frameY, scaleX, scaleY, centerX, centerY, targetFolder, edgeType);
	}
	
	@Override
	public void createTransitions(File selectedFile) throws Exception{
		exportManager.createTransitions(selectedFile);
	}
			
	@Override
	public void createPowerPointPresentation(ArrayList<String> FileNames,String targetFolder,String projectName) throws FileNotFoundException, IOException{
		exportManager.createPowerPointPresentation(FileNames, targetFolder, projectName);
	}
	
	@Override
	public void createVideo(File file) throws IOException{
		exportManager.createVideo(file);
	}
	


	@Override
	public Component refresh(double forceMult, int repulsionRange) {
		return modelManager.refresh(forceMult,repulsionRange);
	}

    @Override
    public void calculateMetrics(String targetFolder,ArrayList<Metric_Enums> metrics) throws FileNotFoundException {
    	ArrayList<DGMetric> dGMetricsList = new ArrayList<DGMetric>();
    	for (Metric_Enums metric : metrics) {
    		if (metric == Metric_Enums.VERTEX_IN_DEGREE)
    			dGMetricsList.add(new OperationVertexInDegreeReport());
    		else if (metric == Metric_Enums.VERTEX_OUT_DEGREE)
    			dGMetricsList.add(new OperationVertexOutDegreeReport());
    		else if (metric == Metric_Enums.VERTEX_DEGREE)
    			dGMetricsList.add(new OperationVertexDegreeReport());
    		else if (metric == Metric_Enums.VERTEX_BETWEENNESS)
    			dGMetricsList.add(new OperationVertexBetweenessReport());
    		else if (metric == Metric_Enums.CLUSTERING_COEFFICIENT)
    			dGMetricsList.add(new OperationClusteringCoefficientReport());
    		else if (metric == Metric_Enums.EDGE_BETWEENNESS)
    			dGMetricsList.add(new OperationEdgeBetweennessReport());
    		else if (metric == Metric_Enums.GRAPH_DIAMETER)
    			dGMetricsList.add(new OperationGraphDiameterReport());
    		else if (metric == Metric_Enums.NUMBER_OF_VERTICES)
    			dGMetricsList.add(new OperationVertexCountReport());
    		else if (metric == Metric_Enums.NUMBER_OF_EDGES)
    			dGMetricsList.add(new OperationEdgeCountReport());
    		else if (metric == Metric_Enums.NUMBER_OF_CONNECTED_COMPONENTS)
    			dGMetricsList.add(new OperationConnectedComponentsCountReport());
    		else if (metric == Metric_Enums.NUMBER_OF_VERTICES_IN_GCC)
    			dGMetricsList.add(new OperationVertexCountReportForGcc());
    		else if (metric == Metric_Enums.NUMBER_OF_EDGES_IN_GCC)
    			dGMetricsList.add(new OperationEdgeCountReportForGcc());
    	}
    	modelManager.generateReports(targetFolder, dGMetricsList);
    	//modelManager.getArticulationVertices();
    }
}
