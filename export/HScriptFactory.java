/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package export;

import java.io.File;

/**
 *
 * @author User
 */
public class HScriptFactory {
	public HScript getHScript(String HScriptType, File folder) throws Exception
	{
		if(HScriptType.equals("HecateScript"))
			return new HecateScript(folder);
		else
			return null;
	}
}
