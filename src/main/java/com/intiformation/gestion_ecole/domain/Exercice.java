package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="exercice")
@Table(name="exercices")
public class Exercice implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idExercice;
	
	private String libelle;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="COURS_ID", referencedColumnName="idCours")
	private Cours cours;

	public Exercice() {
		super();
	}

	public Exercice(String libelle, Cours cours) {
		this.libelle = libelle;
		this.cours = cours;
	}

	public Exercice(Long idExercice, String libelle, Cours cours) {
		this.idExercice = idExercice;
		this.libelle = libelle;
		this.cours = cours;
	}

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
	
	
	
	
}//end classe
