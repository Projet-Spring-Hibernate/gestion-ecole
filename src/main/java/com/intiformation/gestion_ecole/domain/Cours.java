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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
 * Classe entity pour les cours.
 * Relation ManyToMany avec Etudiant.
 * Relation ManyToOne avec Promotion. 
 * Relation ManyToOne avec Matiere. 
 * @author Lise
 *
 */
@Entity(name="cours")
@Table(name="cours")
public class Cours implements Serializable{
	
	/*____________________props____________________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COURS")
	private Long idCours;
	
	@Column
	private String libelle;
	@Column
	private String date;
	@Column
	private String duree;
	@Column
	private String description;
	

//	@ManyToMany
//	@Cascade(CascadeType.SAVE_UPDATE)
//	@JoinTable(name="etudiant_cours", 
//	joinColumns = @JoinColumn(name="COURS_ID", referencedColumnName="ID_COURS"), 
//	inverseJoinColumns = @JoinColumn(name="ETUDIANT_ID",referencedColumnName="identifiant"))
//	@ManyToMany(mappedBy = "listeCours")
//	@Cascade(CascadeType.SAVE_UPDATE)
//	private List<Etudiant> listeEtudiant =new ArrayList<>();

	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name= "promotion_id", referencedColumnName="ID_PROMOTION")
	//@JsonManagedReference
	@JsonIgnore
	private Promotion promotion;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="matiere_id", referencedColumnName="ID_MATIERE")
	//@JsonManagedReference
	@JsonIgnore
	private Matiere matiere;
	
	
	@OneToMany(mappedBy="cours")
	@Cascade(CascadeType.SAVE_UPDATE)
	//@JsonBackReference
	@JsonIgnore
	private List<EtudiantCours> listeEtudiantCours =new ArrayList<>();
	
	
	@OneToMany(mappedBy="cours", orphanRemoval=true)
	@Cascade(CascadeType.SAVE_UPDATE)
	//@JsonBackReference
	@JsonIgnore
	private List<Exercice> listeExercices = new ArrayList<>();
	
	
	/*____________________ctors____________________*/
	public Cours() {
		super();
	}

	public Cours(String libelle, String date, String duree, String description) {
		super();
		this.libelle = libelle;
		this.date = date;
		this.duree = duree;
		this.description = description;
	}

	public Cours(Long idCours, String libelle, String date, String duree, String description) {
		super();
		this.idCours = idCours;
		this.libelle = libelle;
		this.date = date;
		this.duree = duree;
		this.description = description;
	}

	
	/*________________getters/setter____________________*/
	public Long getIdCours() {
		return idCours;
	}

	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<Etudiant> getListeEtudiant() {
//		return listeEtudiant;
//	}
//
//	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
//		this.listeEtudiant = listeEtudiant;
//	}
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<EtudiantCours> getListeEtudiantCours() {
		return listeEtudiantCours;
	}

	public void setListeEtudiantCours(List<EtudiantCours> listeEtudiantCours) {
		this.listeEtudiantCours = listeEtudiantCours;
	}

	public List<Exercice> getListeExercices() {
		return listeExercices;
	}

	public void setListeExercices(List<Exercice> listeExercices) {
		this.listeExercices = listeExercices;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	// ================== Méthodes ==========================//
	
//	/**
//	 * Ajoute un étudiant à la liste des étudiants du cours + ajoute le cours à la liste des cours de l'étudiant
//	 * @param etudiant
//	 */
//	public void addEtudiant(Etudiant etudiant) {
//		this.listeEtudiant.add(etudiant);
//		etudiant.getListeCours().add(this);
//	}
	
	/**
	 * Attribut une matière à la propriété  matière du cours + ajoute le cours à la liste des cours de la matière
	 * @param matiere
	 */
	public void addMatiere(Matiere matiere) {
		this.matiere=matiere;
		matiere.getListeCours().add(this);
	}
	
	/**
	 *  Ajoute un étudiantCours (table de jointure) à la liste des étudiantCours du cours + ajoute le cours à la propriété  cours de l'étudiantCours.
	 * @param etudiantCours
	 */
	public void addEtudiantCours(EtudiantCours etudiantCours) {
		this.listeEtudiantCours.add(etudiantCours);
		etudiantCours.setCours(this);
	}
	
	/**
	 *  Ajoute un exercice  à la liste des exercices du cours + ajoute le cours à l'attribut cours de l'exercice.	 
	 * @param exercice
	 */
	public void addExercice(Exercice exercice) {
		this.listeExercices.add(exercice);
		exercice.setCours(this);
	}
	
	/**
	 * Attribut une promotion à la propriété promotion du cours + ajoute le cours à la liste des cours de la matière
	 * @param promotion
	 */
	public void addPromotion(Promotion promotion) {
		this.promotion=promotion;
		promotion.getListeCours().add(this);
	}
	
	
	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", libelle=" + libelle + ", date=" + date + ", duree=" + duree
				+ ", description=" + description + "]";
	}


}//end class
