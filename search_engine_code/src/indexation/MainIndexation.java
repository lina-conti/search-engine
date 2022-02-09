package indexation;


public class MainIndexation {

	public static void main(String[] args) {
		ParserCISI p1 = new ParserCISI();
		Stemmer s1 = new Stemmer();
		Index index = new Index("index", p1, s1);
		index.index("cisi.txt");
		System.out.println("Nombre de termes indexés différents contenus dans le document 55 : " + index.getTfsForDoc("55").size());
		
		ParserCISI p2 = new ParserCISI();
		Stemmer s2 = new Stemmer();
		Index requetes = new Index("requetes", p2, s2);
		requetes.index("cisi.qry");
	}

}
