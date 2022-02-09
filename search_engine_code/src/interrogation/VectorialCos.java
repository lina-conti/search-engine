package interrogation;

import java.util.HashMap;

import indexation.Index;

public class VectorialCos extends Vectorial {

	public VectorialCos(Index index, Weighter w) {
		super(index, w);
	}

	@Override
	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap <String, String> docs = index.getDocs();
		HashMap <String, Double> ret = new HashMap<String, Double>();
		double numerateur;
		double q;
		double d;
		double score;
		for (String idDoc : docs.keySet()) {
			numerateur = 0.;
			q = 0.;
			d = 0.;
			for (String mot : queryProcessed.keySet()) {
				numerateur += w.poidsDansRequete(mot, queryProcessed) * w.poidsDansDoc(mot, idDoc);
				q += w.poidsDansRequete(mot, queryProcessed) * w.poidsDansRequete(mot, queryProcessed);
				d += w.poidsDansDoc(mot, idDoc) * w.poidsDansDoc(mot, idDoc);
			}
			if ((Math.sqrt(q) * Math.sqrt(d)) == 0) {
				score = 0.;
			} else {
				score = numerateur / (Math.sqrt(q) * Math.sqrt(d));
			}
			if (score != 0.) {
				ret.put(idDoc, score);
			}
		}
		return ret;
	}
}
