package interrogation;

import java.util.HashMap;

import indexation.Index;

public class VectorialCart extends Vectorial {

	public VectorialCart(Index index, Weighter w) {
		super(index, w);
	}
	
	@Override
	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap <String, String> docs = index.getDocs();
		HashMap <String, Double> ret = new HashMap<String, Double>();
		double score;
		for (String idDoc : docs.keySet()) {
			score = 0.;
			for (String mot : queryProcessed.keySet()) {
				score += w.poidsDansRequete(mot, queryProcessed) * w.poidsDansDoc(mot, idDoc);
			}
			if (score != 0.) {
				ret.put(idDoc, score);
			}
		}
		return ret;
	}

}
