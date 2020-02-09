import java.util.ArrayList;
import java.util.Arrays;

import org.jgrapht.graph.*;

public class Main {
	
	public static void main(String[] args) {
		Graphe<NoeudSysteme, Edge> g = new Graphe <>(Edge.class);
		
		
		
		Donnees don1 = new Donnees(1,30);
		Donnees don2 = new Donnees(2,10);
		Donnees don3 = new Donnees(3,70);
		Donnees don4 = new Donnees(4,3);
		Donnees don5 = new Donnees(5,25);
		
		ArrayList<Donnees> listeDon = new ArrayList<Donnees>();
		listeDon.add(don1);
		listeDon.add(don2);
		listeDon.add(don3);
		listeDon.add(don4);
		listeDon.add(don5);
		g.ajouterDonneesAPlacer(listeDon);
		
		
		NoeudSysteme n1 = new NoeudSysteme(1,50);
		NoeudSysteme n2 = new NoeudSysteme(2,40);
		NoeudSysteme n3 = new NoeudSysteme(3,80);
		NoeudSysteme n4 = new NoeudSysteme(4,40);
		
		n1.ajouterNoeudAccessible(n2);
		n1.ajouterNoeudAccessible(n4);
		n2.ajouterNoeudAccessible(n3);
		n4.ajouterNoeudAccessible(n3);
		
		
		g.ajouterNoeudGraphe(n1);
		g.ajouterNoeudGraphe(n2);
		g.ajouterNoeudGraphe(n3);
		g.ajouterNoeudGraphe(n4);
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(don1.getIDdonnee());
		arr.add(don2.getIDdonnee());
		arr.add(don3.getIDdonnee());
		arr.add(don4.getIDdonnee());
		arr.add(don5.getIDdonnee());
		
		Utilisateurs util1 = new Utilisateurs(1,arr,1);
		System.out.println(util1.getListeIdDonnees());
		
		g.ajouterUnUtil(util1);

		
		g.addVertex(n1);
		g.addVertex(n2);
		g.addVertex(n3);
		g.addVertex(n4);
				
		DefaultWeightedEdge edge1 = (DefaultWeightedEdge)g.addEdge(n1, n2);
		DefaultWeightedEdge edge2 = (DefaultWeightedEdge)g.addEdge(n1, n4);
		DefaultWeightedEdge edge3 = (DefaultWeightedEdge)g.addEdge(n2, n3);
		DefaultWeightedEdge edge4 = (DefaultWeightedEdge)g.addEdge(n4, n3);
		
		
		
		
		g.setEdgeWeight(edge1, 5);
		g.setEdgeWeight(edge2, 4);
		g.setEdgeWeight(edge3, 10);
		g.setEdgeWeight(edge4, 1);

	
		g.placerDonneeDansGraphe(util1);
		
		g.affichageNoeudGraphe();
		
		
		g.placerDonneesDescendant(n1, don3);
		
		
		
		
		Graphe<NoeudSysteme, Edge> g2 = new Graphe <>(Edge.class);
		
		g2.ajouterDonneesAPlacer(listeDon);
		
		NoeudSysteme n11 = new NoeudSysteme(1,50);
		NoeudSysteme n22 = new NoeudSysteme(2,40);
		NoeudSysteme n33 = new NoeudSysteme(3,80);
		NoeudSysteme n44 = new NoeudSysteme(4,40);
		
		n11.ajouterNoeudAccessible(n22);
		n11.ajouterNoeudAccessible(n44);
		n22.ajouterNoeudAccessible(n33);
		n44.ajouterNoeudAccessible(n33);
		
		Utilisateurs util10 = new Utilisateurs(1,arr,1);
		Utilisateurs util11 = new Utilisateurs(2,arr,3);

		g2.addVertex(n11);
		g2.addVertex(n22);
		g2.addVertex(n33);
		g2.addVertex(n44);
		
		g2.ajouterNoeudGraphe(n11);
		g2.ajouterNoeudGraphe(n22);
		g2.ajouterNoeudGraphe(n33);
		g2.ajouterNoeudGraphe(n44);
		
		
		
		g2.addEdge(n11, n22);
		g2.addEdge(n11, n44);
		g2.addEdge(n22, n33);
		g2.addEdge(n44, n33);
		
		
		g2.setEdgeWeight(edge1, 5);
		g2.setEdgeWeight(edge2, 4);
		g2.setEdgeWeight(edge3, 10);
		g2.setEdgeWeight(edge4, 1);
				
		System.out.println("");
		g2.placerDonnee2Utils(util10, util11, don2);
		g2.affichageNoeudGraphe();

			
	}
	
}

