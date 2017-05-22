/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import parmenidianEnumerations.Metric_Enums;
/**
 *
 * @author User
 */
public interface Manager {
	void clear();
	String getTargetFolder();
	void stopConvergence();
	void saveVertexCoordinates(String projectIni)throws IOException;
	void setTransformingMode();
	void setPickingMode();
	void visualize(VisualizationViewer< String, String> vv,String projectIni,String targetFolder,int edgeType)throws IOException;
	Component loadProject(String sql,String xml,String graphml, double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY,String targetFolder,int edgeType)throws Exception;
	void createTransitions(File selectedFile)throws Exception;
	void createPowerPointPresentation(ArrayList<String> FileNames,String targetFolder,String projectName)throws IOException;
	void createVideo(File file)throws IOException;
	Component refresh(double forceMult, int repulsionRange);
	void calculateMetrics(String targetFolder,ArrayList<Metric_Enums> metrics)throws IOException;
}

