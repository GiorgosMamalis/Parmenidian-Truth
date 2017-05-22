/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Map;
import model.DBVersion;
import model.ForeignKey;
import model.Table;

/**
 *
 * @author User
 */
public interface Parser {

    ArrayList<DBVersion> getLifetime();

    ArrayList<Map<String, Integer>> getTransitions();

    boolean hasGraphml();
    
    ArrayList<Table> getNodes();
    
    ArrayList<ForeignKey> getEdges();
    
}
