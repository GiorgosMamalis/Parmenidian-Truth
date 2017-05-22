/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author User
 */
public class ParserFactory {
    public Parser getParser(String ParserType, String sqlFiles,String xmlFile, String graphmlFile) throws Exception
    {
        if(ParserType.equals("PTParser"))
        {
            return new PTParser(sqlFiles, xmlFile, graphmlFile);
        }
        else
        {
            return null;
        }
    }
}

