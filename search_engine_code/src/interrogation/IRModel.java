package interrogation;
import indexation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class IRModel {
	protected Index index;
	
	public IRModel(Index index) {
		this.index = index;
	}
	
	public LinkedHashMap<String, Double> runModel (String  query) {
		HashMap<String, Integer > queryProcessed = getQueryProcessed(query);
		HashMap<String, Double> docsScore = getDocScores(queryProcessed);
		return getRanking(docsScore);
	}

	public abstract HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed);

	public LinkedHashMap<String, Double> getRanking (HashMap<String, Double> docScores) {
		List<Map.Entry<String, Double>> entries = new ArrayList<Map.Entry<String, Double>>(docScores.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
		public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
			return b.getValue().compareTo(a.getValue());
		}
		});
		LinkedHashMap<String, Double> ret = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> entry : entries) {
			ret.put(entry.getKey(), entry.getValue());
		}
		return ret;
	}
	
	public HashMap<String, Integer> getQueryProcessed(String  query) {
		TextRepresenter textRep = index.getTextRepresenter();
		HashMap<String, Integer> ret = textRep.getTextRepresentation(query);
		return ret;
	}
		
}
