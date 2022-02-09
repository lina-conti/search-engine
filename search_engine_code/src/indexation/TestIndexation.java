package indexation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import core.*;

public class TestIndexation {

	public static void main(String[] args) {
		Index index = Index.deserialize("index");
		
		try {
			
			Document d55 = index.getDoc("55");
			System.out.println(d55.getText());
			System.out.println("\n");
			System.out.println(index.getTfsForDoc("55"));
			System.out.println("\n");
			System.out.println(index.getTfsForStem("attempt"));
			System.out.println("\n");
			
			System.out.println("Nombre de termes indexés différents contenus dans le document 55 : " + index.getTfsForDoc("55").size());
			System.out.println("\n");
			HashMap <String, Integer> dicoAttempt = index.getTfsForStem("attempt");
			System.out.println("Nombre de documents contenant le terme 'attempt' : " + dicoAttempt.size());
			System.out.println("\n");
			System.out.println("Documents contenant le terme 'attempt' : \n");
			/*for (String idDoc : dicoAttempt.keySet()) {
				System.out.println(index.getDoc(idDoc).getText() + "\n----------------------------------------------------------------------------------------------------------------------------\n");
			}*/
			
			HashMap <String, Double> dicoScoreAlea = new HashMap <String, Double>();
			HashMap <String, String> dicoDocs = index.getDocs();
			for (String idDoc : dicoDocs.keySet()) {
				dicoScoreAlea.put(idDoc, Math.random());
			}
			System.out.println("Hashmap associant à chaque document un score aléatoire : \n\n" + dicoScoreAlea);
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
