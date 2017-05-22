package model;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import model.DBVersion;
import model.ForeignKey;
import model.Table;



public class PTParser implements Parser {
	private ArrayList<DBVersion> lifetime= new ArrayList<DBVersion>();
	private ArrayList<Map<String,Integer>> transitions = new ArrayList<Map<String,Integer>>();
	private boolean graphml=false;
	private GraphmlLoader graphmlLoader;
	private HecateManager worker = new HecateManager();

	
	public PTParser(String sqlFiles,String xmlFile, String graphmlFile) throws Exception{
		
		lifetime=worker.parseSql(sqlFiles);
		
		transitions=worker.parseXml(xmlFile);
		

		//parsarw an uparxei to .graphml an uparxei
		if(graphmlFile!=null){
			graphml=true;
			try {
				graphmlLoader = new GraphmlLoader(graphmlFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}


	

	
    @Override
	public ArrayList<DBVersion>getLifetime(){
		return lifetime;
	}
	
    @Override
	public ArrayList<Map<String,Integer>>getTransitions(){
		return transitions;
	}


    @Override
	public boolean hasGraphml() {
		return graphml;
	}


    @Override
    public ArrayList<Table> getNodes() {
        return graphmlLoader.getNodes();
    }

    @Override
    public ArrayList<ForeignKey> getEdges() {
        return graphmlLoader.getEdges();
    }

}
