package model;




import java.util.ArrayList;
import java.util.Map;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * @author libathos
 *h klash Table einai ths Ekaths kai afora thn sql anaparastash twn table
 *h dikia m klash table edw einai model.table gia na apofigoume ta conflicts
 *kai periexei plirofories sxetika me thn anaparastash tou table ws komvo
 *
 */
public class DBVersion  {
	
	private String versionName;
	private ArrayList<Table> tablesWithin=new ArrayList<Table>();
	private ArrayList<ForeignKey> versionForeignKeys= new ArrayList<ForeignKey>();
	private DBVersionVisualRepresentation visualizationsOfDBVersion;
	private GraphMetrics graphMetricsOfDBVersion;

	public DBVersion(ArrayList<Table> tablesWithin,ArrayList<ForeignKey> versionForeignKeys,String vn){
		
		//1 set VersionName				
		String[] array =vn.split(".sql",2);
		versionName=array[0];
		
		//2 set all the tables of the current version		
		this.tablesWithin=tablesWithin;

		
		//3 set all the FK dependencies of the current version
		 this.versionForeignKeys=versionForeignKeys;

		 graphMetricsOfDBVersion = new GraphMetrics(tablesWithin,versionForeignKeys);
		 visualizationsOfDBVersion = new DBVersionVisualRepresentation(this); 
		
	}
	
	public void visualizeEpisode(VisualizationViewer< String, String> vv,Representation diachronicGraph,ConcurrentHashMap<String, Table> graph){
		visualizationsOfDBVersion.createEpisodes(vv,graph,diachronicGraph.getUniversalFrame(),diachronicGraph.getUniversalBounds(),diachronicGraph.getUniversalCenter(),diachronicGraph.getFrameX(),diachronicGraph.getFrameY(),diachronicGraph.getScaleX(),diachronicGraph.getScaleY());
	}

	public void setDetails(String tf, int et,int width,int height){
		visualizationsOfDBVersion.setDetails(tf, et,width, height);
	}

	public Table getNode(int position) {
		return tablesWithin.get(position);
	}
	
	public String getKeyOfNode(Table t) {
		return t.getKey();
	}
	
	public int getTableStatusOfNode(Table t) {
		return t.getTableStatus();
	}
	
	public int getSizeOfNodesList() {
		return tablesWithin.size();
	}
		
	public ArrayList<Table> getTables() {
		return tablesWithin;
	}

	public ArrayList<ForeignKey> getVersionForeignKeys() {
		return versionForeignKeys;
	}

	public ArrayList<Table> getNodes() {
		return getTables();
	}

	public ArrayList<ForeignKey> getEdges() {
		return getVersionForeignKeys();
	}

	public String getVersion() {
		return this.versionName;
	}
	
	public String getSourceTableOfForeignKey(ForeignKey fk) {
		return fk.getSourceTable();
	}

	public String getTargetTableOfForeignKey(ForeignKey fk) {
		return fk.getTargetTable();
	}
	
	public Graph getGraph(){
		return graphMetricsOfDBVersion.getGraph();
		
	}
	
	public String generateVertexDegree(String vertex){
		vertex=vertex.replace(",","");
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(getKeyOfNode(tablesWithin.get(i)))){
				vertex=vertex+",";
				return graphMetricsOfDBVersion.generateVertexDegree(vertex);
			}
		}
		
		return "*,";
	}
	
	public String generateVertexInDegree(String vertex){
		
		vertex=vertex.replace(",","");
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(getKeyOfNode(tablesWithin.get(i)))){
				vertex=vertex+",";
				return graphMetricsOfDBVersion.generateVertexInDegree(vertex);
			}
		}
		
		return "*,";
	}
	
	public String generateVertexOutDegree(String vertex){
		vertex=vertex.replace(",","");
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(getKeyOfNode(tablesWithin.get(i)))){
				vertex=vertex+",";
				return graphMetricsOfDBVersion.generateVertexOutDegree(vertex);
			}
		}
		
		return "*,";	
	}
	
	public String generateVertexBetweenness(String vertex){
		vertex=vertex.replace(",","");
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(getKeyOfNode(tablesWithin.get(i)))){
				vertex=vertex+",";
				return graphMetricsOfDBVersion.generateVertexBetweenness(vertex);
			}
		}
		
		return "*,";
	}

	public String generateEdgeBetweenness(String edge) {
		edge=edge.replace(",","");

		for(int i=0;i<versionForeignKeys.size();++i){
			if(edge.equals(getSourceTableOfForeignKey(versionForeignKeys.get(i))+"|"+getTargetTableOfForeignKey(versionForeignKeys.get(i)))){
				edge=edge+",";
				return graphMetricsOfDBVersion.generateEdgeBetweenness(edge);
			}
		}
		
		return "*,";
	}
	
	public String getGraphDiameter(){
		return graphMetricsOfDBVersion.getGraphDiameter();
	}
	
	public String getVertexCount(){
		return graphMetricsOfDBVersion.getVertexCount();
	}
	
	public String getVertexCountForGcc() {
		return graphMetricsOfDBVersion.getVertexCountForGcc();
	}
	
	public String getEdgeCount(){
		return graphMetricsOfDBVersion.getEdgeCount();		
	}
	
	public String getEdgeCountForGCC(){
		return graphMetricsOfDBVersion.getEdgeCountForGcc();		
	}
	
	public String generateConnectedComponentsCountReport(){
		return graphMetricsOfDBVersion.getNumberOfConnectedComponents();
	}
	
	public Map<String,Double> getClusteringCoefficient(){
		return graphMetricsOfDBVersion.getClusteringCoefficient();
	}
	
}
