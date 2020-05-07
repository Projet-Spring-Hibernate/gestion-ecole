package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Classe entity pour les exercices. 
 * Relation OneToMany avec cours
 * @author Thanesh
 *
 */
@Entity(name="exercice")
@Table(name="exercices")
public class Exercice implements Serializable {

	/*____________________props____________________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idExercice;
	
	private String libelle;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="COURS_ID", referencedColumnName="ID_COURS")
	//@JsonManagedReference
	@JsonIgnore
	private Cours cours;

	
	/*____________________ctors____________________*/
	public Exercice() {
		super();
	}

	public Exercice(String libelle) {
		this.libelle = libelle;
	}

	public Exercice(Long idExercice, String libelle) {
		this.idExercice = idExercice;
		this.libelle = libelle;
	}

	/*____________________getter/setter____________________*/
	public Long getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(Long idExercice) {
		this.idExercice = idExercice;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	// ================== Méthodes ==========================//
	
	/**
	 * Attribut un cours à la propriété cours de l'exercice + ajoute l'exercice à la liste des exercices du cours 
	 * @param cours
	 */
	public void addCours(Cours cours) {
		this.cours=cours;
		cours.getListeExercices().add(this);
	}
	
	@Override
	public String toString() {
		return "Exercice [idExercice=" + idExercice + ", libelle=" + libelle + ", cours=" + cours + "]";
	}
	
	
	
	
}//end classe
