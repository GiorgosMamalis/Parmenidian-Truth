/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface DGMetric {
    public void report(String targetFolder, ArrayList<DBVersion> versions, ArrayList<Table> vertices, ArrayList<ForeignKey> edges, GraphMetrics graphMetricsOfDiachronicGraph)throws FileNotFoundException;
    
}
