package model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import edu.uci.ics.jung.visualization.VisualizationViewer;

public interface Representation {
	
	void setPickingMode();
	void setTransformingMode();
	void saveVertexCoordinates(String projectIni) throws IOException;
	void stopConvergence();
	String getTargetFolder();
	void visualizeDiachronicGraph(VisualizationViewer<String, String> vv);
	void visualizeIndividualDBVersions(VisualizationViewer<String, String> vv, String targetFolder, int edgeType,
	ArrayList<DBVersion> versions, ConcurrentHashMap<String, Table> graph);
	VisualizationViewer show();
	Dimension getUniversalFrame();
	Point2D getUniversalCenter();
	double getScaleX();
	double getScaleY();
	double getFrameX();
	double getFrameY();
	Component refresh(double forceMult, int repulsionRange);
	Rectangle getUniversalBounds();
	void clear();
	
}