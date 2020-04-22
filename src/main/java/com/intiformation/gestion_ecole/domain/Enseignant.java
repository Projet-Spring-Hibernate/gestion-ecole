package com.intiformation.gestion_ecole.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity(name="enseignant")
@Table(name="enseignants")
@DiscriminatorValue("enseignant")
public class Enseignant extends Personne {

	/*_______________prop_______________*/

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="id_personne"),
	inverseJoinColumns = @JoinColumn(name="MATIERE_ID")
	)
	private List<Matiere> listeMatiere;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="id_personne"),
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
