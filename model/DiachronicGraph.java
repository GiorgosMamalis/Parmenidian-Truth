package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import parmenidianEnumerations.Status;
import edu.uci.ics.jung.graph.Graph;
import java.awt.geom.Point2D;



public class DiachronicGraph {

	private ArrayList<DBVersion> versions = new ArrayList<DBVersion>();
	private ArrayList<Map<String,Integer>> transitions = new ArrayList<Map<String,Integer>>();//auxiliary class for creating DiachronicGraph
	private static ConcurrentHashMap<String, Table> graph = new ConcurrentHashMap<String, Table>();//union of tables
	private ConcurrentHashMap<String, ForeignKey> graphEdges = new ConcurrentHashMap<String, ForeignKey>();//union of edges
	private ArrayList<Table>  vertices= new ArrayList<Table>();//union of tables
	private ArrayList<ForeignKey> edges= new ArrayList<ForeignKey>();//union of edges
	private GraphMetrics graphMetricsOfDiachronicGraph;
	private int mode;
	private ParserFactory PF = new ParserFactory();
	
	public DiachronicGraph(String sql,String xml,String graphml, String targetFolder, int et,double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY) throws Exception {
		Parser myParser =  PF.getParser("PTParser",sql,xml,graphml);
		//myParser = new PTParser(sql,xml,graphml);
		versions=myParser.getLifetime();
		transitions=myParser.getTransitions();
		updateLifetimeWithTransitions();
		
		if(myParser.hasGraphml()){
			vertices=myParser.getNodes();
			edges=myParser.getEdges();
			fixGraph();	
			mode=1;
			graphMetricsOfDiachronicGraph = new GraphMetrics(vertices,edges);
		}else{
			createDiachronicGraph();
			mode=0;
			graphMetricsOfDiachronicGraph = new GraphMetrics(vertices,edges);
		}
		
	}
	

	
/**   this piece of code is identical to the above except that
 *    additively to edge and score fields
 *    it decomposes the edge
 *    to source node and target node fields. 
**/


	private void updateLifetimeWithTransitions(){
		for(int i=0;i<versions.size();++i)
			if(i==0)
				setFirstVersion(versions.get(i));
			else if(i==versions.size()-1)
				setFinalVersion(versions.get(i),i);
			else
				setIntermediateVersion(versions.get(i),i);
	}
	
	
	/**
	 * Trexw thn prwth version me to prwto Dictionary kai checkarw n dw an sthn
	 * 2h version exei svistei kapoios pinakas.Me endiaferei mono to deletion
	 * An kapoioi exoun ginei updated tha tous vapsw sthn 2h ekdosh,oxi edw
	 * @param fversion :firstVersion
	 */
	private void setFirstVersion(DBVersion fversion){
		for(int i=0;i<fversion.getTables().size();++i)
			if(transitions.get(0).containsKey(fversion.getTables().get(i).getKey())
			&& transitions.get(0).get(fversion.getTables().get(i).getKey())==Status.DELETION.getValue())
				fversion.getTables().get(i).setTableStatus(Status.DELETION.getValue());		
	}
	
	/**
	 * Trexw thn teleutaia version mou me to teleutaio dictionary mou,h thesh tou
	 * teleutaiou dictionary mou einai mia prin apo thn thesh ths teleutaias version mou.
	 * Psaxnw gia tables pou periexontai st dictionary mou KAI DEN einai deletions,einai 
	 * dhladh mono newTable kai UpdateTable kai tous vafw analoga me thn timh pou exei to
	 * dictionary mou.
	 * @param fversion :finalVersion
	 * @param k :H thesh ths teleutaias Version mou sthn Lista
	 */
	private void setFinalVersion(DBVersion fversion,int k){
		for(int i=0;i<fversion.getTables().size();++i)
			if(transitions.get(k-1).containsKey(fversion.getTables().get(i).getKey())
			&& transitions.get(k-1).get(fversion.getTables().get(i).getKey())!=Status.DELETION.getValue())
				fversion.getTables().get(i).setTableStatus(transitions.get(k-1).get(fversion.getTables().get(i).getKey()));
	}
	
