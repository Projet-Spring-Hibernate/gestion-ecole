package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
	private Long idPromotion;
	
	private String libelle;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "enseignant_matiere_promotion",
	joinColumns = @JoinColumn(name="PROMOTION_ID"),
	inverseJoinColumns = @JoinColumn(name="id_personne")
	)
	private List<Enseignant> listeEnseignant;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "enseignant_matiere_promotion",
	joinColumns = @JoinColumn(name="PROMOTION_ID"),
	inverseJoinColumns = @JoinColumn(name="MATIERE_ID")
	)
	private List<Matiere> listeMatiere;
	
	// relation entre promotion et cours
	@OneToMany(mappedBy="promotion", cascade =CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Cours> listeCours;
	
	// relation entre promotion et étudiant
	@ManyToMany(mappedBy = "listePromotion", cascade =CascadeType.PERSIST,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Etudiant> listeEtudiant;
	
	
	
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

	public List<Enseignant> getListeEnseignant() {
		return listeEnseignant;
	}

	public void setListeEnseignant(List<Enseignant> listeEnseignant) {
		this.listeEnseignant = listeEnseignant;
	}

	public List<Matiere> getListeMatiere() {
		return listeMatiere;
	}

	public void setListeMatiere(List<Matiere> listeMatiere) {
		this.listeMatiere = listeMatiere;
	}

	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
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

	@Override
	public String toString() {
		return "Promotion [idPromotion=" + idPromotion + ", libelle=" + libelle + ", listeEnseignant=" + listeEnseignant
				+ ", listeMatiere=" + listeMatiere + ", listeCours=" + listeCours + ", listeEtudiant=" + listeEtudiant
				+ "]";
	}
	
	
	
	
	
}
