package interrogation;

import java.util.HashMap;

import indexation.*;

public abstract class Weighter {
	protected Index index;
	
	public Weighter(Index index) {
		this.index = index;
	}
	
	public double poidsDansRequete(String terme, HashMap<String, Integer> requete) {
		double cpt = 0.;
		double nbMots = 0.;
		for (String mot : requete.keySet()) {
			if (mot.equals(terme)) {
				cpt++;
			}
			nbMots += requete.get(mot);
		}
		return cpt / nbMots;
	}
	
	public double poidsDansDoc(String terme, String idDoc) {
		HashMap <String, Integer> tfsDoc = index.getTfsForDoc(idDoc);
		double nbMots = 0.;
		for (Integer occurence : tfsDoc.values()) {
			nbMots += occurence;
		}
		try {
			return tfsDoc.get(terme) / nbMots;
		} catch(NullPointerException e) {
			return 0.;
		}
	}
}
