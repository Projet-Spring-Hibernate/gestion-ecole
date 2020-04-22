package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="promotions")
@Table(name="Promotions")
public class Promotion implements Serializable {


	/*_______________prop_______________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPromotion;
	private String libelle;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="PROMOTION_ID"),
	inverseJoinColumns = @JoinColumn(name="ENSEIGNANT_ID")
	)
	private List<Enseignant> listeEnseignant;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="PROMOTION_ID"),
	inverseJoinColumns = @JoinColumn(name="MATIERE_ID")
	)
	private List<Matiere> listeMatiere;
	
	
	
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
	
	
	
	
	
}