	private void setIntermediateVersion(DBVersion version,int k){
		for(int i=0;i<version.getTables().size();++i){
			//koitaw to mellontiko m dictionary
			if(transitions.get(k).containsKey(version.getTables().get(i).getKey())
			&& transitions.get(k).get(version.getTables().get(i).getKey())==Status.DELETION.getValue())
				version.getTables().get(i).setTableStatus(Status.DELETION.getValue());
			
			//koitaw to palho m dictionary
			if(transitions.get(k-1).containsKey(version.getTables().get(i).getKey())
			&& transitions.get(k-1).get(version.getTables().get(i).getKey())!=Status.DELETION.getValue())
				version.getTables().get(i).setTableStatus(transitions.get(k-1).get(version.getTables().get(i).getKey()));	
		}
	}

	//se periptwsh pou o UniversalGraph kataskeuazetai apto graphml
	//ektos apo tis listes exw enhmerwmeno kai to graph gia thn grhgorh
	//prospelash twn komvwn
	private void fixGraph() {
		for(int i=0;i<vertices.size();++i){
			graph.put(getKeyOfNode(vertices.get(i)),vertices.get(i));
		}		
	}

	private void createDiachronicGraph() {
		for(int i=0;i<versions.size();++i){	
			for(Table mt : versions.get(i).getTables())
				graph.putIfAbsent(mt.getKey(), mt);//union of tables
			
			for(ForeignKey fk : versions.get(i).getVersionForeignKeys())
				graphEdges.putIfAbsent(fk.getKey(), fk);//union of FK
		}
		transformNodes();
		transformEdges();
	}

	private void transformEdges() {
		//metatroph tou hashmap se ArrayList 
		Iterator i=graphEdges.entrySet().iterator();
		while(i.hasNext()){
			  Map.Entry entry = (Map.Entry) i.next();
			  String key = (String)entry.getKey();
			  ForeignKey value = (ForeignKey)entry.getValue();	  
			  edges.add(value);
		}
	}

	private void transformNodes() {
		//metatroph tou hashmap se ArrayList 
		Iterator i=graph.entrySet().iterator();		
		while(i.hasNext()){
			  Map.Entry entry = (Map.Entry) i.next();
			  String key = (String)entry.getKey();
			  Table value = (Table)entry.getValue();
			  vertices.add(value);
		}
	}	

	public void executeFactory(String targetFolder, DGMetric metric) throws FileNotFoundException{
		metric.report(targetFolder, versions, vertices, edges, graphMetricsOfDiachronicGraph);
	}
	
	public GraphMetrics getMetrics(){
		return graphMetricsOfDiachronicGraph;
	}
	
	public ArrayList<DBVersion> getVersions() {
		return versions;
	}
	
	public ArrayList<Table> getNodes() {
		return vertices;
	}
	
	public Table getNode(int position) {
		return vertices.get(position);
	}
	
	public String getKeyOfNode(Table t) {
		return t.getKey(); 
	}
	
	public int getSizeOfNodesList() {
		return vertices.size();
	}
	
	public Point2D getCoordsOfNode(Table t) {
		return t.getCoords();
	}
	
	public void setCoordsOfNode(Object object, int position) {
		getNode(position).setCoords(object);
	}
	
	public ArrayList<ForeignKey> getEdges() {
		return edges;
	}

	public ConcurrentHashMap<String, Table> getDictionaryOfGraph() {
		return graph;
	}

	public String getVersion() {
		return "Universal Graph";
	}
	
	public Graph getGraph(){
		return graphMetricsOfDiachronicGraph.getGraph();
	}
        
    public int getMode() {
        return mode;
    }
        
	public void clear(){
		versions.clear();
		graph.clear();
		graphEdges.clear();
		vertices.clear();
		edges.clear();
	}
	
}
