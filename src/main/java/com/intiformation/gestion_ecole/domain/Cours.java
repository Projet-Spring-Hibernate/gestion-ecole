package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
	private Long idCours;
	
	@Column
	private String libelle;
	@Column
	private String date;
	@Column
	private String duree;
	@Column
	private String description;
	
	@ManyToMany(mappedBy = "listeCours")
	private List<Etudiant> listeEtudiant;
	
	
	@ManyToOne(cascade= CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name= "promotion_id", referencedColumnName="idPromotion")
	private Promotion promotion;
	
	@ManyToOne(cascade= CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinColumn(name="matiere_id", referencedColumnName="idMatiere")
	private Matiere matiere;
	
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

	
	
	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}

	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}
	
	
	

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", libelle=" + libelle + ", date=" + date + ", duree=" + duree
				+ ", description=" + description + ", listeEtudiant=" + listeEtudiant + ", promotion=" + promotion
				+ ", matiere=" + matiere + "]";
	}


	
	
	
	
	
	

}
