package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * Classe entity pour les matières.
 * Relation triple avec Enseignant et Promotion
 * Relation ManyToMany avec Enseignant.
 * Relation ManyToMany avec Promotion.
 * @author Thanesh
 *
 */
@Entity(name="matiere")
@Table(name="matieres")
public class Matiere implements Serializable {


	/*_______________prop_______________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MATIERE")
	private Long idMatiere;
	
	
	private String libelle;
	
	
	@OneToMany(mappedBy="matiere", orphanRemoval= true)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JsonBackReference
	private List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = new ArrayList<>();
	
	
	//relation entre matière et Cours
	@OneToMany(mappedBy="matiere", fetch=FetchType.EAGER,  orphanRemoval= true)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JsonBackReference
	private List<Cours> listeCours= new ArrayList<>();
	
	
	/*_______________ctor_______________*/

	public Matiere() {
	}

	public Matiere(String libelle) {
		this.libelle = libelle;
	}

	
	/*_______________get/set_______________*/

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

//	public List<Enseignant> getListeEnseignant() {
//		return listeEnseignant;
//	}
//
//	public void setListeEnseignant(List<Enseignant> listeEnseignant) {
//		this.listeEnseignant = listeEnseignant;
//	}
//
//	public List<Promotion> getListePromotion() {
//		return listePromotion;
//	}
//
//	public void setListePromotion(List<Promotion> listePromotion) {
//		this.listePromotion = listePromotion;
//	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotion() {
		return listeEnseignantMatierePromotion;
	}

	public void setListeEnseignantMatierePromotion(List<EnseignantMatierePromotion> listeEnseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion = listeEnseignantMatierePromotion;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	// ================== Méthodes ==========================//
	
//	/**
//	 * Ajoute un enseignant à la liste des enseignants de la matière + ajoute la matière à la liste des matières de l'enseignant
//	 * @param enseignant
//	 */
//	public void addEnseignant(Enseignant enseignant) {
//		this.listeEnseignant.add(enseignant);
//		enseignant.getListeMatiere().add(this);
//	}
//	
//	/**
//	 * Ajoute une promotion à la liste des promotions de la matière + ajoute la matière à la liste des matières de la promotion 
//	 * @param promotion
//	 */
//	public void addPromotion(Promotion promotion) {
//		this.listePromotion.add(promotion);
//		promotion.getListeMatiere().add(this);
//	}
	
	
	public void addEnseignantMatierePromotion(EnseignantMatierePromotion enseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion.add(enseignantMatierePromotion);
		enseignantMatierePromotion.setMatiere(this);
	}
	
	
	/**
	 * Ajoute un cours à la liste des cours de la matière + attribut la matière à la propriété matière du cours
	 * @param promotion
	 */
	public void addCours(Cours cours) {
		System.out.println("============"+ cours);
		System.out.println("========="+this);
		this.listeCours.add(cours);
		System.out.println("checkpoint");
		cours.setMatiere(this);
	}
	
	
	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", libelle=" + libelle + "]";
	}
	
}//end class
