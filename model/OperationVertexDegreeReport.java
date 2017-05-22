/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OperationVertexDegreeReport implements DGMetric{
    public void report(String targetFolder, ArrayList<DBVersion> versions, ArrayList<Table> vertices, ArrayList<ForeignKey> edges, GraphMetrics graphMetricsOfDiachronicGraph) throws FileNotFoundException {
        File vertexReport = new File(targetFolder+"\\Report of vertex degree.csv");
		
        PrintWriter writer = new PrintWriter(vertexReport);
		
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
		for(int i=1;i<columns;i++)
            for(int j=1;j<lines;j++)
            	if(i==1)
            		report[j][i] = graphMetricsOfDiachronicGraph.generateVertexDegree(report[j][0]);
            	else
                    report[j][i]=versions.get(i-2).generateVertexDegree(report[j][0]);
		
		
	//print array into file
		for(int i=0;i<lines;i++){
			for(int j=0;j<columns;j++)
				writer.print(report[i][j]);
			writer.print("\n");
		}
				
		writer.close();
    }
}
