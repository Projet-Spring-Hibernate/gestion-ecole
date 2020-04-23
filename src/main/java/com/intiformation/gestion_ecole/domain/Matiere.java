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
	private Long idMatiere;
	
	
	private String libelle;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "enseignant_matiere_promotion",
	joinColumns = @JoinColumn(name="MATIERE_ID"),
	inverseJoinColumns = @JoinColumn(name="id_personne")
	)
	private List<Enseignant> listeEnseignant;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "enseignant_matiere_promotion",
	joinColumns = @JoinColumn(name="MATIERE_ID"),
	inverseJoinColumns = @JoinColumn(name="PROMOTION_ID")
	)
	private List<Promotion> listePromotion;
	
	//relation entre matière et Cours
	@OneToMany(mappedBy="matiere", cascade =CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Cours> listeCours;
	
	
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

	public List<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(List<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	@Override
	public String toString() {
		return "Matiere [idMatiere=" + idMatiere + ", libelle=" + libelle + "]";
	}
	
	
	
	
	
}
