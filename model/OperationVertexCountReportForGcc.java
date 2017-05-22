package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OperationVertexCountReportForGcc implements DGMetric {
	@Override
	public void report(String targetFolder, ArrayList<DBVersion> versions, ArrayList<Table> vertices, ArrayList<ForeignKey> edges, GraphMetrics graphMetricsOfDiachronicGraph)throws FileNotFoundException {
		File reportFile = new File(targetFolder+"\\Report of vertexCount in gcc.csv");
		PrintWriter writer = new PrintWriter(reportFile);
		
		int lines = 2;
		int columns =versions.size()+2;
		String[][] report= new String[lines][columns];
		
		//create 1st line
		report[0][0]=" ,";
		report[0][1]="Diachronic Graph,";		
		for(int i=0;i<versions.size();i++)			
			report[0][i+2]=versions.get(i).getVersion()+",";
		
		//create 1st column		
		report[1][0]="# of Vertices in gcc,";
		
		//fill in the rest
		for(int i=1;i<columns;i++)
			if(i==1)
				report[1][i] = graphMetricsOfDiachronicGraph.getVertexCountForGcc();
			else
				report[1][i]=versions.get(i-2).getVertexCountForGcc();
		
		//print array into file
		for(int i=0;i<lines;i++){
			for(int j=0;j<columns;j++)
				writer.print(report[i][j]);
			writer.print("\n");
		}
		writer.close();
	}
}