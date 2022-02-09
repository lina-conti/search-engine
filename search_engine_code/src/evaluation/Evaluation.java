package evaluation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import indexation.Index;
import interrogation.*;

public class Evaluation {
	private Index requetes;
	private HashMap<String, ArrayList<String>> relations;
	
	public Evaluation(Index requetes, HashMap<String, ArrayList<String>> relations) {
		this.requetes = requetes;
		this.relations = relations;
	}
	
	@SuppressWarnings("unchecked")
	private double dnInterDocsAtt(String idRequete, int n, IRModel modele) throws FileNotFoundException, IOException {
		@SuppressWarnings("rawtypes")
		ArrayList docsModele = new ArrayList(modele.runModel(requetes.getDoc(idRequete).getText()).keySet()); 
		ArrayList<String> docsAttendus = relations.get(idRequete);
		int cpt = 0;
		for (int i=0; i<n; i++) {
			try {
				if (docsAttendus.contains(docsModele.get(i))) {
					cpt++;
				}
			} catch (NullPointerException e) {}
		}
		return  ((double) cpt);
	}
	
	public double precision(String idRequete, int n, IRModel modele) throws FileNotFoundException, IOException {
		return  dnInterDocsAtt(idRequete, n, modele) / ((double) n);
	}
	
	public double rappel(String idRequete, int n, IRModel modele) throws FileNotFoundException, IOException {
		ArrayList<String> docsAttendus = relations.get(idRequete);
		return dnInterDocsAtt(idRequete, n, modele) / ((double) docsAttendus.size());
	}
	
	public double fMesure(String idRequete, int n, IRModel modele) throws FileNotFoundException, IOException {
		double precision = precision(idRequete, n, modele);
		double rappel = rappel(idRequete, n, modele);
		if (precision + rappel == 0) {
			System.out.println("Impossible de calculer la fMesure, le dénominateur est nul !");
			return -1.;
		}
		return 2 * ((precision * rappel)/(precision + rappel));
	}
}
