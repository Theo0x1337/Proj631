import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.*;

public class Graphe<Noeud,Edge> extends SimpleWeightedGraph {
	
	
	private ArrayList<Utilisateurs> listeUtil = new ArrayList<Utilisateurs>();
	private ArrayList<Donnees> listeDonneesAPlacer = new ArrayList<Donnees>();
	private ArrayList<Donnees> listeDonneesPlacees = new ArrayList<Donnees>();
	private ArrayList<NoeudSysteme> listeNoeud = new ArrayList<NoeudSysteme>();

	
	public Graphe(Class arg0) {
		super(arg0);
	}
	
	
	
	public List cheminLePlusCourt(NoeudSysteme n1,NoeudSysteme n2) {
		DijkstraShortestPath<NoeudSysteme,Edge> dij = new DijkstraShortestPath<NoeudSysteme,Edge> (this, n1, n2);
		return dij.findPathBetween(this, n1, n2);
	}
	
	
	public void ajouterUtils(ArrayList<Utilisateurs> utils) {
		for(int i = 0;i<utils.size();i++) {
			this.addVertex(utils);
		}
	}
	
	public void ajouterUnUtil(Utilisateurs util) {
		this.listeUtil.add(util);
	}
	
	
	public void ajouterNoeud(ArrayList<NoeudSysteme> noeud) {
		for(int i = 0;i<noeud.size();i++) {
			this.addVertex(noeud);
			this.listeNoeud.add(noeud.get(i));
		}
	}
	
	
	public void ajouterDonneesAPlacer(ArrayList<Donnees> don) {
		for(int i = 0;i<don.size();i++) {
			this.listeDonneesAPlacer.add(don.get(i));
		}
	}
	
	
	public void donneesAEtePlace(Donnees don) {
		this.listeDonneesAPlacer.remove(don);
		this.listeDonneesPlacees.add(don);
	}
	
	
	public Donnees getDonneeById(Integer id) {
		for(int i = 0;i<this.listeDonneesAPlacer.size();i++) {
			if(this.listeDonneesAPlacer.get(i).getIDdonnee() == id) {
				return this.listeDonneesAPlacer.get(i);
			}
		}
		return null;
	}
	
	
	public ArrayList<Donnees> getDonneesAPlacerParUtil(Utilisateurs util){
		ArrayList<Donnees> arr = new ArrayList<Donnees>();
		for(int i = 0;i<util.getListeIdDonnees().size();i++) {
			if(util.getListeIdDonnees().get(i) == this.listeDonneesAPlacer.get(i).getIDdonnee()) {
				arr.add(this.listeDonneesAPlacer.get(i));
			}
		}
		return arr;
	}
	
	
	public void ajouterNoeudGraphe(NoeudSysteme noeud) {
		this.listeNoeud.add(noeud);
	}
	
	
	
