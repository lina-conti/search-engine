package interrogation;

import java.util.HashMap;

import indexation.Index;

public class Boolean extends IRModel {

	public Boolean(Index index) {
		super(index);
	}

	@Override
	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap <String, String> docs = index.getDocs();
		HashMap <String, Double> docsScore = new HashMap<String, Double>();
		HashMap <String, Double> ret = new HashMap<String, Double>();
		for (String idDoc : docs.keySet()) {
			docsScore.put(idDoc, 1.);
		}
		for (String mot : queryProcessed.keySet()) {
			for (String idDoc : docsScore.keySet()) {
				if (index.getTfsForStem(mot).get(idDoc) != null) {
					ret.put(idDoc, docsScore.get(idDoc));
				}
			}
		}
		return ret;
	}

}
