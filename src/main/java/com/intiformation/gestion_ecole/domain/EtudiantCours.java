package com.intiformation.gestion_ecole.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="etudiant_cours")
public class EtudiantCours implements Serializable{

	/*_____________________props___________________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean absence;
	@Column
	private String motif;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "Cours", referencedColumnName="idCours")
	private Cours cours;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "Etudiant", referencedColumnName="identifiant")
	private Etudiant etudiant;

	
	
	/*____________________ctors____________________*/
	
	public EtudiantCours() {
		super();
	}



	public EtudiantCours(boolean absence, String motif) {
		super();
		this.absence = absence;
		this.motif = motif;
	}



	/*________________getters/setter____________________*/
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public boolean isAbsence() {
		return absence;
	}



	public void setAbsence(boolean absence) {
		this.absence = absence;
	}



	public String getMotif() {
		return motif;
	}



	public void setMotif(String motif) {
		this.motif = motif;
	}



	public Cours getCours() {
		return cours;
	}



	public void setCours(Cours cours) {
		this.cours = cours;
	}



	public Etudiant getEtudiant() {
		return etudiant;
	}



	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}



	@Override
	public String toString() {
		return "EtudiantCours [id=" + id + ", absence=" + absence + ", motif=" + motif + ", cours=" + cours
				+ ", etudiant=" + etudiant + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
