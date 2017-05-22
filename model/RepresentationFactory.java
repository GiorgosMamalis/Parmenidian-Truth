package model;

public class RepresentationFactory {
	public Representation createRepresentation(String representationType, DiachronicGraph p, String sql,String xml,String graphml, String targetFolder, int et, double frameX,double frameY,double scaleX,double scaleY,double centerX,double centerY) throws Exception {
		if(representationType.equals("DGRepresentation"))
			return new DGRepresentation(p,sql,xml,graphml,targetFolder,et,frameX,frameY,scaleX,scaleY,centerX,centerY);
		return null;
	}
}
