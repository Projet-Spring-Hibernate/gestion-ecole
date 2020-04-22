package com.intiformation.gestion_ecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity(name="enseignants")
@Table(name="Enseignants")
public class Enseignant extends Personne {

	/*_______________prop_______________*/

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="ENSEIGNANT_ID"),
	inverseJoinColumns = @JoinColumn(name="MATIERE_ID")
	)
	private List<Matiere> listeMatiere;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="ENSEIGNANT_ID"),
	inverseJoinColumns = @JoinColumn(name="PROMOTION_ID")
	)
	private List<Promotion> listePromotion;

	/*_______________ctor_______________*/

	public Enseignant() {
	}
	
	public Enseignant(Long identifiant,String motdepasse, String nom, String prenom, String email) {
		super(identifiant,motdepasse,nom,prenom,email);
	}
	
	public Enseignant(String motdepasse, String nom, String prenom, String email) {
		super(motdepasse,nom,prenom,email);
	}

	
	/*_______________get/set_______________*/

	public List<Matiere> getListeMatiere() {
		return listeMatiere;
	}

	public void setListeMatiere(List<Matiere> listeMatiere) {
		this.listeMatiere = listeMatiere;
	}

	public List<Promotion> getListePromotion() {
		return listePromotion;
	}

	public void setListePromotion(List<Promotion> listePromotion) {
		this.listePromotion = listePromotion;
	}
	

	
	
	
	
}//end classe
