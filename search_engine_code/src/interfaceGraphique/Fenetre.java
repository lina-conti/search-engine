package interfaceGraphique;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import indexation.Index;
import interrogation.IRModel;
import interrogation.VectorialCart;
import interrogation.WeighterTF;

public class Fenetre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JTextField jtf = new JTextField("");
	private JButton bouton = new JButton("Lancer la recherche");
	
	public Fenetre(){
	    this.setTitle("Moteur de Recherche Galina");
	    this.setSize(700, 538);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	   
	    //Définition de sa couleur de fond
	    container.setBackground(Color.WHITE);  
	    
	    this.setLayout(new FlowLayout());
	    
	    //Boite requete
	    JPanel saisieRequete = new JPanel();
	    jtf.setPreferredSize(new Dimension(150, 30));
	    saisieRequete.add(jtf);
	    container.setLayout(new FlowLayout());
	    
	    container.add(saisieRequete);
	    
	    bouton.addActionListener(this);
	    container.add(bouton);   
	    
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(container); 
	    
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Index index = Index.deserialize ("index"); 
		String query = jtf.getText();
		WeighterTF w = new WeighterTF(index);
		IRModel modCart = new VectorialCart(index, w);
		
		ArrayList<String> docs = new ArrayList<String>(modCart.runModel(query).keySet());
		for (int i=0; i<10; i++) {
			try {
				JButton b = new JButton(index.getDoc(docs.get(i)).getText());
				int idDoc = i;
				b.setPreferredSize(new Dimension(700, 40));
				b.addActionListener( 
						new ActionListener()	{
							public void actionPerformed(ActionEvent e) {
								JFrame fenetre = new JFrame();
				                fenetre.setTitle("Document n°" + docs.get(idDoc));
							    fenetre.setSize(800, 800);
							    fenetre.setLocationRelativeTo(null);      
							    fenetre.setVisible(true);
							    JTextArea zoneAffichage;
								try {
									zoneAffichage = new JTextArea(index.getDoc(docs.get(idDoc)).getText());
								} catch (IOException e1) {
									zoneAffichage = new JTextArea();
									e1.printStackTrace();
								}
								zoneAffichage.setEditable(false);  
								zoneAffichage.setCursor(null);  
								zoneAffichage.setOpaque(false);  
								zoneAffichage.setFocusable(false);
								zoneAffichage.setLineWrap(true);
								zoneAffichage.setWrapStyleWord(true);
							 	fenetre.setContentPane(zoneAffichage);
							}
						}
				);
				container.add(b);
				this.setContentPane(container); 
			} catch (IOException e1) {
				
			} catch (IndexOutOfBoundsException e1) {
				
			}
		}
	}  
	
}