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

/**
 * Classe entity pour les promotions.
 * Relation triple avec Enseignant et Matière
 * Relation ManyToMany avec Enseignant.
 * Relation ManyToMany avec Matière.
 * 
 * @author Thanesh
 *
 */
@Entity(name="promotion")
@Table(name="promotions")
public class Promotion implements Serializable {


	/*_______________prop_______________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROMOTION")
	private Long idPromotion;
	
	private String libelle;
	
//	@ManyToMany(mappedBy="listePromotion")
//	@Cascade(CascadeType.SAVE_UPDATE)
//	private List<Enseignant> listeEnseignant =new ArrayList<>();
//	
//	@ManyToMany(fetch=FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	@Cascade(CascadeType.SAVE_UPDATE)
//	@JoinTable(name = "enseignant_matiere_promotion",
//	joinColumns = @JoinColumn(name="PROMOTION_ID",referencedColumnName = "ID_PROMOTION"),
//	inverseJoinColumns = @JoinColumn(name="MATIERE_ID",referencedColumnName = "ID_MATIERE")
//	)
//	private List<Matiere> listeMatiere =new ArrayList<>();
	
	@OneToMany(mappedBy="promotion")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<EnseignantMatierePromotion> listeEnseignantMatierePromotion = new ArrayList<>();
	
	
	
	// relation entre promotion et cours
	@OneToMany(mappedBy="promotion")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Cours> listeCours =new ArrayList<>();
	
	// relation entre promotion et étudiant
	@ManyToMany(mappedBy = "listePromotion")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Etudiant> listeEtudiant =new ArrayList<>();
	
	
	
	/*_______________ctor_______________*/

	public Promotion() {
		super();
	}

	public Promotion(String libelle) {
		this.libelle = libelle;
	}

	
	/*_______________get/set_______________*/

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Long getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Long idPromotion) {
		this.idPromotion = idPromotion;
	}

//	public List<Enseignant> getListeEnseignant() {
//		return listeEnseignant;
//	}
//
//	public void setListeEnseignant(List<Enseignant> listeEnseignant) {
//		this.listeEnseignant = listeEnseignant;
//	}
//
//	public List<Matiere> getListeMatiere() {
//		return listeMatiere;
//	}
//
//	public void setListeMatiere(List<Matiere> listeMatiere) {
//		this.listeMatiere = listeMatiere;
//	}

	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}



	public List<EnseignantMatierePromotion> getListeEnseignantMatierePromotion() {
		return listeEnseignantMatierePromotion;
	}

	public void setListeEnseignantMatierePromotion(List<EnseignantMatierePromotion> listeEnseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion = listeEnseignantMatierePromotion;
	}

	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	
	// ================== Méthodes ==========================//
	
//	/**
//	 * Ajoute une matière à la liste des matières de la promotion + ajoute la promotion à la liste des promotions de la matière
//	 * @param matiere
//	 */
//	public void addMatiere(Matiere matiere) {
//		this.listeMatiere.add(matiere);
//		matiere.getListePromotion().add(this);
//	}
//	
//	/**
//	 * Ajoute un enseignant à la liste des enseignants de la promotion + ajoute la promotion à la liste des promotions de l'enseignant
//	 * @param matiere
//	 */
//	public void addEnseignant(Enseignant enseignant) {
//		this.listeEnseignant.add(enseignant);
//		enseignant.getListePromotion().add(this);
//	}
	
	
	public void addEnseignantMatierePromotion(EnseignantMatierePromotion enseignantMatierePromotion) {
		this.listeEnseignantMatierePromotion.add(enseignantMatierePromotion);
		enseignantMatierePromotion.setPromotion(this);
	}
	
	/**
	 * Ajoute un etudiant à la liste des etudiants de la promotion + ajoute la promotion à la liste des promotions de l'etudiant
	 * @param matiere
	 */
	public void addEtudiant(Etudiant etudiant ) {
		this.listeEtudiant.add(etudiant);
		etudiant.getListePromotion().add(this);
	}
	
	/**
	 * Ajoute un cours à la liste des cours de la promotion + attribut la promotion à la propriété promotion du cours
	 * @param cours
	 */
	public void addCours(Cours cours) {
		this.listeCours.add(cours);
		cours.setPromotion(this);
	}
	
	
	@Override
	public String toString() {
		return "Promotion [idPromotion=" + idPromotion + ", libelle=" + libelle + "]";
	}
	
	
}//end class
