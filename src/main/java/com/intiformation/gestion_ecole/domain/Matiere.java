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

@Entity(name="matieres")
@Table(name="Matieres")
public class Matiere implements Serializable {


	/*_______________prop_______________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMatiere;
	private String libelle;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="MATIERE_ID"),
	inverseJoinColumns = @JoinColumn(name="ENSEIGNANT_ID")
	)
	private List<Enseignant> listeEnseignant;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "enseigne",
	joinColumns = @JoinColumn(name="MATIERE_ID"),
	inverseJoinColumns = @JoinColumn(name="PROMOTION_ID")
	)
	private List<Promotion> listePromotion;
	
	
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

	public List<Enseignant> getListeEnseignant() {
		return listeEnseignant;
	}

	public void setListeEnseignant(List<Enseignant> listeEnseignant) {
		this.listeEnseignant = listeEnseignant;
	}

	public List<Promotion> getListePromotion() {
		return listePromotion;
	}

	public void setListePromotion(List<Promotion> listePromotion) {
		this.listePromotion = listePromotion;
	}
	
	
	
	
}
