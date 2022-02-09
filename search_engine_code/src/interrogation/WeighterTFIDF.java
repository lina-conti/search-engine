package interrogation;

import java.util.HashMap;

import indexation.Index;

public class WeighterTFIDF extends Weighter {

	public WeighterTFIDF(Index index) {
		super(index);
	}
	
	@Override
	public double poidsDansDoc(String terme, String idDoc) {
		if (index.getTfsForStem(terme).size() != 0) {
			return super.poidsDansDoc(terme, idDoc) * (Math.log(index.getDocs().size() / index.getTfsForStem(terme).size()) );
		} else {
			return 0.;
		}		
	}
	
	@Override
	public double poidsDansRequete(String terme, HashMap<String, Integer> requete) {
		if (index.getTfsForStem(terme).size() != 0) {
			return super.poidsDansRequete(terme, requete) * (Math.log(index.getDocs().size() / index.getTfsForStem(terme).size()) );
		} else {
			return 0.;
		}		
	}

}
