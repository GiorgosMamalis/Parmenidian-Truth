/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author User
 */
public class ManagerFactory {
	
	public Manager getManager(String ManagerType)
	{
		if(ManagerType.equals("ParmenidianTruth"))
			return new ParmenidianTruthManager();
		else
			return null;
	}
}