	public void ajouterPlusieursNoeud(ArrayList<NoeudSysteme> noeuds) {
		for(int i = 0;i<noeuds.size();i++) {
			this.listeNoeud.add(noeuds.get(i));
		}
	}
	
	
	public NoeudSysteme getNoeudById(int idNoeud) {
		for(int i = 0;i<this.listeNoeud.size();i++) {
			if (this.listeNoeud.get(i).getIdNoeud() == idNoeud){
				return this.listeNoeud.get(i);
			}
		}
		System.out.println("Le noeud cherche n'est pas dans le graphe");
		return null;
	}
	
	
	public ArrayList<Donnees> getListeIntermed(ArrayList<Donnees> don){
		ArrayList<Donnees> intermed = new ArrayList<Donnees>();
		for(Donnees donnee : don) {
			intermed.add(donnee);
		}
		return intermed;
	}
	
	
	public void placerDonneesPourChaqueUtil() {
		for(int i = 0;i<this.listeUtil.size();i++) {
			this.placerDonneeDansGraphe(this.listeUtil.get(i));
		}
	}
	
	
	public void placerDonneeDansGraphe(Utilisateurs util) {
		//initiliase la liste dont on a besoin pour placer les donnees
		ArrayList<Donnees> aPlacer = new ArrayList<Donnees>();
		ArrayList<Donnees> intermed = new ArrayList<Donnees>();
			//on recupere la liste des donnees a placer pour cet utilisateur
			aPlacer = this.getDonneesAPlacerParUtil(util);
			intermed = this.getListeIntermed(aPlacer);
			//on recupere le noeud auquel l'utilisateur est lie
			NoeudSysteme nUtil = this.getNoeudById(util.getNoeudAccessible());
			//pour chaque donnee a placer
			for (int j = 0;j<aPlacer.size();j++) {
				//si la taille de la donnee est inférieure ou egale a la taille du noeud
				if (aPlacer.get(j).getTaille() <= nUtil.getCapacite()) {
					//on ajoute la donnee dans ce noeud 
					nUtil.ajouterDonnée(aPlacer.get(j));
					//on retire cette donnee de la liste a ajouter
					intermed.remove(aPlacer.get(j));
				//sinon
				}else {
					//on regarde tous les noeuds accessible depuis le noeud courant
					ArrayList<NoeudSysteme> nAccessibles = nUtil.getNoeudAccessibles();
					double min = 1000;
					NoeudSysteme nSuivant = null;
					//on cherche le chemin le plus court possible entre le noeud courant et les autres noeuds
					//pour tous les noeuds dans la liste
					for(NoeudSysteme n : nAccessibles) {
						//si le poid de l'arc entre le noeud courant et le noeud de la liste et inf au min
						// et si le noeud de la liste a assez de memoire pour stocker la donnee
						if (this.getEdgeWeight(this.getEdge(nUtil,n))< min & n.getCapacite()>= aPlacer.get(j).getTaille()) {
							//le min devient le poid de l'arc entre le noeud courant et le noeud de la liste
							min = this.getEdgeWeight(this.getEdge(nUtil,n));
							//le noeud suivant devient le noeud de la liste
							nSuivant = n;
						}
					}
					//on ajoute la donnee dans le noeud suivant si un noeud suivant est disponible
					if(nSuivant != null) {
						nSuivant.ajouterDonnée(aPlacer.get(j));
						//on supprime la donnee qui a ete ajoute de la liste intermediaire
						intermed.remove(aPlacer.get(j));	
					}else {
						this.placerDonneesDescendant(nUtil, aPlacer.get(j));
						intermed.remove(aPlacer.get(j));
					}
				}
		}if(intermed.size() > 0 ) {
			System.out.println(intermed);
			System.out.println("Il n'y a actuellement pas assez de place pour placer cette donnee");
		}else {
			System.out.println("Les donnees de l'utilisateur "+util.getIdUtil()+" ont été placé");
		}
	}
	
	
	public ArrayList<NoeudSysteme> getNoeudAccessibleDescendant(NoeudSysteme n) {
		//initialise la liste dont on va avoir besoin
		ArrayList<NoeudSysteme> noeuds = new ArrayList<NoeudSysteme>();
		//pour chaque noeudSysteme accessible depuis le noeud
		for (NoeudSysteme noeud : n.getNoeudAccessibles()) {
			NoeudSysteme noeud2 = noeud;
			for(NoeudSysteme noeudIntermed : noeud2.getNoeudAccessibles()) {
				noeuds.add(noeudIntermed);
			}
		}
		return noeuds;
	}
	
	
	
	public void placerDonneesDescendant(NoeudSysteme n,Donnees don) {
		//initialise la liste dont on a besoin
		ArrayList<NoeudSysteme> arrNoeud = this.getNoeudAccessibleDescendant(n);
		//pour chaque noeud descendant de n
		for(NoeudSysteme noeud : arrNoeud) {
			//si le noeud de la liste peut accueillir la donnee alors 
			if(noeud.getCapacite() >= don.getTaille()) {
				noeud.ajouterDonnée(don);
			}
		}
	}
	
	
	
	public void placerDonnee2Utils(Utilisateurs util1,Utilisateurs util2,Donnees don) {
		//on cherche le chemin le plus court entre les deux noeuds accessibles des utilisateurs
		ArrayList arr = (ArrayList) this.cheminLePlusCourt(this.getNoeudById(util1.getNoeudAccessible()),this.getNoeudById(util2.getNoeudAccessible()));
		//si il y a un nombre pair d'arc dans le chemin entre les noeuds
		if(arr.size() % 2 == 0) {
			//on va recuperer le noeud qui se trouve a la moitie du chemin entre les deux noeuds
			int indice = arr.size()/2;
			
			System.out.println(arr.get(indice).getClass());
				
		}
	}
	
	
	public void affichageNoeudGraphe() {
		for(NoeudSysteme n : this.listeNoeud) {
			System.out.println("Noeud : " + n.getIdNoeud() + " capacite : "+n.getCapacite() + " donnees placees : "+n.getListeDonneesStockees());
		}
	}
	

}
