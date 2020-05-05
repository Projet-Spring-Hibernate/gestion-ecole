package com.intiformation.gestion_ecole.domain;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * Classe entity pour les administrateurs. Classe fille de la classe Personne.
 * Relation triple avec Matière et Promotion
 * Relation ManyToMany avec Matiere.
 * Relation ManyToMany avec Promotion.
 * 
 * @author Thanesh
 *
 */
@Entity(name="enseignant")
@DiscriminatorValue("ROLE_ENSEIGNANT")
public class Enseignant extends Personne {

	/*_______________prop_______________*/

	
	
	@OneToMany(mappedBy="enseignant")
	@Cascade(CascadeType.SAVE_UPDATE)
	@JsonBackReference
	private List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = new ArrayList<>();
	
	

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

//	public List<Matiere> getListeMatiere() {
//		return listeMatiere;
//	}
//
//	public void setListeMatiere(List<Matiere> listeMatiere) {
//		this.listeMatiere = listeMatiere;
//	}
//
//	public List<Promotion> getListePromotion() {
//		return listePromotion;
//	}
//
//	public void setListePromotion(List<Promotion> listePromotion) {
//		this.listePromotion = listePromotion;
//	}
	
	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotion() {
		return listeEnseignantMatierePromotion;
	}

	public void setListeEnseignantMatierePromotion(List<EnseignantMatierePromotion> listeEnseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion = listeEnseignantMatierePromotion;
	}
	
	//_____________ Méthodes _________________________//
	
//	/**
//	 * Ajoute une promotion à la liste des promotions de l'enseignant + ajoute l'enseignant à la liste des enseignants de la promotion
//	 * @param promotion
//	 */
//	public void addPromotion(Promotion promotion) {
//		this.listePromotion.add(promotion);
//		promotion.getListeEnseignant().add(this);
//	}
//
//	/**
//	 * Ajoute une matière à la liste des matières de l'enseignant + ajoute l'enseignant à la liste des enseignants de la matière
//	 * @param matiere
//	 */
//	public void addMatiere(Matiere matiere) {
//		this.listeMatiere.add(matiere);
//		matiere.getListeEnseignant().add(this);
//	}
//	
	public void addEnseignantMatierePromotion(EnseignantMatierePromotion enseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion.add(enseignantMatierePromotion);
		enseignantMatierePromotion.setEnseignant(this);
	}
	
	
	@Override
	public String toString() {
		return "Enseignant [identifiant=" + super.getIdentifiant() + ", motdepasse=" + super.getMotdepasse() + ", nom=" + super.getNom() + ", prenom="
				+ super.getPrenom() + ", email=" + super.getEmail() + "]";
	}


	
	
}//end classe
