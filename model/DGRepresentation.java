/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import edu.uci.ics.jung.graph.Graph;

/**
 *
 * @author User
 */
public class DGRepresentation implements Representation {
    private DiachronicGraphVisualRepresentation visualizationOfDiachronicGraph;
    
    public DGRepresentation(DiachronicGraph p,String sql,String xml,String graphml, String targetFolder, int et, double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY) throws Exception {
		
		visualizationOfDiachronicGraph = new DiachronicGraphVisualRepresentation(p,sql,targetFolder,et,p.getMode(),frameX,frameY,scaleX,scaleY,centerX,centerY);
    }
    
    /* (non-Javadoc)
	 * @see model.Representation#setPickingMode()
	 */
    @Override
	public void setPickingMode(){
		
    	visualizationOfDiachronicGraph.setPickingMode();
		
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#setTransformingMode()
	 */
    @Override
	public void setTransformingMode(){
		
        visualizationOfDiachronicGraph.setTransformingMode();
		
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#saveVertexCoordinates(java.lang.String)
	 */
    @Override
	public void saveVertexCoordinates(String projectIni) throws IOException{

	visualizationOfDiachronicGraph.saveVertexCoordinates(projectIni);
		
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#stopConvergence()
	 */
    @Override
	public void stopConvergence(){
		
        visualizationOfDiachronicGraph.stop();
    }

    /* (non-Javadoc)
	 * @see model.Representation#getTargetFolder()
	 */
    @Override
	public String getTargetFolder(){
		
        return visualizationOfDiachronicGraph.getTargetFolder();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#visualizeDiachronicGraph(edu.uci.ics.jung.visualization.VisualizationViewer)
	 */
    @Override
	public void visualizeDiachronicGraph(VisualizationViewer< String, String> vv){
		
        visualizationOfDiachronicGraph.createEpisode(vv);
		
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#visualizeIndividualDBVersions(edu.uci.ics.jung.visualization.VisualizationViewer, java.lang.String, int, java.util.ArrayList, java.util.concurrent.ConcurrentHashMap)
	 */
    @Override
	public void visualizeIndividualDBVersions(VisualizationViewer< String, String> vv,String targetFolder,int edgeType, ArrayList<DBVersion> versions, ConcurrentHashMap<String, Table> graph){
		
        int width = visualizationOfDiachronicGraph.getWidthOfVisualizationViewer();
        int height = visualizationOfDiachronicGraph.getHeightOfVisualizationViewer();
		
        for(int i=0;i<versions.size();++i){
            versions.get(i).setDetails(targetFolder, edgeType,width,height);
            versions.get(i).visualizeEpisode(vv,this,graph);
        }
		
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#show()
	 */
    @Override
	public VisualizationViewer show(){
        return visualizationOfDiachronicGraph.show();
    }
	
	
    /* (non-Javadoc)
	 * @see model.Representation#getUniversalFrame()
	 */
    @Override
	public Dimension getUniversalFrame(){	
    	return visualizationOfDiachronicGraph.getUniversalFrame();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getUniversalCenter()
	 */
    @Override
	public Point2D getUniversalCenter(){
    	return visualizationOfDiachronicGraph.getUniversalCenter();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getScaleX()
	 */
    @Override
	public double getScaleX(){
    	return visualizationOfDiachronicGraph.getScaleX();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getScaleY()
	 */
    @Override
	public double getScaleY(){	
    	return visualizationOfDiachronicGraph.getScaleY();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getFrameX()
	 */
    @Override
	public  double getFrameX(){	
    	return visualizationOfDiachronicGraph.getFrameX();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getFrameY()
	 */
    @Override
	public  double getFrameY(){
    	return visualizationOfDiachronicGraph.getFrameY();
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#refresh(double, int)
	 */
    @Override
	public Component refresh(double forceMult, int repulsionRange) {
        return this.visualizationOfDiachronicGraph.refresh(forceMult,repulsionRange);
    }
	
    /* (non-Javadoc)
	 * @see model.Representation#getUniversalBounds()
	 */
    @Override
	public Rectangle getUniversalBounds(){
    	return visualizationOfDiachronicGraph.getUniversalBounds();
    }

    /* (non-Javadoc)
	 * @see model.Representation#clear()
	 */
    @Override
	public void clear(){
        visualizationOfDiachronicGraph=null;
    }
    
}
