package interrogation;

import java.io.IOException;
import java.util.HashMap;

import indexation.*;

public class MainInterrogation {

	public static void main(String[] args) {
	// initialise l’index 
	Index index = Index.deserialize ("index"); 
	String query = "What is the usual relevance of the content of articles to their titles??";
	
	// Modele booleen 
	IRModel mod = new Boolean(index);
	System.out.println(mod.runModel(query));
	
	WeighterTF w  = new WeighterTF(index);
	HashMap<String, Integer> requete = mod.getQueryProcessed("young boy boi");
	System.out.println(w.poidsDansRequete("young", requete));
	
	WeighterTFIDF w1  = new WeighterTFIDF(index);
	System.out.println(w1.poidsDansDoc("law", "55"));
	
	WeighterTFIDF w3 = new WeighterTFIDF(index);
	
	//Modele Vectoriel Cartesien avec ponderation TF
	//WeighterTF w2  = new WeighterTF(index);
	IRModel modCart = new VectorialCart(index, w3);
	System.out.println(modCart.runModel(query));
	
	//Modele Vectoriel Cosinus avec ponderation TFIDF
	
	IRModel modCos = new VectorialCos(index, w3);
	System.out.println(modCos.runModel(query));
	

	for (String idDoc : modCos.runModel(query).keySet()) {
		try {
			System.out.println(index.getDoc(idDoc).getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}

}
