package evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import indexation.Index;
import interrogation.Boolean;
import interrogation.IRModel;
import interrogation.VectorialCart;
import interrogation.VectorialCos;
import interrogation.WeighterTF;
import interrogation.WeighterTFIDF;


public class MainEvaluation {

	public static void main(String[] args) {
		Scanner s;
		try {
			//lecture de cisi.rel sous forme d'un HashMap
			s = new Scanner(new File("cisi.rel"));
			HashMap<String, ArrayList<String>> relations = new HashMap<String, ArrayList<String>>();

			while (s.hasNext()) {
			    Integer cle = s.nextInt();
			    relations.putIfAbsent(cle.toString(), new ArrayList<String>());
			    
			    Integer valeur = s.nextInt();
			    relations.get(cle.toString()).add(valeur.toString());
			    s.nextLine();
			}
			
			//recuperation de cisi.qry sous forme d'un index
			Index requetes = Index.deserialize("requetes");
			
			//evaluation d'un modele
			Index index = Index.deserialize ("index");
			WeighterTF wTF  = new WeighterTF(index);
			WeighterTFIDF wTFIDF  = new WeighterTFIDF(index);
			IRModel modCartTF = new VectorialCart(index, wTF);
			IRModel modCartTFIDF = new VectorialCart(index, wTFIDF);
			IRModel modCosTF = new VectorialCos(index, wTF);
			IRModel modCosTFIDF = new VectorialCos(index, wTFIDF);
			IRModel modBool = new Boolean(index);
			String idRequete = "6";
			
			Evaluation eval = new Evaluation(requetes, relations);
			
			System.out.println("VectorialCart TF");
			System.out.println("precision : " + eval.precision(idRequete, 5, modCartTF));
			System.out.println("rappel : " + eval.rappel("1", 5, modCartTF));
			System.out.println("fMesure : " + eval.fMesure("1", 5, modCartTF) + "\n\n");
	
			System.out.println("VectorialCart TFIDF");
			System.out.println("precision : " + eval.precision(idRequete, 5, modCartTFIDF));
			System.out.println("rappel : " + eval.rappel("1", 5, modCartTFIDF));
			System.out.println("fMesure : " + eval.fMesure("1", 5, modCartTFIDF) + "\n\n");
			
			System.out.println("VectorialCos TF");
			System.out.println("precision : " + eval.precision(idRequete, 5, modCosTF));
			System.out.println("rappel : " + eval.rappel("1", 5, modCosTF));
			System.out.println("fMesure : " + eval.fMesure("1", 5, modCosTF) + "\n\n");
			
			System.out.println("VectorialCos TFIDF");
			System.out.println("precision : " + eval.precision(idRequete, 5, modCosTFIDF));
			System.out.println("rappel : " + eval.rappel("1", 5, modCosTFIDF));
			System.out.println("fMesure : " + eval.fMesure("1", 5, modCosTFIDF) + "\n\n");
			
			System.out.println("Boolean");
			System.out.println("precision : " + eval.precision(idRequete, 5, modBool));
			System.out.println("rappel : " + eval.rappel("1", 5, modBool));
			System.out.println("fMesure : " + eval.fMesure("1", 5, modBool) + "\n\n");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
