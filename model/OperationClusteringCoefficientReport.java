package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class OperationClusteringCoefficientReport implements DGMetric {
    @Override
    public void report(String targetFolder, ArrayList<DBVersion> versions, ArrayList<Table> vertices, ArrayList<ForeignKey> edges, GraphMetrics graphMetricsOfDiachronicGraph)throws FileNotFoundException {
		File reportFile = new File(targetFolder+"\\Report of clustering coefficient.csv");	
		Map<String,Double> collection=null;
		PrintWriter writer = new PrintWriter(reportFile);
			
		int lines = vertices.size()+1;
		int columns =versions.size()+2;
			
		String[][] report= new String[lines][columns];
			
		//create 1st line
		report[0][0]=" ,";
		report[0][1]="Diachronic Graph,";		
		for(int i=0;i<versions.size();i++)			
            report[0][i+2]=versions.get(i).getVersion()+",";
		
		//create 1st column		
		for(int i=0;i<vertices.size();i++)
	            report[i+1][0]=vertices.get(i).getKey()+",";		
			
		//fill in the rest
		for(int i=1;i<columns;i++){
            for(int j=1;j<lines;j++)
            	if(i==1){
                    if(collection==null)
                    	collection = graphMetricsOfDiachronicGraph.getClusteringCoefficient();
					
					String candidate =report[j][0].replace(",", "");
					String clusteringCoefficientScore=String.valueOf(collection.get(candidate));
					if(clusteringCoefficientScore.equals("null"))
						clusteringCoefficientScore="*";
					report[j][i] =  clusteringCoefficientScore +",";
            	}else{
                    if(collection==null)
                        collection = versions.get(i-2).getClusteringCoefficient();
					
                    String candidate =report[j][0].replace(",", "");
                    String clusteringCoefficientScore=String.valueOf(collection.get(candidate));
					if(clusteringCoefficientScore.equals("null"))
                            clusteringCoefficientScore="*";
					report[j][i] =  clusteringCoefficientScore +",";
            	}
            	collection.clear();
            	collection=null;
		}
		
		
	//print array into file
		for(int i=0;i<lines;i++){
	            for(int j=0;j<columns;j++)
			writer.print(report[i][j]);
	            writer.print("\n");
		}
				
		writer.close();
		
	}
}